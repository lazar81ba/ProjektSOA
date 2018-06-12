package validators;

import ejb.AuthorizationService;
import managedBeans.AuthService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@FacesValidator("oldPasswordValidator")
public class OldPasswordValidator implements Validator{
    private AuthService authService = new AuthService();


    @EJB(mappedName = "java:global/ejb/AuthorizationServiceImpl!ejb.AuthorizationService")
    private AuthorizationService authorizationService;

    private final static Hashtable jndiProperties = new Hashtable();

    public OldPasswordValidator()throws NamingException {
        jndiProperties.put(Context.URL_PKG_PREFIXES,
                "org.jboss.ejb.client.naming");
        authorizationService=lookupAuthorizationServiceEJB();
    }

    private static AuthorizationService lookupAuthorizationServiceEJB() throws NamingException {
        final Context context = new InitialContext(jndiProperties);
        return (AuthorizationService) context.lookup("java:global/ejb/AuthorizationServiceImpl!ejb.AuthorizationService");
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {

        String password = value.toString();

        // Let required="true" do its job.
        if (password == null || password.isEmpty()) {
            return;
        }

        if (!authorizationService.validateOldPassword(authService.getUserName(),password)) {

            throw new ValidatorException(new FacesMessage(
                    "Old password is not correct."));
        }

    }
}
