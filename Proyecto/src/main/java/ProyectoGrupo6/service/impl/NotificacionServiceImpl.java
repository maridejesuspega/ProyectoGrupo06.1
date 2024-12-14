/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.service.impl;

import ProyectoGrupo6.dto.PagoDto;
import ProyectoGrupo6.servic.NotificacionService;
import ProyectoGrupo6.servic.CorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;

/**
 *
 * @author Armando Elizondo M
 */


@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private CorreoService correoService;

    @Override
    public void enviarConfirmacionCliente(PagoDto pagoDto, String numeroFactura, String contenidoFactura) 
            throws MessagingException {
        String asunto = "Confirmación de compra - Vida Animal";
        String contenido = generarContenidoCorreoCliente(numeroFactura, contenidoFactura);
        correoService.enviarCorreoHtml(pagoDto.getCorreoCliente(), asunto, contenido);
    }

    @Override
    public void enviarNotificacionVeterinaria(PagoDto pagoDto, String numeroFactura, String contenidoFactura) 
            throws MessagingException {
        String asunto = "Nueva venta realizada - Vida Animal";
        String contenido = generarContenidoCorreoVeterinaria(pagoDto, numeroFactura, contenidoFactura);
        correoService.enviarCorreoHtml("diana-b-14@vidaanimal.com", asunto, contenido);
    }

    private String generarContenidoCorreoCliente(String numeroFactura, String contenidoFactura) {
        StringBuilder contenido = new StringBuilder();
        contenido.append("<div style='font-family: Arial, sans-serif;'>");
        contenido.append("<h2 style='color: #8C207A;'>¡Gracias por tu compra!</h2>");
        contenido.append("<p>Tu pedido ha sido procesado exitosamente.</p>");
        contenido.append("<p>Número de factura: <strong>").append(numeroFactura).append("</strong></p>");
        contenido.append("<div style='margin-top: 20px;'>");
        contenido.append(contenidoFactura);
        contenido.append("</div>");
        contenido.append("</div>");
        return contenido.toString();
    }

    private String generarContenidoCorreoVeterinaria(PagoDto pagoDto, String numeroFactura, String contenidoFactura) {
        StringBuilder contenido = new StringBuilder();
        contenido.append("<div style='font-family: Arial, sans-serif;'>");
        contenido.append("<h2 style='color: #8C207A;'>Nueva venta realizada</h2>");
        contenido.append("<p>Se ha procesado una nueva venta con los siguientes detalles:</p>");
        contenido.append("<p>Cliente: ").append(pagoDto.getCorreoCliente()).append("</p>");
        contenido.append("<div style='margin-top: 20px;'>");
        contenido.append(contenidoFactura);
        contenido.append("</div>");
        contenido.append("</div>");
        return contenido.toString();
    }
}