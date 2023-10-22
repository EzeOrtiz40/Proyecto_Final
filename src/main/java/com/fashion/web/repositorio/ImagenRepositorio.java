package com.fashion.web.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fashion.web.entidades.Imagen;


@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, Long>{
    @Query("SELECT i FROM Imagen i WHERE i.id = :id")
    public Imagen getImagen(@Param("id")Long id);
}

