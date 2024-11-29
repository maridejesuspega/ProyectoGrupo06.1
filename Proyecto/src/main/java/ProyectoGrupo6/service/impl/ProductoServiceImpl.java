package ProyectoGrupo6.service.impl;

import ProyectoGrupo6.dao.ProductoDao;
import ProyectoGrupo6.domain.Producto;
import ProyectoGrupo6.servic.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    @Autowired
    private ProductoDao productoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
        return productoDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductosPorCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            return listarProductos();
        }
        return productoDao.findByCategoria(categoria);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<String> listarCategorias() {
        return productoDao.findDistinctCategorias();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarProductos(String busqueda, String categoria) {
        if (busqueda == null || busqueda.isEmpty()) {
            return categoria == null ? listarProductos() : listarProductosPorCategoria(categoria);
        }
        
        if (categoria == null || categoria.isEmpty()) {
            return productoDao.buscarProductos(busqueda);
        }
        
        return productoDao.buscarProductosPorCategoriaYTexto(categoria, busqueda);
    }
    
    @Override
    @Transactional
    public void guardar(Producto producto) {
        productoDao.save(producto);
    }
    
    @Override
    @Transactional
    public void eliminar(Producto producto) {
        productoDao.delete(producto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Producto encontrarProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }
}