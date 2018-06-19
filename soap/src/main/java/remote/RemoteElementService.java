package remote;

import ejb.ElementService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@ManagedBean
@SessionScoped
public class RemoteElementService {
    @EJB(mappedName = "java:global/ejb/ElementServiceImpl!ejb.ElementService")
    private ElementService elementService;


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

    public ElementService getElementService() {
        return elementService;
    }

    public void setElementService(ElementService elementService) {
        this.elementService = elementService;
    }
}
