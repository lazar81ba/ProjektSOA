package converter;


import managedBeans.AuthService;
import managedBeans.RemoteCategoryService;
import model.Category;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "categoryConverter")
@RequestScoped
@FacesConverter(value = "categoryConverter")
public class CategoryConverter implements Converter {

    public final static String CONVERTER_ID = "categoryConverter";


    private AuthService authService = new AuthService();

    @ManagedProperty("#{remoteCategoryService}")
    private RemoteCategoryService remoteCategoryService;

    public CategoryConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }
        Category category = remoteCategoryService.getCategoryService().getCategoriesByCategoryLabel(authService.getUserName(),submittedValue)
                .stream().findFirst().get();

        return category;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }


        return String.valueOf(((Category) modelValue).getCategoryLabel());
    }


    public void setRemoteCategoryService(RemoteCategoryService remoteCategoryService) {
        this.remoteCategoryService = remoteCategoryService;
    }
}
