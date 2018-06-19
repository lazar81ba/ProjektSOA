package controller;

import converter.CategoryConverter;
import converter.ElementConverter;
import remote.RemoteElementService;
import restModel.Category;
import org.codehaus.jackson.annotate.JsonIgnore;
import remote.RemoteCategoryService;
import restModel.Element;
import translator.CategoryTranslator;
import translator.ElementTranslator;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Locale;

@Path("/category")

public class CategoryController {


    @Context
    private HttpServletRequest servletRequest;

    private RemoteCategoryService remoteCategoryService;
    private RemoteElementService remoteElementService;


    public CategoryController(){
        try {
            this.remoteCategoryService = new RemoteCategoryService();
            this.remoteElementService = new RemoteElementService();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategories( @Context Request request){
        List<Variant> vs =
                Variant.languages(new Locale("en"), new Locale("pl")).build();
        Variant bestVariant = request.selectVariant(vs);
        List<Category> categories = (List<Category>) CategoryConverter.convertList(
                remoteCategoryService.getAllCategories(servletRequest.getUserPrincipal().getName()));
        if(bestVariant.getLanguage().getLanguage().equals("pl"))
            return CategoryTranslator.translateCategories(categories);
        return categories;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Category getCategory(@PathParam("id") Long id, @Context Request request){
        List<Variant> vs =
                Variant.languages(new Locale("en"), new Locale("pl")).build();
        Variant bestVariant = request.selectVariant(vs);
        Category category = CategoryConverter.convertSingleCategory(remoteCategoryService.getCategoryService().getCategoryById(id));
        if(bestVariant.getLanguage().getLanguage().equals("pl"))
            return CategoryTranslator.translateCategory(category);
        return category;
    }

    @GET
    @Path("/{id}/elements")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Element> getCategoryElements(@PathParam("id") Long id,@Context Request request){
        List<Variant> vs =
                Variant.languages(new Locale("en"), new Locale("pl")).build();
        Variant bestVariant = request.selectVariant(vs);
        List<Element> elements = (List<Element>) CategoryConverter.convertSingleCategory(remoteCategoryService.getCategoryService().getCategoryById(id)).getElements();
        if(bestVariant.getLanguage().getLanguage().equals("pl"))
            return ElementTranslator.translateElements(elements);
        return elements;

    }

    @POST
    @Path("/{id}/elements")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getCategoryElements(@PathParam("id") Long id, Element element){
        model.Element elementToAdd = ElementConverter.convertToModelElement(element);
        elementToAdd.setCategory(remoteCategoryService.getCategoryService().getCategoryById(id));
        remoteElementService.getElementService().insertElement(elementToAdd);
        return Response.status(201).entity("Done").build();
    }


    @GET
    @Path("/{id}/elements/{idElement}")
    @Produces({MediaType.APPLICATION_JSON})
    public Element getCategoryElement(@PathParam("id") Long id, @PathParam("idElement") final Long idElement, @Context Request request){
        List<Variant> vs =
                Variant.languages(new Locale("en"), new Locale("pl")).build();
        Variant bestVariant = request.selectVariant(vs);
        Element element = CategoryConverter.convertSingleCategory(remoteCategoryService.getCategoryService().getCategoryById(id)).getElements()
                .stream().filter(x -> x.getId() == idElement).findFirst().get();
        if(bestVariant.getLanguage().getLanguage().equals("pl"))
            return ElementTranslator.translateElement(element);
        return element;
    }


    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

}
