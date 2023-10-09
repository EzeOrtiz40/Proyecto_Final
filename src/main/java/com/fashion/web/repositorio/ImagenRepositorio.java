package com.fashion.web.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fashion.web.entidades.Imagen;


@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String>{
    
}
