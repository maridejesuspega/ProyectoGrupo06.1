package ProyectoGrupo6.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    private String descripcion;
    private boolean activo;
    private String rutaImagen;

    public Categoria() {
    }

    public Categoria(String descripcion, boolean activo, String rutaImagen) {
        this.descripcion = descripcion;
        this.activo = activo;
        this.rutaImagen = rutaImagen;
    }
}
