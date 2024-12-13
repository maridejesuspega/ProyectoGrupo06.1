package ProyectoGrupo6.servic;

import ProyectoGrupo6.domain.Producto;
import ProyectoGrupo6.domain.ItemCarrito;
import java.util.List;

public interface CarritoService {
    void agregarProducto(Producto producto);
    void eliminarProducto(Long idProducto);
    void actualizarCantidad(Long idProducto, int cantidad);
    List<ItemCarrito> obtenerItems();
    double obtenerTotal();
    int obtenerCantidadTotal();
    void limpiarCarrito();
}