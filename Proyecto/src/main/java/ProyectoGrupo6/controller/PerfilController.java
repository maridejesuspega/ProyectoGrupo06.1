/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.controller;


import ProyectoGrupo6.domain.Usuario;
import ProyectoGrupo6.servic.UsuarioService;
import ProyectoGrupo6.servic.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Armando Elizondo M
 */
@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String mostrarPerfil(Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioService.getUsuarioPorUsername(auth.getName());
            
            if (usuario == null) {
                return "redirect:/login";
            }
            
            model.addAttribute("usuario", usuario);
            return "perfil";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el perfil: " + e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarPerfil(@ModelAttribute Usuario usuario,
                                  @RequestParam(required = false) MultipartFile imagenFile,
                                  @RequestParam(required = false) String newPassword,
                                  RedirectAttributes redirectAttributes) {
        try {
            // Obtener usuario actual
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuarioActual = usuarioService.getUsuarioPorUsername(auth.getName());
            
            if (usuarioActual == null) {
                redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
                return "redirect:/login";
            }
            
            // Actualizar campos básicos
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setApellidos(usuario.getApellidos());
            usuarioActual.setTelefono(usuario.getTelefono());
            
            // Actualizar imagen si se proporciona una nueva
            if (imagenFile != null && !imagenFile.isEmpty()) {
                String rutaImagen = firebaseStorageService.cargaImagen(
                    imagenFile, 
                    "usuario", 
                    usuarioActual.getIdUsuario()
                );
                usuarioActual.setRutaImagen(rutaImagen);
            }
            
            // Actualizar contraseña si se proporciona una nueva
            if (newPassword != null && !newPassword.isEmpty()) {
                if (newPassword.length() < 8) {
                    redirectAttributes.addFlashAttribute("error", "La contraseña debe tener al menos 8 caracteres");
                    return "redirect:/perfil";
                }
                usuarioActual.setPassword(passwordEncoder.encode(newPassword));
            }
            
            // Guardar cambios
            usuarioService.save(usuarioActual, false);
            
            redirectAttributes.addFlashAttribute("success", "Perfil actualizado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el perfil: " + e.getMessage());
        }
        
        return "redirect:/perfil";
    }
}