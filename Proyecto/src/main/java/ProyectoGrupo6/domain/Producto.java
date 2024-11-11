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
    @Column(name = "cantidad")
    private int existencias;
    private String rutaImagen;
    private boolean activo;
    private String categoria;
}