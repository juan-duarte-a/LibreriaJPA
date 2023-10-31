package libreria.persistence;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import libreria.entity.Prestamo;

public class PrestamoDAO extends DAO<Prestamo> {

    public List<Prestamo> findPrestamosByCliente(long documentoCliente) {;
        try (EntityManager em = getEntityManager()) {
            String jpql = "SELECT p FROM Prestamo p " 
                    + "WHERE p.cliente.documento = :documentoCliente " 
                    + "AND p.fechaDevolucion is null";
            TypedQuery<Prestamo> query = em.createQuery(jpql, Prestamo.class);
            query.setParameter("documentoCliente", documentoCliente);
            return query.getResultList();
        }
    }
    
}