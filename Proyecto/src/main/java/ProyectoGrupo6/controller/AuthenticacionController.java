/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.controller;

import ProyectoGrupo6.domain.Usuario;
import ProyectoGrupo6.servic.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * @author Armando Elizondo M
 */

@Controller
public class AuthenticacionController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("titulo", "Iniciar Sesión - VidaAnimal");
        return "authenticacion/login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("titulo", "Registro - VidaAnimal");
        model.addAttribute("usuario", new Usuario());
        return "authenticacion/registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
        if (usuarioService.encontrarPorEmail(usuario.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("error", "El email ya está registrado");
            return "redirect:/registro";
        }
        
        usuarioService.guardar(usuario);
        redirectAttributes.addFlashAttribute("success", "Registro exitoso. Por favor, inicia sesión");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUsuario(String email, String password, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioService.encontrarPorEmail(email);
        
        if (usuario != null && usuario.getPassword().equals(password)) {
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/";
        }
        
        redirectAttributes.addFlashAttribute("error", "Email o contraseña incorrectos");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("usuarioLogueado");
        return "redirect:/";
    }
}