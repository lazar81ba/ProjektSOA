package repository;

import model.User;

public interface UsersDAO {
    public String getUserRole(String login);
    public User getUser(String login);
    public String getUserPass(String login);
    public void changeUserPass(String login, String password);
}
