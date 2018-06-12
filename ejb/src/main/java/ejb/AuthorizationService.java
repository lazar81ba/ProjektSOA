package ejb;

import javax.ejb.Remote;

@Remote
public interface AuthorizationService {
    public void changePassword(String login, String newPassword);
    public boolean validateOldPassword(String login, String oldPassword);
}
