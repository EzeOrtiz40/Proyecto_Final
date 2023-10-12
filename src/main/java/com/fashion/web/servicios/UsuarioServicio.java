package com.fashion.web.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.enums.Rol;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public Usuario agregar(String nombre, String apellido, String email, String password,Imagen imagen) throws Exceptiones{
        
        validarCampos(nombre, apellido, email, password);

        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setFecha_creacion(new Date());
        usuario.setImagen(imagen); 
        usuario.setRol(Rol.USER);

        return usuarioRepositorio.save(usuario);
        

    }

    public void validarCampos(String nombre, String apellido, String email, String password) throws Exceptiones{

         if( nombre == null ||nombre.trim().isEmpty()){
            throw new Exceptiones("El nombre no puede estar vacio o ser null");
        }

        if(apellido == null ||apellido.trim().isEmpty()){
            throw new Exceptiones("El apellido no puede estar vacio o ser null");
        }

        if(apellido == null || email.trim().isEmpty()){
            throw new Exceptiones("El email no puede estar vacio o ser null");
        }

        if(password == null ||password.trim().isEmpty()){
            throw new Exceptiones("El password no puede estar vacio o ser null");
        }
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepositorio.buscarPorId(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Usuario buscarPorEmail(String email){
        return usuarioRepositorio.buscarEmail(email);
    }

    public List<Usuario> buscarPorNombre(String nombre){
        List<Usuario> listaNombres = usuarioRepositorio.buscarNombre(nombre);
        return listaNombres;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarEmail(email);

        if(usuario != null){
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("RELE_" + usuario.getRol().toString());
            permisos.add(p);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        }else{
            return null;
        }
    }
}

