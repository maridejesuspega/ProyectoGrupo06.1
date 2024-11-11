package ProyectoGrupo6.service;

import ProyectoGrupo6.domain.Producto;
import java.util.List;

public interface ProductoService {
    public List<Producto> listarProductos();
    public void guardar(Producto producto);
    public void eliminar(Producto producto);
    public Producto encontrarProducto(Producto producto);
}