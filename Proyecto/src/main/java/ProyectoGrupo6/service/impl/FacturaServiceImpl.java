/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.service.impl;

import ProyectoGrupo6.dto.PagoDto;
import ProyectoGrupo6.servic.FacturaService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 *
 * @author Armando Elizondo M
 */
@Service
public class FacturaServiceImpl implements FacturaService {

    @Override
    public String generarFactura(PagoDto pagoDto, String numeroFactura) {
        StringBuilder factura = new StringBuilder();
        factura.append("<div style='font-family: Arial, sans-serif;'>");
        factura.append("<h2 style='color: #8C207A;'>Factura #").append(numeroFactura).append("</h2>");
        factura.append("<p>Fecha: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("</p>");
        factura.append("<h3 style='color: #9BBF63;'>Detalle de compra:</h3>");
        factura.append("<table style='width: 100%; border-collapse: collapse;'>");
        factura.append("<tr style='background-color: #f8f9fa;'>");
        factura.append("<th style='padding: 10px; border: 1px solid #dee2e6;'>Producto</th>");
        factura.append("<th style='padding: 10px; border: 1px solid #dee2e6;'>Cantidad</th>");
        factura.append("<th style='padding: 10px; border: 1px solid #dee2e6;'>Precio</th>");
        factura.append("<th style='padding: 10px; border: 1px solid #dee2e6;'>Subtotal</th></tr>");
        
        pagoDto.getItems().forEach(item -> {
            factura.append("<tr>");
            factura.append("<td style='padding: 10px; border: 1px solid #dee2e6;'>").append(item.getNombre()).append("</td>");
            factura.append("<td style='padding: 10px; border: 1px solid #dee2e6;'>").append(item.getCantidad()).append("</td>");
            factura.append("<td style='padding: 10px; border: 1px solid #dee2e6;'>₡").append(String.format("%,.2f", item.getPrecio())).append("</td>");
            factura.append("<td style='padding: 10px; border: 1px solid #dee2e6;'>₡").append(String.format("%,.2f", item.getSubtotal())).append("</td>");
            factura.append("</tr>");
        });
        
        factura.append("</table>");
        factura.append("<p style='text-align: right; margin-top: 20px;'><strong>Total: ₡").append(String.format("%,.2f", pagoDto.getTotal())).append("</strong></p>");
        factura.append("</div>");
        
        return factura.toString();
    }

    @Override
    public String generarNumeroFactura() {
        return "FA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}