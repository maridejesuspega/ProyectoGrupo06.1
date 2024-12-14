/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.dto;

import java.util.List;

/**
 *
 * @author Armando Elizondo M
 */
   public class PagoDto {
    private List<ItemCarritoDto> items;
    private double total;
    private DatosPagoDto datosPago;
    private String correoCliente;

    public List<ItemCarritoDto> getItems() {
        return items;
    }

    public void setItems(List<ItemCarritoDto> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public DatosPagoDto getDatosPago() {
        return datosPago;
    }

    public void setDatosPago(DatosPagoDto datosPago) {
        this.datosPago = datosPago;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }
    
}

