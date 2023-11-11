package libreria;

import libreria.entity.Autor;
import libreria.entity.Cliente;
import libreria.entity.Editorial;
import libreria.entity.Libro;
import libreria.entity.Prestamo;
import libreria.service.AutorService;
import libreria.service.ClienteService;
import libreria.service.EditorialService;
import libreria.service.LibroService;
import libreria.service.PrestamoService;

import java.time.LocalDate;
import java.util.ArrayList;

public class Libreria {

    private final AutorService autorService;
    private final EditorialService editorialService;
    private final LibroService libroService;
    private final ClienteService clienteService;
    private final PrestamoService prestamoService;

    public static void main(String[] args) {
        Libreria libreria = null;
        try {
            libreria = new Libreria();
            libreria.insertData();

            Libro libro = libreria.libroService.findLibroByTitulo("For Whom the Bell Tolls");
            libro.setEjemplares(5);
            libreria.libroService.updateLibro(libro);

            libro = libreria.libroService.findLibroByTitulo("Murder on the Orient Express");
            libro.setEjemplares(10);
            libreria.libroService.updateLibro(libro);

            Prestamo prestamo = libreria.prestarLibro(
                80234678, 
                "For Whom the Bell Tolls", 
                LocalDate.now().minusDays(15));

            System.out.println(prestamo);

            prestamo = libreria.prestarLibro(
                10468596, 
                "Murder on the Orient Express", 
                LocalDate.now().minusDays(10));

            System.out.println();
            System.out.println(prestamo);

            libro = libreria.libroService.findLibroByTitulo("For Whom the Bell Tolls");
            libreria.devolverLibro(80234678, libro.getIsbn(), LocalDate.now());

            System.out.println();
            libreria.printLibroByTitulo("For Whom the Bell Tolls");

            System.out.println();
            libreria.prestamoService.findPrestamosByCliente(80234678).forEach(System.out::println);
        } finally {
            if (libreria != null) {
                System.out.println("\nCerrando recursos...");
                libreria.closeResources();
            }
        }
    }

    public Libreria() {
        autorService = new AutorService();
        editorialService = new EditorialService();
        libroService = new LibroService();
        clienteService = new ClienteService();
        prestamoService = new PrestamoService();
    }

    public void printAutorByNombre(String nombre) {
        System.out.println(autorService.findAutorByNombre(nombre));
    }

    public void printLibroByISBN(String isbn) {
        System.out.println(libroService.findLibroByISBN(isbn));
    }

    public void printLibroByTitulo(String titulo) {
        System.out.println(libroService.findLibroByTitulo(titulo));
    }

    public void printLibrosByAutor(String nombreAutor) {
        libroService.findLibrosByAutor(nombreAutor).forEach(System.out::println);
    }

    public void printLibrosByEditorial(String nombreEditorial) {
        libroService.findLibrosByEditorial(nombreEditorial).forEach(System.out::println);
    }

    public Prestamo prestarLibro(long documentoCliente, String tituloLibro, LocalDate fechaPrestamo) {
        return prestamoService.prestarLibro(documentoCliente, tituloLibro, fechaPrestamo);
    }

    public void devolverLibro(long documentoCliente, String isbn, LocalDate fechaDevolucion) {
        prestamoService.devolverLibro(documentoCliente, isbn, fechaDevolucion);
    }

    public void closeResources() {
        autorService.closeResources();
        editorialService.closeResources();
        libroService.closeResources();
        clienteService.closeResources();
        prestamoService.closeResources();
    }

