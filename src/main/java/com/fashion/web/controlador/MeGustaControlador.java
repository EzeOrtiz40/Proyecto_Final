package com.fashion.web.controlador;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.servicios.MegustaServicio;
import com.fashion.web.servicios.PublicacionServicio;

//@Controller
@RestController
@RequestMapping("/likes")
public class MeGustaControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;
    @Autowired
    private MegustaServicio megustaServicio;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/like/{id}")
    public ResponseEntity<Map<String, Object>> darLikes(@PathVariable("id") Long id, HttpSession session) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            Publicacion publicacion = publicacionServicio.getById(id);

            if (megustaServicio.validarLike(publicacion, usuario)) {
                megustaServicio.crearLike(publicacion, usuario);

                // Crear una respuesta JSON
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Like agregado correctamente");

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // Crear una respuesta JSON
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Ya diste like a esta publicación");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            // Crear una respuesta JSON
            Map<String, Object> response = new HashMap<>();
            response.put("message", "No se pudo");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
