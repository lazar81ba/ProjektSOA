package ejb;

import model.Category;
import model.Element;
import repository.CategoryDAO;
import repository.ElementDAO;
import repository.implementation.CategoryDAOImpl;
import repository.implementation.ElementDAOImpl;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Collection;

@Stateless
@Remote(ElementService.class)
public class ElementServiceImpl implements ElementService {

    private ElementDAO elementDAO = new ElementDAOImpl();
    private CategoryDAO categoryDAO= new CategoryDAOImpl();

    @Override
    public Collection<Element> getAllElements(Category category) {
        return elementDAO.getByCategory(category);
    }

    @Override
    public Element getElementById(long id) {
        return elementDAO.getById(id);
    }

    @Override
    public Collection<Element> getElementByLabel(Category category, String elementLabel) {
        return elementDAO.getByLabelAndCategory(category,elementLabel);
    }


    @Override
    public void updateElement(Element element) {
        elementDAO.update(element);
    }

    @Override
    public void deleteElement(Element element) {
        elementDAO.delete(element);
    }

    @Override
    public void insertElement(Element element) {
        elementDAO.insert(element);
    }
}
