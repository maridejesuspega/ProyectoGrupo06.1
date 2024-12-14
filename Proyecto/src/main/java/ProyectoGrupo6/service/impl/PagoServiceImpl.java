/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.service.impl;

import ProyectoGrupo6.domain.ItemCarrito;
import ProyectoGrupo6.servic.CorreoService;
import ProyectoGrupo6.servic.PagoService;
import ProyectoGrupo6.util.EmailTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Armando Elizondo M
 */


@Service
@Slf4j
public class PagoServiceImpl implements PagoService {

    @Autowired
    private CorreoService correoService;

    @Override
    public void procesarPago(List<ItemCarrito> items, double total, String emailCliente) throws Exception {
        // Aquí iría la lógica de procesamiento del pago con una pasarela de pagos
        
        try {
            // Enviar correo al cliente
            String mensajeCliente = EmailTemplateUtil.createClientEmailTemplate(items, total);
            correoService.enviarCorreoHtml(emailCliente, "Confirmación de Compra - VidaAnimal", mensajeCliente);
            
            // Enviar correo a la veterinaria
            String mensajeVeterinaria = EmailTemplateUtil.createVetEmailTemplate(items, total, emailCliente);
            correoService.enviarCorreoHtml("diana-b-14@vidaanimal.com", "Nueva Venta Realizada", mensajeVeterinaria);
        } catch (Exception e) {
            throw new Exception("Error al enviar los correos de confirmación: " + e.getMessage());
        }
    }
}