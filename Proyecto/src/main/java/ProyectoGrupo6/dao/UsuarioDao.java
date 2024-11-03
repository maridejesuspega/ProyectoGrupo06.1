/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ProyectoGrupo6.dao;

import ProyectoGrupo6.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Armando Elizondo M
 */
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
