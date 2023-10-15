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

import com.fashion.web.Enumeraciones.Rol;
import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public Usuario agregar(String nombre, String apellido, String email, String password, String password2, Imagen imagen) throws Exceptiones{
        
        validarCampos(nombre, apellido, email, password, password2);

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

    public void validarCampos(String nombre, String apellido, String email, String password, String password2) throws Exceptiones{

         if(nombre == null || nombre.trim().isEmpty()){
            throw new Exceptiones("El nombre no puede estar vacio o ser null");
        }

        if(apellido == null || apellido.trim().isEmpty()){
            throw new Exceptiones("El apellido no puede estar vacio o ser null");
        }

        if(email == null || email.trim().isEmpty()){
            throw new Exceptiones("El email no puede estar vacio o ser null");
        }

        if(password == null || password.trim().isEmpty() || password.length() <= 6){
            throw new Exceptiones("El password no puede estar vacio o ser null");
        }

        if(!password.equals(password2)){
            throw new Exceptiones("El password debe coincidir");
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
        Usuario usuario =  usuarioRepositorio.buscarEmail(email);
        
        if(usuario != null){
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ usuario.getRol());

            permisos.add(p);
            return  new User(usuario.getEmail(),usuario.getPassword(), permisos);
        }else{
            return null;
        }
    }
}
