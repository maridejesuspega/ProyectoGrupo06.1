package ProyectoGrupo6.service.impl;

import ProyectoGrupo6.domain.Producto;
import ProyectoGrupo6.domain.ItemCarrito;
import ProyectoGrupo6.servic.CarritoService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SessionScope
public class CarritoServiceImpl implements CarritoService {
    
    private final Map<Long, ItemCarrito> items = new HashMap<>();

    @Override
    public void agregarProducto(Producto producto) {
        ItemCarrito item = items.get(producto.getIdProducto());
        if (item != null) {
            item.incrementarCantidad();
        } else {
            items.put(producto.getIdProducto(), new ItemCarrito(producto));
        }
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        items.remove(idProducto);
    }

    @Override
    public void actualizarCantidad(Long idProducto, int cantidad) {
        ItemCarrito item = items.get(idProducto);
        if (item != null) {
            if (cantidad <= 0) {
                items.remove(idProducto);
            } else {
                item.setCantidad(cantidad);
            }
        }
    }

    @Override
    public List<ItemCarrito> obtenerItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    public double obtenerTotal() {
        return items.values().stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();
    }

    @Override
    public int obtenerCantidadTotal() {
        return items.values().stream()
                .mapToInt(ItemCarrito::getCantidad)
                .sum();
    }

    @Override
    public void limpiarCarrito() {
        items.clear();
    }
}