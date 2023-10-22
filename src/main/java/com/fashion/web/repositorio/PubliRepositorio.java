package com.fashion.web.repositorio;

import com.fashion.web.entidades.Publicacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PubliRepositorio extends JpaRepository<Publicacion, Long>{

    @Query("SELECT p FROM Publicacion p WHERE p.id = :id")
    public Publicacion buscarPorId(@Param("id") Long id);


    @Query("SELECT p FROM Publicacion p ORDER BY p.fecha DESC")
    public List<Publicacion> trerRecientes(@Param("id") Long id);

}
