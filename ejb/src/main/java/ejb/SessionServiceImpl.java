package ejb;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.LinkedHashMap;
import java.util.Map;

@Singleton
@Remote(SessionService.class)
public class SessionServiceImpl implements SessionService {

    private Map<String, String> sessionMap = new LinkedHashMap<>();


    @Override
    public void addSession(String login, String sessionID) {
        sessionMap.put(login,sessionID);
    }

    @Override
    public boolean wasSessionCreated(String login) {
        return sessionMap.containsKey(login);
    }

    @Override
    public String getSession(String login) {
        return sessionMap.get(login);
    }

    @Override
    public void removeSession(String login) {
        sessionMap.remove(login);
    }
}
