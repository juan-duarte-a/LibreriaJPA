package libreria.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;
    
    private LocalDate fechaDevolucion;

    @OneToOne
    @JoinColumn(nullable = false)
    private Libro libro;

    @OneToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    public Prestamo() { }

    public Prestamo(LocalDate fechaPrestamo, Libro libro, Cliente cliente) {
        this.fechaPrestamo = fechaPrestamo;
        this.libro = libro;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Préstamo -> id: " + id + ", fecha préstamo: " + fechaPrestamo + ", fecha devolucion: " + fechaDevolucion
                + "\n" + libro + "\n" + cliente;
    }
    
}