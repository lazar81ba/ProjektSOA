package managedBeans;

import model.Category;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CategoryService {

    @ManagedProperty("#{remoteCategoryService}")
    private RemoteCategoryService remoteCategoryService;
    private AuthService authService = new AuthService();

    private Category panelCategory;
    private boolean update;

    public CategoryService() {

    }

    public String insert(){
        panelCategory = new Category();
        update = false;
        return "categoryPanel";
    }


    public String update(Category category){
        panelCategory = category;
        update = true;
        return "categoryPanel";
    }

    public String submit(){
        if(update)
            remoteCategoryService.getCategoryService().updateCategory(panelCategory);
        else
            remoteCategoryService.getCategoryService().insertCategory(authService.getUserName(), panelCategory);
        return "index.xhmtl?faces-redirect=true";

    }


    public Category getPanelCategory() {
        return panelCategory;
    }

    public void setPanelCategory(Category panelCategory) {
        this.panelCategory = panelCategory;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void setRemoteCategoryService(RemoteCategoryService remoteCategoryService) {
        this.remoteCategoryService = remoteCategoryService;
    }
}
