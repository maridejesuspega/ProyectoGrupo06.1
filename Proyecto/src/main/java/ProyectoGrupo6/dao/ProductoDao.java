package ProyectoGrupo6.dao;

import ProyectoGrupo6.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Long> {
    // Métodos personalizados si son necesarios
}