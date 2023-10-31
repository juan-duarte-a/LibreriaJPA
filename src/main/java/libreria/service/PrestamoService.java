package libreria.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import libreria.entity.Cliente;
import libreria.entity.Libro;
import libreria.entity.Prestamo;
import libreria.persistence.PrestamoDAO;

public class PrestamoService {
    
    private final PrestamoDAO prestamoDAO;
    private final LibroService libroService;
    private final ClienteService clienteService;

    public PrestamoService() {
        prestamoDAO = new PrestamoDAO();
        libroService = new LibroService();
        clienteService = new ClienteService();
    }

    public void savePrestamo(Prestamo prestamo) {
        prestamoDAO.save(prestamo);
    }

    public void updatePrestamo(Prestamo prestamo) {
        prestamoDAO.update(prestamo);
    }

    public Prestamo findPrestamoById(Long id) {
        return prestamoDAO.findById(Prestamo.class, id).orElseThrow(
            () -> new NoSuchElementException("Préstamo no encontrado"));
    }

    public List<Prestamo> findPrestamosByCliente(long documentoCliente) {
        List<Prestamo> prestamos = prestamoDAO.findPrestamosByCliente(documentoCliente);
        return prestamos;
    }

    public Prestamo prestarLibro(long documentoCliente, String tituloLibro, LocalDate fechaPrestamo) {
        Libro libro = libroService.findLibroByTitulo(tituloLibro);
        if (libro.getEjemplaresRestantes() < 1) {
            throw new IllegalStateException("No hay ejemplares disponibles");
        }

        Cliente cliente = clienteService.findClienteByDocumento(documentoCliente);
        Prestamo prestamo = new Prestamo(fechaPrestamo, libro, cliente);
        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
        libroService.updateLibro(libro);
        prestamoDAO.save(prestamo);
        return prestamo;
    }

    public void devolverLibro(long documentoCliente, String isbn, LocalDate fechaDevolucion) {
        Libro libro = libroService.findLibroByISBN(isbn);
        if (libro.getEjemplaresPrestados() < 1) {
            throw new IllegalStateException("No hay préstamos por devolver para el " + 
                    "libro con ISBN " + libro.getIsbn());
        }

        Prestamo prestamo = findPrestamosByCliente(documentoCliente).stream()
                .filter(p -> p.getCliente().getDocumento() == documentoCliente
                        && p.getLibro().getIsbn().equals(isbn))
                .findFirst().orElseThrow(
                        () -> new NoSuchElementException("No hay préstamos del libro " + libro.getIsbn()
                                + " y cliente con documento " + documentoCliente));
        
        prestamo.setFechaDevolucion(fechaDevolucion);
        updatePrestamo(prestamo);
        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
        libroService.updateLibro(libro);
    }

    public void closeResources() {
        prestamoDAO.closeResources();
        clienteService.closeResources();
        libroService.closeResources();
    }

}
