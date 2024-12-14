/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ProyectoGrupo6.servic;

import ProyectoGrupo6.dto.PagoDto;
import jakarta.mail.MessagingException;

/**
 *
 * @author Armando Elizondo M
 */
public interface NotificacionService {

    void enviarConfirmacionCliente(PagoDto pagoDto, String numeroFactura, String contenidoFactura) throws MessagingException;

    void enviarNotificacionVeterinaria(PagoDto pagoDto, String numeroFactura, String contenidoFactura) throws MessagingException;
}
