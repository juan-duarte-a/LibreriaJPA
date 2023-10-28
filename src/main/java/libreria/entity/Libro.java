package libreria.entity;


import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String isbn;

    @Column(nullable = false, unique = true)
    private String titulo;

    private Integer anio;

    private Integer ejemplares;

    private Integer ejemplaresPrestados;

    private Integer ejemplaresRestantes;

    @Column(nullable = false)
    private boolean alta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Autor autor;

    @ManyToOne
    private Editorial editorial;

    public Libro() {
        this.alta = true;
    }

    public Libro(String titulo, Integer anio, Autor autor, Editorial editorial) {
        this();
        this.titulo = titulo;
        this.anio = anio;
        this.autor = autor;
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro:" + "\n" +
                " - ISBN: " + isbn + "\n" +
                " - Título: " + titulo + "\n" +
                " - Año: " + anio + "\n" +
                " - Autor: " + autor + "\n" +
                " - Editorial: " + editorial + "\n" +
                " - Ejemplares: " + ejemplares +
                ", ejemplares prestados: " + ejemplaresPrestados +
                ", ejemplares restantes: " + ejemplaresRestantes;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

}
