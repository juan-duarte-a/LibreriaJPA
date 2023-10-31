package libreria.persistence;

import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import libreria.entity.Cliente;

public class ClienteDAO extends DAO<Cliente> {

    public Optional<Cliente> findClienteByDocumento(long documento) {
        try (EntityManager em = getEntityManager()) {
            String jpql = "SELECT c FROM Cliente c WHERE c.documento = :documento";
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("documento", documento);
            return Optional.ofNullable(query.getSingleResult());
        }
    }
    
}