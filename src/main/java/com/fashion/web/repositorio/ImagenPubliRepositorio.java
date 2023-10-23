package com.fashion.web.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fashion.web.entidades.ImagenPubli;

public interface ImagenPubliRepositorio extends JpaRepository<ImagenPubli, String>{
    @Query("SELECT i FROM ImagenPubli i WHERE i.id = :id")
    public ImagenPubli getImagenPubli(@Param("id")Long id);
}

