package com.fashion.web.repositorio;

import com.fashion.web.entidades.Publicacion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PubliRepositorio extends JpaRepository<Publicacion, String> {

    @Query("SELECT p FROM Publicacion p WHERE p.id = :id")
    public Publicacion buscarPorId(@Param("id") String id);

    
}
