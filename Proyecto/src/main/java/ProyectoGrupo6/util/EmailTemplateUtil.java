/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.util;

import ProyectoGrupo6.domain.ItemCarrito;
import java.util.List;

public class EmailTemplateUtil {
    
    public static String createClientEmailTemplate(List<ItemCarrito> items, double total) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(createEmailHeader("¡Gracias por tu compra!"));
        mensaje.append("<p>Tu pedido ha sido confirmado. Aquí está el detalle de tu compra:</p>");
        mensaje.append(createOrderTable(items));
        mensaje.append(createTotalSection(total));
        mensaje.append("<p>Gracias por confiar en VidaAnimal.</p>");
        return mensaje.toString();
    }
    
    public static String createVetEmailTemplate(List<ItemCarrito> items, double total, String emailCliente) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(createEmailHeader("Nueva Venta Realizada"));
        mensaje.append("<p>Se ha realizado una nueva venta con los siguientes detalles:</p>");
        mensaje.append("<p><strong>Cliente:</strong> ").append(emailCliente).append("</p>");
        mensaje.append(createOrderTable(items));
        mensaje.append(createTotalSection(total));
        return mensaje.toString();
    }
    
    private static String createEmailHeader(String title) {
        return "<h2>" + title + "</h2>";
    }
    
    private static String createOrderTable(List<ItemCarrito> items) {
        StringBuilder table = new StringBuilder();
        table.append("<table style='width:100%; border-collapse: collapse;'>");
        table.append(createTableHeader());
        table.append(createTableBody(items));
        table.append("</table>");
        return table.toString();
    }
    
    private static String createTableHeader() {
        return "<tr>" +
               "<th style='border:1px solid #ddd; padding:8px;'>Producto</th>" +
               "<th style='border:1px solid #ddd; padding:8px;'>Cantidad</th>" +
               "<th style='border:1px solid #ddd; padding:8px;'>Precio</th>" +
               "<th style='border:1px solid #ddd; padding:8px;'>Subtotal</th>" +
               "</tr>";
    }
    
    private static String createTableBody(List<ItemCarrito> items) {
        StringBuilder body = new StringBuilder();
        for (ItemCarrito item : items) {
            body.append("<tr>");
            body.append("<td style='border:1px solid #ddd; padding:8px;'>").append(item.getNombre()).append("</td>");
            body.append("<td style='border:1px solid #ddd; padding:8px;'>").append(item.getCantidad()).append("</td>");
            body.append("<td style='border:1px solid #ddd; padding:8px;'>₡").append(String.format("%,.0f", item.getPrecio())).append("</td>");
            body.append("<td style='border:1px solid #ddd; padding:8px;'>₡").append(String.format("%,.0f", item.getSubtotal())).append("</td>");
            body.append("</tr>");
        }
        return body.toString();
    }
    
    private static String createTotalSection(double total) {
        return "<p><strong>Total: ₡" + String.format("%,.0f", total) + "</strong></p>";
    }
}