    public void insertData() {
        ArrayList<Autor> autores = new ArrayList<>();
        ArrayList<Editorial> editoriales = new ArrayList<>();
        ArrayList<Libro> libros = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();

        autores.add(new Autor("J.K. Rowling"));
        autores.add(new Autor("George R.R. Martin"));
        autores.add(new Autor("J.R.R. Tolkien"));
        autores.add(new Autor("Isaac Asimov"));
        autores.add(new Autor("Ernest Hemingway"));
        autores.add(new Autor("Agatha Christie"));
        autores.add(new Autor("Stephen King"));
        autores.add(new Autor("F. Scott Fitzgerald"));
        autores.add(new Autor("Orson Scott Card"));

        editoriales.add(new Editorial("Penguin"));
        editoriales.add(new Editorial("Random House"));
        editoriales.add(new Editorial("HarperCollins"));
        editoriales.add(new Editorial("Scholastic"));
        editoriales.add(new Editorial("Simon & Schuster"));
        editoriales.add(new Editorial("Hachette"));
        editoriales.add(new Editorial("Doubleday"));

        libros.add(new Libro("Harry Potter and the Sorcerer's Stone", 1997, autores.get(0), editoriales.get(3)));
        libros.add(new Libro("A Game of Thrones", 1996, autores.get(1), editoriales.get(0)));
        libros.add(new Libro("The Hobbit", 1937, autores.get(2), editoriales.get(1)));
        libros.add(new Libro("Foundation", 1951, autores.get(3), editoriales.get(2)));
        libros.add(new Libro("The Old Man and the Sea", 1952, autores.get(4), editoriales.get(4)));
        libros.add(new Libro("Harry Potter and the Chamber of Secrets", 1998, autores.get(0), editoriales.get(3)));
        libros.add(new Libro("A Clash of Kings", 1998, autores.get(1), editoriales.get(0)));
        libros.add(new Libro("The Fellowship of the Ring", 1954, autores.get(2), editoriales.get(1)));
        libros.add(new Libro("Foundation and Empire", 1952, autores.get(3), editoriales.get(2)));
        libros.add(new Libro("For Whom the Bell Tolls", 1940, autores.get(4), editoriales.get(4)));
        libros.add(new Libro("Harry Potter and the Prisoner of Azkaban", 1999, autores.get(0), editoriales.get(3)));
        libros.add(new Libro("A Storm of Swords", 2000, autores.get(1), editoriales.get(0)));
        libros.add(new Libro("The Two Towers", 1954, autores.get(2), editoriales.get(1)));
        libros.add(new Libro("Second Foundation", 1953, autores.get(3), editoriales.get(2)));
        libros.add(new Libro("A Farewell to Arms", 1929, autores.get(4), editoriales.get(4)));
        libros.add(new Libro("Murder on the Orient Express", 1934, autores.get(5), editoriales.get(5)));
        libros.add(new Libro("It", 1986, autores.get(6), editoriales.get(5)));
        libros.add(new Libro("The Great Gatsby", 1925, autores.get(7), editoriales.get(4)));
        libros.add(new Libro("Ender's Game", 1985, autores.get(8), editoriales.get(0)));
        libros.add(new Libro("Harry Potter and the Goblet of Fire", 2000, autores.get(0), editoriales.get(3)));
        libros.add(new Libro("A Feast for Crows", 2005, autores.get(1), editoriales.get(0)));
        libros.add(new Libro("The Return of the King", 1955, autores.get(2), editoriales.get(1)));
        libros.add(new Libro("Robots and Empire", 1985, autores.get(3), editoriales.get(2)));
        libros.add(new Libro("To Have and Have Not", 1937, autores.get(4), editoriales.get(4)));
        libros.add(new Libro("And Then There Were None", 1939, autores.get(5), editoriales.get(5)));
        libros.add(new Libro("The Shining", 1977, autores.get(6), editoriales.get(6)));
        libros.add(new Libro("Tender Is the Night", 1934, autores.get(7), editoriales.get(4)));
        libros.add(new Libro("Speaker for the Dead", 1986, autores.get(8), editoriales.get(0)));
        libros.add(new Libro("Harry Potter and the Order of the Phoenix", 2003, autores.get(0), editoriales.get(3)));
        libros.add(new Libro("A Dance with Dragons", 2011, autores.get(1), editoriales.get(0)));

        clientes.add(new Cliente(80234678, "Juan", "Duarte"));
        clientes.add(new Cliente(10468596, "Laura", "Rodríguez"));

        autores.forEach(autorService::saveAutor);
        editoriales.forEach(editorialService::saveEditorial);
        libros.forEach(libroService::saveLibro);
        clientes.forEach(clienteService::saveCliente);
    }

}
