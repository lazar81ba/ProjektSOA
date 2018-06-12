package ejb;

import model.Category;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface CategoryService {
    public Collection<Category> getAllCategories(String login);
    public Category getCategoryById(long id);
    public Collection<Category> getCategoriesByCategoryLabel(String login, String categoryLabel);
    public void updateCategory(Category category);
    public void deleteCategory(Category category);
    public void insertCategory(String login, Category category);
}
