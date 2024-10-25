package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="Arbol")
public class Arbol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="nombre_comun", length=50)
    private String nombreComun;
    
    @Column(name="tipo_flor", length=50)
    private String tipoFlor;
    
    @Column(name="dureza_madera", length=50)
    private String durezaMadera;
    
    @Column(name="altura_aproximada", precision=5, scale=2)
    private Double alturaAproximada;
    
    @Column(name="ruta_imagen", columnDefinition="TEXT")
    private String rutaImagen;

    public Arbol() {
    }

    public Arbol(String nombreComun, String tipoFlor, String durezaMadera, Double alturaAproximada, String rutaImagen) {
        this.nombreComun = nombreComun;
        this.tipoFlor = tipoFlor;
        this.durezaMadera = durezaMadera;
        this.alturaAproximada = alturaAproximada;
        this.rutaImagen = rutaImagen;
    }
}
