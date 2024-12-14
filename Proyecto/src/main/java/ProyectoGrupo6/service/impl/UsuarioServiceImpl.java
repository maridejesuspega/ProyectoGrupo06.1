/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.service.impl;

import ProyectoGrupo6.dao.RolDao;
import ProyectoGrupo6.dao.UsuarioDao;
import ProyectoGrupo6.domain.Rol;
import ProyectoGrupo6.domain.Usuario;
import ProyectoGrupo6.servic.UsuarioService;
import ProyectoGrupo6.util.ValidationUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.findByUsernameOrCorreo(username, correo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.existsByUsernameOrCorreo(username, correo);
    }

    @Override
    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        // Validar campos obligatorios
        validarUsuario(usuario);

        // Guardar usuario
        usuario = usuarioDao.save(usuario);

        // Crear rol por defecto si es necesario
        if (crearRolUser) {
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol.setIdUsuario(usuario.getIdUsuario());
            rolDao.save(rol);
        }
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    /**
     * Valida los datos del usuario antes de guardarlos.
     *
     * @param usuario Usuario a validar.
     */
    private void validarUsuario(Usuario usuario) {
        if (!ValidationUtil.isValidName(usuario.getNombre()) || 
            !ValidationUtil.isValidName(usuario.getApellidos())) {
            throw new IllegalArgumentException("El nombre y apellidos son obligatorios");
        }

        if (usuario.getTelefono() != null && !usuario.getTelefono().isEmpty() && 
            !ValidationUtil.isValidPhoneNumber(usuario.getTelefono())) {
            throw new IllegalArgumentException("El número de teléfono debe tener 8 dígitos");
        }
    }
}
