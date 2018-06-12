package ejb;

import org.apache.commons.codec.digest.DigestUtils;
import repository.UsersDAO;
import repository.implementation.UsersDAOImpl;

import javax.ejb.Remote;
import javax.ejb.Stateless;


@Stateless
@Remote(AuthorizationService.class)
public class AuthorizationServiceImpl implements AuthorizationService {

    private UsersDAO usersDAO= new UsersDAOImpl();

    @Override
    public void changePassword(String login, String newPassword) {
        usersDAO.changeUserPass(login, DigestUtils.sha256Hex(newPassword));
    }

    @Override
    public boolean validateOldPassword(String login, String oldPassword) {
        return DigestUtils.sha256Hex(oldPassword).equals(usersDAO.getUserPass(login));
    }
}
