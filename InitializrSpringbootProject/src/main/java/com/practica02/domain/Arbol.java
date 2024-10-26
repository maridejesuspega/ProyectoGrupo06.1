/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica02.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Maria
 */
@Data
@Entity
@Table(name = "Arbol")
public class Arbol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String nombreComun;
    private String tipoFlor;
    private String durezaMadera;
    private int alturaPromedio;

    @Column(name = "imagen_ruta")
    private String imagenRuta;

    public Arbol() {
    }

    public Arbol(long id, String nombreComun, String tipoFlor, String durezaMadera, int alturaPromedio, String imagenRuta) {
        this.id = id;
        this.nombreComun = nombreComun;
        this.tipoFlor = tipoFlor;
        this.durezaMadera = durezaMadera;
        this.alturaPromedio = alturaPromedio;
        this.imagenRuta = imagenRuta;
    }

    
}
