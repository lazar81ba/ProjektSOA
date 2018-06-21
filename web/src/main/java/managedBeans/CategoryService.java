package managedBeans;

import model.Category;
import model.Element;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean
@ViewScoped
public class CategoryService {

    @ManagedProperty("#{remoteCategoryService}")
    private RemoteCategoryService remoteCategoryService;

    @ManagedProperty("#{authService}")
    private AuthService authService;

    private Category panelCategory;
    private boolean update;

    @PostConstruct
    public void setUp(){
        Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String categoryID = params.get("categoryID");
        if(categoryID!=null && !categoryID.equals("")){
            panelCategory = remoteCategoryService.getCategoryById(Long.parseLong(categoryID));
            update = true;
        }
        else
            panelCategory = new Category();
    }

    public String submit(){
        if(update)
            remoteCategoryService.updateCategory(panelCategory);
        else
            remoteCategoryService.insertCategory(authService.getUserName(), panelCategory);
        return "index.xhmtl?faces-redirect=true";

    }


    public Category getPanelCategory() {
        return panelCategory;
    }

    public void setPanelCategory(Category panelCategory) {
        this.panelCategory = panelCategory;
    }

    public void setRemoteCategoryService(RemoteCategoryService remoteCategoryService) {
        this.remoteCategoryService = remoteCategoryService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
