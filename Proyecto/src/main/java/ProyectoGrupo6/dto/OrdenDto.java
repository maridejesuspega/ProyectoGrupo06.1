/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.dto;

import ProyectoGrupo6.domain.ItemCarrito;
import lombok.Data;
import java.util.List;

/**
 *
 * @author Armando Elizondo M
 */
@Data
public class OrdenDto {

    private Long orderId;
    private String customerName;
    private String customerEmail;
    private List<ItemCarrito> items;
    private double total;
    private String status;
    private String paymentId;
}
