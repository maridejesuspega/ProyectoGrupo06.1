package ProyectoGrupo6.controller;

import ProyectoGrupo6.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TiendaController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/tienda")
    public String tienda(Model model) {
        var productos = productoService.listarProductos();
        model.addAttribute("productos", productos);
        return "tienda";
    }
}