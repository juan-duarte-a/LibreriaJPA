package libreria.service;

import java.util.NoSuchElementException;

import libreria.entity.Cliente;
import libreria.persistence.ClienteDAO;

public class ClienteService {

    private final ClienteDAO clienteDAO;
    
    public ClienteService() {
        clienteDAO = new ClienteDAO();
    }

    public void saveCliente(Cliente cliente) {
        clienteDAO.save(cliente);
    }

    public void updateCliente(Cliente cliente) {
        clienteDAO.update(cliente);
    }

    public Cliente findClienteById(Long id) {
        return clienteDAO.findById(Cliente.class, id).orElseThrow(
            () -> new NoSuchElementException("Cliente no encontrado"));
    }

    public Cliente findClienteByDocumento(long documento) {
        return clienteDAO.findClienteByDocumento(documento).orElseThrow(
            () -> new NoSuchElementException("Cliente no encontrado"));
    }

    public void closeResources() {
        clienteDAO.closeResources();
    }
    
}