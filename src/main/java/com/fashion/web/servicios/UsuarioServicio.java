package com.fashion.web.servicios;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public Usuario agregar(String nombre, String apellido, String email, String password, Imagen imagen) throws Exceptiones{
        
        validarCampos(nombre, apellido, email, password);

        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setFecha_creacion(new Date());
        usuario.setImagen(imagen); 

        return usuarioRepositorio.save(usuario);
        

    }

    public void validarCampos(String nombre, String apellido, String email, String password) throws Exceptiones{

         if(nombre.trim().isEmpty() || nombre == null){
            throw new Exceptiones("El nombre no puede estar vacio o ser null");
        }

        if(apellido.trim().isEmpty() || apellido == null){
            throw new Exceptiones("El apellido no puede estar vacio o ser null");
        }

        if(email.trim().isEmpty() || email == null){
            throw new Exceptiones("El email no puede estar vacio o ser null");
        }

        if(password.trim().isEmpty() || password == null){
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
}
