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
    
    // Método de conveniencia para verificar existencias
    public boolean tieneExistencias() {
        return this.cantidad > 0;
    }
    
    // Método para obtener existencias
    public int getExistencias() {
        return this.cantidad;
    }
}