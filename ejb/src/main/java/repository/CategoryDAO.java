package repository;

import model.Category;
import model.User;

import java.util.Collection;

public interface CategoryDAO {
    public Collection<Category> getAll();
    public Collection<Category> getAllByUser(User user);
    public Category getById(long id);
    public Collection<Category> getByCategoryLabel(String categoryLabel);
    public Collection<Category> getByCategoryLabelAndUser(User user, String categoryLabel);
    public void update(Category category);
    public void delete(Category category);
    public void insert(Category category);
}
