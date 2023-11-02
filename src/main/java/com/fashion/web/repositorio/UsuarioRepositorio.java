package com.fashion.web.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fashion.web.entidades.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    public Usuario buscarPorId(@Param("id") Long id);

    @Query("SELECT n FROM Usuario n WHERE n.nombre = :nombre")
    public List<Usuario> buscarNombre(@Param("nombre") String nombre);


    @Modifying
    @Query("DELETE FROM Comentario c WHERE c.publicacion.usuario.id = :id")
    public void eliminarComentariosPorUsuario(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Publicacion p WHERE p.usuario.id = :id")
    public void eliminarPublicacionesPorUsuario(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Usuario u WHERE u.id = :id")
    public void eliminarUsuario(@Param("id") Long id);

    // @Query(" DELETE u, p, c FROM Usuarios u LEFT JOIN Publicaciones p ON u.id = p.usuario.id LEFT JOIN Comentarios c ON p.id = c.publicacion.id WHERE u.id = :id ")
    // public Usuario eliminarUsuario(@Param("usuario") Usuario usuario);


    @Query("SELECT e FROM Usuario e WHERE e.email = :email")
    public Usuario buscarEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE Usuario u SET u.nombre = :nombre, u.apellido = :apellido, u.email = :email, u.password = :password WHERE u.id = :id")
    int actualizarUsuario(@Param("id") Long id,
                          @Param("nombre") String nombre,
                          @Param("apellido") String apellido,
                          @Param("email") String email,
                          @Param("password") String password);
}


