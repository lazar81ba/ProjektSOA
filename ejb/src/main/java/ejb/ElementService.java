package ejb;

import model.Category;
import model.Element;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface ElementService {
    public Collection<Element> getAllElements(Category category);
    public Element getElementById(long id);
    public Collection<Element> getElementByLabel(Category category, String elementLabel);
    public void updateElement(Element element);
    public void deleteElement(Element element);
    public void insertElement(Element element);
}
