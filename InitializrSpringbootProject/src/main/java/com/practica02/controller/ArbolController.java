
package com.practica02.controller;

import com.practica02.domain.Arbol;
import com.practica02.service.ArbolService;
import com.practica02.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Maria
 */
@Controller
@RequestMapping("/categoria")
public class ArbolController {

    @Autowired
    private ArbolService arbolService;

    @Autowired
    private FirebaseStorageService firebaseStorageService; // Inyección del servicio Firebase

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = arbolService.getArbol(false); 
        model.addAttribute("categorias", lista);
        model.addAttribute("totalCategorias", lista.size());
        return "/categoria/listado"; 
    }

    @GetMapping("/nuevo")
    public String categoriaNuevo(Arbol arbol) {
        return "/categoria/modificar"; 
    }

    @PostMapping("/guardar")
    public String categoriaGuardar(Arbol arbol, @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            arbol.setRutaImagen(
                     firebaseStorageService.cargaImagen(imagenFile, "categoria", arbol.getId()));
        }
        arbolService.save(arbol);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String categoriaEliminar(@PathVariable("idCategoria") Long idCategoria) {
        Arbol arbol = new Arbol();
        arbol.setId(idCategoria);
        arbolService.delete(arbol);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(@PathVariable("idCategoria") Long idCategoria, Model model) {
        Arbol arbol = new Arbol();
        arbol.setId(idCategoria);
        arbol = arbolService.getArbol(arbol);
        model.addAttribute("categoria", arbol);
        return "/categoria/modificar"; 
    }
}
