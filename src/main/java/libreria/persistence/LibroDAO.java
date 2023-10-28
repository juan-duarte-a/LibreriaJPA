package libreria.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import libreria.entity.Libro;

import java.util.List;

public class LibroDAO extends DAO<Libro> {

    public Libro findByTitulo(String titulo) {
        try (EntityManager em = getEntityManager()) {
            String jpql = "SELECT l FROM Libro l WHERE l.titulo LIKE :titulo AND l.alta = true";
            TypedQuery<Libro> query = em.createQuery(jpql, Libro.class);
            query.setParameter("titulo", titulo);
            return query.getSingleResult();
        }
    }

    public List<Libro> findByAutor(String nombreAutor) {
        try (EntityManager em = getEntityManager()) {
            String jpql = "SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombreAutor AND l.alta = true";
            TypedQuery<Libro> query = em.createQuery(jpql, Libro.class);
            query.setParameter("nombreAutor", nombreAutor);
            return query.getResultList();
        }
    }

    public List<Libro> findByEditorial(String nombreEditorial) {
        try (EntityManager em = getEntityManager()) {
            String jpql = "SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombreEditorial AND l.alta = true";
            TypedQuery<Libro> query = em.createQuery(jpql, Libro.class);
            query.setParameter("nombreEditorial", nombreEditorial);
            return query.getResultList();
        }
    }

}
