package ProyectoGrupo6.dao;

import ProyectoGrupo6.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductoDao extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(String categoria);
    
    @Query("SELECT DISTINCT p.categoria FROM Producto p")
    List<String> findDistinctCategorias();
    
    @Query("SELECT p FROM Producto p WHERE " +
           "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Producto> buscarProductos(@Param("busqueda") String busqueda);
    
    @Query("SELECT p FROM Producto p WHERE " +
           "p.categoria = :categoria AND " +
           "(LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :busqueda, '%')))")
    List<Producto> buscarProductosPorCategoriaYTexto(
        @Param("categoria") String categoria, 
        @Param("busqueda") String busqueda
    );
}