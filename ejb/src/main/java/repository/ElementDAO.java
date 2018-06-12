package repository;

import model.Category;
import model.Element;

import java.util.Collection;

public interface ElementDAO {
    public Collection<Element> getAll();
    public Element getById(long id);
    public Collection<Element> getByCategory(Category category);
    public Collection<Element> getByLabel(String label);
    public Collection<Element> getByLabelAndCategory(Category category, String label);
    public void update(Element element);
    public void delete(Element element);
    public void insert(Element element);
}
