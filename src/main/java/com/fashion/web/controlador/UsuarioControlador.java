package com.fashion.web.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.servicios.ImagenServicio;
import com.fashion.web.servicios.UsuarioServicio;

@Controller
// @RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ImagenServicio imagenServicio;

    @GetMapping("/buscar/{id}")
    public Usuario getUsuarioPorId(@PathVariable Long id) {
        return usuarioServicio.buscarUsuarioPorId(id);
    }

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/email/{email}")
    public Usuario getEmail(@PathVariable String email) {
        return usuarioServicio.buscarPorEmail(email);
    }

    @GetMapping("/nombre/{nombre}/lista")
    public List<Usuario> getNombres(@PathVariable String nombre) {
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
    public String lista(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_list";
    }

    //  @GetMapping("/registrar")
    //  public String registrar() {
    //      return "usuario_form";
    //  }

    @PostMapping("/registro")
    public String agregarUsuario(@RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password2,
            @RequestParam(required = false) MultipartFile archivo, ModelMap model) {
        
            Imagen imagen;
        try {
            
            Imagen guest_imagen = usuarioServicio.buscarPorEmail("guest@test.com").getImagen();

            if(archivo.getContentType().equals("application/octet-stream")) {
                imagen = imagenServicio.guardarImagen(new Imagen(guest_imagen.getMime()
                                                                , guest_imagen.getNombre()
                                                                , guest_imagen.getContenido()));
                System.out.println(imagen.getMime()+ "  Estoy en el if ");
            }
            
            else{

              imagen = imagenServicio.guardar(archivo);
                System.out.println("Estoy en el else");
            }
            
            usuarioServicio.agregar(nombre, apellido, email, password,password2, imagen);
            model.put("exito", "Usuario creado correctamente");

            return "login";

        } catch (Exceptiones e) {
            model.put("error", e.getMessage());
            model.put("nombre", nombre);
            model.put("apellido", apellido);
            model.put("email", email);
            
            //return "redirect:/usuario/registrar";
            return "usuario_form";
        }

    }
}