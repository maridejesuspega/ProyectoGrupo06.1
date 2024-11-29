package ProyectoGrupo6.servic;

import ProyectoGrupo6.domain.Producto;
import java.util.List;

public interface ProductoService {
    public List<Producto> listarProductos();
    public List<Producto> listarProductosPorCategoria(String categoria);
    public List<String> listarCategorias();
    public List<Producto> buscarProductos(String busqueda, String categoria);
    public void guardar(Producto producto);
    public void eliminar(Producto producto);
    public Producto encontrarProducto(Producto producto);
}