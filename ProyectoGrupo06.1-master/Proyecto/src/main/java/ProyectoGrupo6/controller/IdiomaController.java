/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.controller;

import java.util.Locale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Kenneth
 */

public class IdiomaController {

    @GetMapping("/")
    public String redirigirAlInicio() {
        return "redirect:/tienda";
    }

    @GetMapping("/tienda")
    public String tienda() {
        return "tienda"; // Nombre del archivo HTML de la vista
    }
    @ModelAttribute("locale")
    public Locale locale(Locale locale) {
        return locale;
    }
}

