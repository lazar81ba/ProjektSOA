package repository.implementation;

import model.Category;
import model.Element;
import repository.ElementDAO;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

public class ElementDAOImpl implements ElementDAO {
    @Override
    public Collection<Element> getAll() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Collection<Element> elements = new LinkedList();
        try {
            TypedQuery<Element> query =
                    em.createNamedQuery("Element.findAll", Element.class);
            elements = query.getResultList();

        }
        catch(Exception e) {
            System.err.println("getAll error: " + e);
        } finally {
            em.close();
            factory.close();
            return elements;
        }
    }

    @Override
    public Element getById(long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Element element = null;
        try {
            TypedQuery<Element> query =
                    em.createNamedQuery("Element.findById", Element.class);
            query.setParameter("id",id);
            element = query.getSingleResult();

        }
        catch(Exception e) {
            System.err.println("getById error: " + e);
        } finally {
            em.close();
            factory.close();
            return element;
        }
    }

    @Override
    public Collection<Element> getByCategory(Category category) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Collection<Element> elements = new LinkedList();
        try {
            TypedQuery<Element> query =
                    em.createNamedQuery("Element.findByCategory", Element.class);
            query.setParameter("category",category);
            elements = query.getResultList();

        }
        catch(Exception e) {
            System.err.println("getByCategory error: " + e);
        } finally {
            em.close();
            factory.close();
            return elements;
        }
    }

    @Override
    public Collection<Element> getByLabel(String label) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Collection<Element> elements = new LinkedList();
        try {
            TypedQuery<Element> query =
                    em.createNamedQuery("Element.findByLabel", Element.class);
            query.setParameter("label",label);
            elements = query.getResultList();

        }
        catch(Exception e) {
            System.err.println("getByCategory error: " + e);
        } finally {
            em.close();
            factory.close();
            return elements;
        }
    }

    @Override
    public Collection<Element> getByLabelAndCategory(Category category, String label) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        Collection<Element> elements = new LinkedList();
        try {
            TypedQuery<Element> query =
                    em.createNamedQuery("Element.findByLabelAndCategory", Element.class);
            query.setParameter("category",category);
            query.setParameter("label",label);
            elements = query.getResultList();

        }
        catch(Exception e) {
            System.err.println("getByCategory error: " + e);
        } finally {
            em.close();
            factory.close();
            return elements;
        }
    }

    @Override
    public void update(Element element) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(element);
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
    public void delete(Element element) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Element elementToDelete = em.find(Element.class, element.getId());
            em.remove(elementToDelete);
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
    public void insert(Element element) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fantasy-JPA");
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(element);
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
