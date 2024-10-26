/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
/**
 *
 * @author Maria
 */
@Controller
public class ArbolController {

    @GetMapping("/arboles")
    public String listarArboles(Model model) {
        List<ArbolController> arboles = // obtener la lista de árboles desde tu servicio o repositorio
                model.addAttribute("arboles", arboles);
        return "index"; // Asegúrate de que "index" sea el nombre de tu archivo HTML sin la extensión
    }
}
