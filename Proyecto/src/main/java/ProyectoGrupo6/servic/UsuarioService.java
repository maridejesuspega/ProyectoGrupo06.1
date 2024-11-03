/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ProyectoGrupo6.servic;

import ProyectoGrupo6.domain.Usuario;
import java.util.List;

/**
 *
 * @author Armando Elizondo M
 */


public interface UsuarioService {
    public List<Usuario> listarUsuarios();
    public void guardar(Usuario usuario);
    public void eliminar(Usuario usuario);
    public Usuario encontrarUsuario(Usuario usuario);
    public Usuario encontrarPorEmail(String email);
}