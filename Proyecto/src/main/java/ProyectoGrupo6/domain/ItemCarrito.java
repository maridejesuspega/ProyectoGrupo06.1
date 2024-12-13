package ProyectoGrupo6.domain;

import lombok.Data;

@Data
public class ItemCarrito {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private String rutaImagen;
    private int cantidad;

    public ItemCarrito() {
    }

    public ItemCarrito(Producto producto) {
        this.idProducto = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.rutaImagen = producto.getRutaImagen();
        this.cantidad = 1;
    }

    public double getSubtotal() {
        return this.precio * this.cantidad;
    }

    public void incrementarCantidad() {
        this.cantidad++;
    }
}