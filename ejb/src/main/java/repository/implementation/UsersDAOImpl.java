package repository.implementation;

import model.User;
import model.UserRole;
import repository.UsersDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UsersDAOImpl implements UsersDAO {
    @Override
    public String getUserRole(String login) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        String role = "";
        try {
            TypedQuery<UserRole> query =
                    em.createNamedQuery("UserRole.find", UserRole.class);
            query.setParameter("login",login);
            role = query.getSingleResult().getRole();

        }
        catch(Exception e) {
            System.err.println("getById error: " + e);
        } finally {
            em.close();
            factory.close();
            return role;
        }
    }

    @Override
    public User getUser(String login) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        User user = null;
        try {
            TypedQuery<User> query =
                    em.createNamedQuery("User.find", User.class);
            query.setParameter("login",login);
            user = query.getSingleResult();

        }
        catch(Exception e) {
            System.err.println("getById error: " + e);
        } finally {
            em.close();
            factory.close();
            return user;
        }
    }

    @Override
    public String getUserPass(String login) {
        return getUser(login).getPass();
    }

    @Override
    public void changeUserPass(String login, String password) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, login);
            user.setPass(password);
            em.merge(user);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.err.println("Update error: " + e);
        } finally {
            em.close();
            factory.close();
        }
    }
}
