package ProyectoGrupo6.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;
    
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    
    @Column(name = "ruta_imagen")
    private String rutaImagen;
    private String activo;
    private String categoria;
    
    public Producto() {
        // Default constructor
    }
    
    public Producto(Long idProducto) {
        this.idProducto = idProducto;
    }
    
    public boolean tieneExistencias() {
        return this.cantidad > 0;
    }
    
    public int getExistencias() {
        return this.cantidad;
    }
}