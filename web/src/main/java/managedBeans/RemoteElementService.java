package managedBeans;

import ejb.CategoryService;
import ejb.ElementService;
import events.Notify;
import model.Category;
import model.Element;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Collection;
import java.util.Hashtable;

@ManagedBean
@SessionScoped
public class RemoteElementService {
    @EJB(mappedName = "java:global/ejb/ElementServiceImpl!ejb.ElementService")
    private ElementService elementService;

    @Inject
    @Notify
    private Event<String> importantMessageEvent;

    private final static Hashtable jndiProperties = new Hashtable();


    public RemoteElementService() throws NamingException {
        jndiProperties.put(Context.URL_PKG_PREFIXES,
                "org.jboss.ejb.client.naming");
        elementService=lookupElementServiceEJB();
    }


    private static ElementService lookupElementServiceEJB() throws NamingException {
        final Context context = new InitialContext(jndiProperties);
        return (ElementService) context.lookup("java:global/ejb/ElementServiceImpl!ejb.ElementService");
    }

    public Collection<Element> getAllElements(Category category){
        return elementService.getAllElements(category);
    }

    public Element getElementById(long id){
        return elementService.getElementById(id);
    }

    public Collection<Element> getElementByLabel(Category category, String elementLabel){
        return elementService.getElementByLabel(category,elementLabel);
    }

    public void updateElement(Element element){
        elementService.updateElement(element);
        fireNotify(element.getElementLabel());
    }

    public void deleteElement(Element element){
        elementService.deleteElement(element);
        fireNotify(element.getElementLabel());
    }

    public void insertElement(Element element){
        elementService.insertElement(element);
        fireNotify(element.getElementLabel());
    }

    public Collection<Element> getBestElements(String elementLabel) {
        return elementService.getBestElements(elementLabel);
    }

    private void fireNotify(String message){
        importantMessageEvent.fire(message);
    }

    public ElementService getElementService() {
        return elementService;
    }

    public void setElementService(ElementService elementService) {
        this.elementService = elementService;
    }
}
