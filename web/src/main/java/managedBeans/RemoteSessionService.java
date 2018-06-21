package managedBeans;

import ejb.ElementService;
import ejb.SessionService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@ManagedBean
@SessionScoped
public class RemoteSessionService {

    @EJB(mappedName = "java:global/ejb/SessionServiceImpl!ejb.SessionService")
    private SessionService sessionService;


    private final static Hashtable jndiProperties = new Hashtable();


    public RemoteSessionService() throws NamingException {
        jndiProperties.put(Context.URL_PKG_PREFIXES,
                "org.jboss.ejb.client.naming");
        sessionService=lookupSessionServiceEJB();
    }


    private static SessionService lookupSessionServiceEJB() throws NamingException {
        final Context context = new InitialContext(jndiProperties);
        return (SessionService) context.lookup("java:global/ejb/SessionServiceImpl!ejb.SessionService");
    }

    public void addSession(String login, String sessionID){
        sessionService.addSession(login,sessionID);
    }

    public void removeSession(String login){
        sessionService.removeSession(login);
    }

    public String getSession(String login) {
        return sessionService.getSession(login);
    }

    public boolean wasSessionCreated(String login){
        return sessionService.wasSessionCreated(login);
    }



}
