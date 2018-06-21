package managedBeans;

import ejb.AuthorizationService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@ManagedBean
@ViewScoped
public class PasswordService {

    @ManagedProperty("#{authService}")
    private AuthService authService;

    @EJB(mappedName = "java:global/ejb/AuthorizationServiceImpl!ejb.AuthorizationService")
    private AuthorizationService authorizationService;

    private final static Hashtable jndiProperties = new Hashtable();

    public PasswordService()throws NamingException {
        jndiProperties.put(Context.URL_PKG_PREFIXES,
                "org.jboss.ejb.client.naming");
        authorizationService=lookupAuthorizationServiceEJB();
    }

    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;
    private boolean oldPasswordError = false;


    public String changePassword() {
        authorizationService.changePassword(authService.getUserName(),newPassword);
        return "index.xhmtl?faces-redirect=true";
    }



    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(String getNewPasswordRepeat) {
        this.newPasswordRepeat = getNewPasswordRepeat;
    }

    private static AuthorizationService lookupAuthorizationServiceEJB() throws NamingException {
        final Context context = new InitialContext(jndiProperties);
        return (AuthorizationService) context.lookup("java:global/ejb/AuthorizationServiceImpl!ejb.AuthorizationService");
    }

    public boolean isOldPasswordError() {
        return oldPasswordError;
    }

    public void setOldPasswordError(boolean oldPasswordError) {
        this.oldPasswordError = oldPasswordError;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
