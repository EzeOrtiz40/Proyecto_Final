package com.fashion.web.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fashion.web.entidades.MeGusta;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;

public interface MegustaRepositorio extends JpaRepository<MeGusta, Long> {

    @Query("SELECT m FROM MeGusta m WHERE m.id = : id")
    public MeGusta getById(@Param("id") Long id);

    public boolean existsByUsuarioAndPublicacion(Publicacion publicacion, Usuario usuario);

    public long countByPublicacion(Publicacion publicacion);

    public void deleteByPublicacionAndUsuario(Publicacion publicacion, Usuario usuario);
}
