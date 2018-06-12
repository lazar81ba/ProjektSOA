package managedBeans;


import ejb.CategoryService;
import model.Category;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;

@ManagedBean
@SessionScoped
public class RemoteCategoryService {
    @EJB(mappedName = "java:global/ejb/CategoryServiceImpl!ejb.CategoryService")
    private CategoryService categoryService;

    private AuthService authService = new AuthService();

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

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public List<Category> getAllCategories(){
        return (List<Category>) categoryService.getAllCategories(authService.getUserName());
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
