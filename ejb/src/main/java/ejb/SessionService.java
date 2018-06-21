package ejb;

import javax.ejb.Remote;
import javax.ejb.Singleton;

@Remote
public interface SessionService {
    public void addSession(String login, String sessionID);
    public boolean wasSessionCreated(String login);
    public String getSession(String login);
    public void removeSession(String login);
}
