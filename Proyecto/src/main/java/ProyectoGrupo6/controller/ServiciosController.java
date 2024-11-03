/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.controller;

/**
 *
 * @author Maria
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ServiciosController {

    @GetMapping("/servicios")
    public String servicios(Model model) {
        return "servicios";
    }
    
    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("titulo", "Contacto - VidaAnimal");
        return "contacto";
    }

    @PostMapping("/contacto")
    public String procesarContacto(@RequestParam String nombre,
                                 @RequestParam String email,
                                 @RequestParam String telefono,
                                 @RequestParam String asunto,
                                 @RequestParam String mensaje,
                                 RedirectAttributes redirectAttributes) {
        // Aquí puedes agregar la lógica para procesar el formulario
        // Por ejemplo, enviar un email o guardar en base de datos
        
        redirectAttributes.addFlashAttribute("mensaje", "Gracias por contactarnos. Te responderemos pronto.");
        return "redirect:/contacto";
    }
    
    
}
