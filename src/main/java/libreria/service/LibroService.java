package libreria.service;

import jakarta.persistence.NoResultException;
import libreria.entity.Libro;
import libreria.persistence.LibroDAO;

import java.util.List;
import java.util.NoSuchElementException;

public class LibroService {

    private final LibroDAO libroDAO;

    public LibroService() {
        libroDAO = new LibroDAO();
    }

    public void saveLibro(Libro libro) {
        libroDAO.save(libro);
    }

    public void removeLibro(Libro libro) {
        libro.setAlta(false);
        libroDAO.update(libro);
    }

    public void updateLibro(Libro libro) {
        libroDAO.update(libro);
    }

    public Libro findLibroByISBN(String isbn) {
        return libroDAO.findById(Libro.class, isbn).orElseThrow(
                () -> new NoSuchElementException("Libro no encontrado"));
    }

    public Libro findLibroByTitulo(String titulo) {
        try {
            return libroDAO.findByTitulo(titulo);
        } catch (NoResultException e) {
            throw new NoSuchElementException("Libro no encontrado");
        }
    }

    public List<Libro> findLibrosByAutor(String nombreAutor) {
        List<Libro> libros = libroDAO.findByAutor(nombreAutor);
        if (libros.isEmpty()) {
            throw new NoSuchElementException("Libros no encontrados");
        }
        return libros;
    }

    public List<Libro> findLibrosByEditorial(String nombreEditorial) {
        List<Libro> libros = libroDAO.findByEditorial(nombreEditorial);
        if (libros.isEmpty()) {
            throw new NoSuchElementException("Libros no encontrados");
        }
        return libros;
    }

    public void closeResources() {
        libroDAO.closeResources();
    }

}
