/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ProyectoGrupo6.servic;

import ProyectoGrupo6.dto.PagoDto;

/**
 *
 * @author Armando Elizondo M
 */
public interface FacturaService {

    String generarFactura(PagoDto pagoDto, String numeroFactura);

    String generarNumeroFactura();
}
