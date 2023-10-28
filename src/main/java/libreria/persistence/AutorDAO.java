package libreria.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import libreria.entity.Autor;

public class AutorDAO extends DAO<Autor> {

    public Autor findByNombre(String nombre) {
        try (EntityManager em = getEntityManager()) {
            String jpql = "SELECT a FROM Autor a WHERE a.nombre LIKE :nombre AND a.alta = true";
            TypedQuery<Autor> query = em.createQuery(jpql, Autor.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        }
    }

}
