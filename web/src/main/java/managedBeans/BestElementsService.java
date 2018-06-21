package managedBeans;

import events.Notify;
import model.Element;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Collection;
import java.util.Hashtable;

@ManagedBean
@ApplicationScoped
public class BestElementsService {

    @EJB(mappedName = "java:global/ejb/BestElementServiceImpl!ejb.BestElementService")
    private ejb.BestElementService bestElementsService;

    private final static Hashtable jndiProperties = new Hashtable();


    public BestElementsService() throws NamingException {
        jndiProperties.put(Context.URL_PKG_PREFIXES,
                "org.jboss.ejb.client.naming");
        bestElementsService=lookupBestElementsServiceEJB();
    }


    private static ejb.BestElementService lookupBestElementsServiceEJB() throws NamingException {
        final Context context = new InitialContext(jndiProperties);
        return (ejb.BestElementService) context.lookup("java:global/ejb/BestElementServiceImpl!ejb.BestElementService");
    }


    public void observeEvent(@Observes @Notify String message){
        //updateCollection(message);

        bestElementsService.updateCollection(message);
        updateTables();
    }



    private void updateTables(){
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/update", new FacesMessage("update"));

    }

    public Collection<Element> getBestDragons() {
        return bestElementsService.getBestDragons();
    }

    public Collection<Element> getBestMages() {
        return bestElementsService.getBestMages();
    }

    public Collection<Element> getBestElves() {
        return bestElementsService.getBestElves();
    }



}
