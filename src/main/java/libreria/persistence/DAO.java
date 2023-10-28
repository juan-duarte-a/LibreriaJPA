package libreria.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Optional;

abstract class DAO<E> {

    private final EntityManagerFactory emf;

    public DAO() {
        emf = Persistence.createEntityManagerFactory("libreria");
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void save(E entity) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }

    public void update(E entity) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }
    }

    public Optional<E> findById(Class<E> entityClass, Object id) {
        try (EntityManager em = getEntityManager()) {
            return Optional.ofNullable(em.find(entityClass, id));
        }
    }

    public void closeResources() {
        emf.close();
    }

}
