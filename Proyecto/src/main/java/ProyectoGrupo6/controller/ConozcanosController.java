package ProyectoGrupo6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Kenneth
 */
@Controller
public class ConozcanosController {

    @GetMapping("/conozcanos")
    public String servicios(Model model) {
        return "conozcanos";
    }
}
