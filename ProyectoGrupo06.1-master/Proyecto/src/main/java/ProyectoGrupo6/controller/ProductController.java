package ProyectoGrupo6.controller;

import ProyectoGrupo6.domain.Producto;
import ProyectoGrupo6.servic.CategoriaService;
import ProyectoGrupo6.servic.ProductoService;
import ProyectoGrupo6.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.listarProductos();
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalProductos", productos.size());
        return "/producto/listado";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto, Model model) {
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", new Producto());
        return "/producto/modificar";
    }

    @PostMapping("/guardar")
    public String productoGuardar(Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            productoService.guardar(producto);
            producto.setRutaImagen(
                firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto())
            );
        }
        productoService.guardar(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.eliminar(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model) {
        producto = productoService.encontrarProducto(producto);
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);
        return "/producto/modificar";
    }
}