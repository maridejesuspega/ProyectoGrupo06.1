package ProyectoGrupo6.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private String rutaImagen;
    private String categoria;
    private boolean activo;
}
