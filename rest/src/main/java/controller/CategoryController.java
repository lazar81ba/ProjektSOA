package controller;

import converter.CategoryConverter;
import converter.ElementConverter;
import remote.RemoteElementService;
import restModel.Category;
import org.codehaus.jackson.annotate.JsonIgnore;
import remote.RemoteCategoryService;
import restModel.Element;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/category")

public class CategoryController {

    @Context
    private UriInfo info;

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private ServletContext servletContext;

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
    public List<Category> getCategories(){
        // servletRequest.getUserPrincipal().getName()
        return (List<Category>) CategoryConverter.convertList(
                remoteCategoryService.getAllCategories("223322"));
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Category getCategory(@PathParam("id") Long id){
        return CategoryConverter.convertSingleCategory(remoteCategoryService.getCategoryService().getCategoryById(id));
    }

    @GET
    @Path("/{id}/elements")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Element> getCategoryElements(@PathParam("id") Long id){
        return (List<Element>) CategoryConverter.convertSingleCategory(remoteCategoryService.getCategoryService().getCategoryById(id)).getElements();
    }

    @POST
    @Path("/{id}/elements")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getCategoryElements(@PathParam("id") Long id, Element element){
        model.Element elementDoAdd = ElementConverter.convertToModelElement(element);
        elementDoAdd.setCategory(remoteCategoryService.getCategoryService().getCategoryById(id));
        remoteElementService.getElementService().insertElement(elementDoAdd);
        return Response.status(201).entity("Done").build();
    }


    @GET
    @Path("/{id}/elements/{idElement}")
    @Produces({MediaType.APPLICATION_JSON})
    public Element getCategoryElement(@PathParam("id") Long id, @PathParam("idElement") final Long idElement){
        return CategoryConverter.convertSingleCategory(remoteCategoryService.getCategoryService().getCategoryById(id)).getElements()
                .stream().filter(x -> x.getId() == idElement).findFirst().get();
    }

    public UriInfo getInfo() {
        return info;
    }

    public void setInfo(UriInfo info) {
        this.info = info;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
