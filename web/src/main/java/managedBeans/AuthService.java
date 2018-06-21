package managedBeans;

import ejb.AuthorizationService;
import ejb.CategoryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;

@ManagedBean
@SessionScoped
public class AuthService {

    private RemoteSessionService sessionService;

    public AuthService(){
        try {
            sessionService = new RemoteSessionService();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserAdmin(){
        return getRequest().isUserInRole("admin");
    }


    public String getUserRole() {
        if(isUserAdmin())
            return "admin";
        else
            return "user";
    }

    public String getUserName() {
        return getRequest().getUserPrincipal().getName();
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String logout() {
        sessionService.removeSession(getRequest().getUserPrincipal().getName());
        getRequest().getSession().invalidate();
        return "index.xhmtl?faces-redirect=true";
    }



}
