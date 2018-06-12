package repository.implementation;

import model.Category;
import model.User;
import repository.CategoryDAO;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public Collection<Category> getAll() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Collection<Category> categories = new LinkedList();
        try {
            TypedQuery<Category> query =
                    em.createNamedQuery("Category.findAll", Category.class);

            categories = query.getResultList();

        }
        catch(Exception e) {
            System.err.println("getAll error: " + e);
        } finally {
            em.close();
            factory.close();
            return categories;
        }
    }

    @Override
    public Collection<Category> getAllByUser(User user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Collection<Category> categories = new LinkedList();
        try {
            TypedQuery<Category> query =
                    em.createNamedQuery("Category.findByUser", Category.class);
            query.setParameter("user",user);
            categories = query.getResultList();

        }
        catch(Exception e) {
            System.err.println("getAll error: " + e);
        } finally {
            em.close();
            factory.close();
            return categories;
        }
    }

    @Override
    public Category getById(long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Category category = null;
        try {
            TypedQuery<Category> query =
                    em.createNamedQuery("Category.findById", Category.class);
            query.setParameter("id",id);
            category = query.getSingleResult();

        }
        catch(Exception e) {
            System.err.println("getById error: " + e);
        } finally {
            em.close();
            factory.close();
            return category;
        }
    }

    @Override
    public Collection<Category> getByCategoryLabel(String categoryLabel) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Collection<Category> categories = new LinkedList();
        try {
            TypedQuery<Category> query =
                    em.createNamedQuery("Category.findByCategoryLabel", Category.class);
            query.setParameter("categoryLabel",categoryLabel);
            categories = query.getResultList();

        }
        catch(Exception e) {
            System.err.println("getByCategory error: " + e);
        } finally {
            em.close();
            factory.close();
            return categories;
        }
    }

    @Override
    public Collection<Category> getByCategoryLabelAndUser(User user, String categoryLabel) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Collection<Category> categories = new LinkedList();
        try {
            TypedQuery<Category> query =
                    em.createNamedQuery("Category.findByCategoryLabelAndUser", Category.class);
            query.setParameter("categoryLabel",categoryLabel);
            query.setParameter("user",user);
            categories = query.getResultList();

        }
        catch(Exception e) {
            System.err.println("getByCategory error: " + e);
        } finally {
            em.close();
            factory.close();
            return categories;
        }
    }

    @Override
    public void update(Category category) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.err.println("Update error: " + e);
        } finally {
            em.close();
            factory.close();
        }
    }

    @Override
    public void delete(Category category) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Category categoryToDelete = em.find(Category.class, category.getId());
            em.remove(categoryToDelete);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.err.println("Delete error: " + e);
        } finally {
            em.close();
            factory.close();
        }
    }

    @Override
    public void insert(Category category) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.err.println("Insert error: " + e);
        } finally {
            em.close();
            factory.close();
        }
    }
}
