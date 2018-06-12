package ejb;

import model.Category;
import repository.CategoryDAO;
import repository.UsersDAO;
import repository.implementation.CategoryDAOImpl;
import repository.implementation.UsersDAOImpl;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Collection;

@Stateless
@Remote(CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAOImpl();
    private UsersDAO usersDAO = new UsersDAOImpl();

    @Override
    public Collection<Category> getAllCategories(String login) {
        if(usersDAO.getUserRole(login).equals("admin"))
            return categoryDAO.getAll();
        else
            return categoryDAO.getAllByUser(usersDAO.getUser(login));
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryDAO.getById(id);
    }

    @Override
    public Collection<Category> getCategoriesByCategoryLabel(String login, String categoryLabel) {

        if(usersDAO.getUserRole(login).equals("admin"))
            return categoryDAO.getByCategoryLabel(categoryLabel);
        else
            return categoryDAO.getByCategoryLabelAndUser(usersDAO.getUser(login),categoryLabel);

    }

    @Override
    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryDAO.delete(category);
    }

    @Override
    public void insertCategory(String login, Category category) {
        category.setUser(usersDAO.getUser(login));
        categoryDAO.insert(category);
    }
}
