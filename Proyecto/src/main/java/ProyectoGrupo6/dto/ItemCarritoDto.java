/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.dto;

import lombok.Data;

/**
 *
 * @author Armando Elizondo M
 */
@Data
public class ItemCarritoDto {

    private Long id;
    private String nombre;
    private int cantidad;
    private double precio;
    private double subtotal;

}
