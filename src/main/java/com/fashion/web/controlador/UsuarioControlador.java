package com.fashion.web.controlador;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.servicios.ImagenServicio;
import com.fashion.web.servicios.UsuarioServicio;

@Controller
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ImagenServicio imagenServicio;

    @GetMapping("/buscar/{id}")
    public Usuario getUsuarioPorId(@PathVariable Long id){
        return usuarioServicio.buscarUsuarioPorId(id);
    }
    
    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios(){
        return usuarioServicio.listarUsuarios();
    }
    @GetMapping("/email/{email}")
    public Usuario getEmail(@PathVariable String email ){
        return usuarioServicio.buscarPorEmail(email);
    }
    @GetMapping("/nombre/{nombre}/lista")
    public List<Usuario> getNombres(@PathVariable String nombre){
        return usuarioServicio.buscarPorNombre(nombre);
    }

    @GetMapping("/{id}/imagen")
    public ResponseEntity<byte[]> getImagen(@PathVariable Long id) {
        Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);

        byte[] imagen = usuario.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(imagen, headers, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public List<Usuario> listaUsuarios() {
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "usuario_form";
    }

    @PostMapping("/agregar")
    public String agregarUsuario(@RequestParam String nombre,
                                @RequestParam String apellido,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam MultipartFile archivo, ModelMap model) 
    {
        System.out.println(nombre);
        System.out.println(apellido);
        System.out.println(email);
        System.out.println(password);
        System.out.println(archivo.getName());
        try {

           Imagen imagen = imagenServicio.guardar(archivo);
            usuarioServicio.agregar(nombre, apellido, email, password, imagen);

            model.put("Exito", "Usuario creado correctamente!");
            return "redirect:../usuario/login";
        } catch (Exceptiones e) {
            model.put("Error", "No se pudo crear el usuario, revise sus datos neuvamente!");
            return "usuario_form";
        }
        
    }
}
    
   


