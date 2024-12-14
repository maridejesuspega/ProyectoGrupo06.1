/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ProyectoGrupo6.servic;

import ProyectoGrupo6.domain.ItemCarrito;
import java.util.List;

/**
 *
 * @author Armando Elizondo M
 */
public interface PagoService {

    void procesarPago(List<ItemCarrito> items, double total, String emailCliente) throws Exception;
}
