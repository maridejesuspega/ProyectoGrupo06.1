package ProyectoGrupo6.controller;

import ProyectoGrupo6.domain.Producto;
import ProyectoGrupo6.servic.CarritoService;
import ProyectoGrupo6.servic.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String verCarrito(Model model) {
        model.addAttribute("items", carritoService.obtenerItems());
        model.addAttribute("total", carritoService.obtenerTotal());
        return "carrito";
    }

    @PostMapping("/agregar/{idProducto}")
    @ResponseBody
    public String agregarAlCarrito(@PathVariable Long idProducto) {
        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        producto = productoService.encontrarProducto(producto);
        if (producto != null && producto.tieneExistencias()) {
            carritoService.agregarProducto(producto);
            return "Producto agregado al carrito";
        }
        return "No se pudo agregar el producto";
    }

    @PostMapping("/actualizar/{idProducto}")
    public String actualizarCantidad(@PathVariable Long idProducto, @RequestParam int cantidad) {
        carritoService.actualizarCantidad(idProducto, cantidad);
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar/{idProducto}")  // Cambiado de DeleteMapping a PostMapping
    public String eliminarDelCarrito(@PathVariable Long idProducto) {
        carritoService.eliminarProducto(idProducto);
        return "redirect:/carrito";
    }
}
