package ProyectoGrupo6.controller;

import ProyectoGrupo6.servic.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String tienda(
            Model model, 
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String busqueda) {
        
        // Obtener todos los productos y categorías
        var productos = productoService.buscarProductos(busqueda, categoria);
        var categorias = productoService.listarCategorias();
        
        // Agregar atributos al modelo
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaSeleccionada", categoria);
        model.addAttribute("busqueda", busqueda != null ? busqueda : "");
        
        return "tienda";
    }
}