package libreria.service;

import jakarta.persistence.NoResultException;
import libreria.entity.Autor;
import libreria.persistence.AutorDAO;

import java.util.NoSuchElementException;

public class AutorService {

    private final AutorDAO autorDAO;

    public AutorService() {
        autorDAO = new AutorDAO();
    }

    public void saveAutor(Autor autor) {
        autorDAO.save(autor);
    }

    public void removeAutor(Autor autor) {
        autor.setAlta(false);
        autorDAO.update(autor);
    }

    public void updateAutor(Autor autor) {
        autorDAO.update(autor);
    }

    public Autor findAutorByNombre(String nombre) {
        try {
            return autorDAO.findByNombre(nombre);
        } catch (NoResultException e) {
            throw new NoSuchElementException("Autor no encontrado");
        }
    }

    public void closeResources() {
        autorDAO.closeResources();
    }

}
