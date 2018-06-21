package managedBeans;


import ejb.CategoryService;
import model.Category;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

@ManagedBean
@SessionScoped
public class RemoteCategoryService {
    @EJB(mappedName = "java:global/ejb/CategoryServiceImpl!ejb.CategoryService")
    private CategoryService categoryService;

    @ManagedProperty("#{authService}")
    private AuthService authService ;

    private final static Hashtable jndiProperties = new Hashtable();


    public RemoteCategoryService() throws NamingException {
        jndiProperties.put(Context.URL_PKG_PREFIXES,
                "org.jboss.ejb.client.naming");
        categoryService=lookupCategoryServiceEJB();
    }

    private static CategoryService lookupCategoryServiceEJB() throws NamingException {
        final Context context = new InitialContext(jndiProperties);
        return (CategoryService) context.lookup("java:global/ejb/CategoryServiceImpl!ejb.CategoryService");
    }


    public List<Category> getAllCategories(){
        return (List<Category>) categoryService.getAllCategories(authService.getUserName());
    }

    public Category getCategoryById(long id){
        return categoryService.getCategoryById(id);
    }

    public Collection<Category> getCategoriesByCategoryLabel(String login, String categoryLabel){
        return categoryService.getCategoriesByCategoryLabel(login,categoryLabel);
    }

    public void updateCategory(Category category){
        categoryService.updateCategory(category);
    }

    public void deleteCategory(Category category){
        categoryService.deleteCategory(category);
    }

    public void insertCategory(String login, Category category){
        categoryService.insertCategory(login,category);
    }


    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
