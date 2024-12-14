/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.controller;

import ProyectoGrupo6.servic.CarritoService;
import ProyectoGrupo6.servic.PagoService;
import ProyectoGrupo6.domain.Usuario;
import ProyectoGrupo6.servic.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Armando Elizondo M
 */

@Controller
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;
    
    @Autowired
    private CarritoService carritoService;
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/checkout")
    public String mostrarCheckout(Model model) {
        model.addAttribute("items", carritoService.obtenerItems());
        model.addAttribute("total", carritoService.obtenerTotal());
        return "pago/checkout";
    }

    @PostMapping("/procesar")
    public String procesarPago(RedirectAttributes redirectAttributes) {
        try {
            // Obtener el usuario actual
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioService.getUsuarioPorUsername(auth.getName());
            
            if (usuario == null) {
                redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para realizar la compra");
                return "redirect:/carrito";
            }
            
            // Procesar el pago
            pagoService.procesarPago(
                carritoService.obtenerItems(),
                carritoService.obtenerTotal(),
                usuario.getCorreo()
            );
            
            // Limpiar el carrito
            carritoService.limpiarCarrito();
            
            // Agregar mensaje de éxito
            redirectAttributes.addFlashAttribute("success", 
                "¡Compra realizada con éxito! Se ha enviado un correo de confirmación a " + usuario.getCorreo());
            
            return "redirect:/carrito";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al procesar el pago: " + e.getMessage());
            return "redirect:/carrito";
        }
    }
}