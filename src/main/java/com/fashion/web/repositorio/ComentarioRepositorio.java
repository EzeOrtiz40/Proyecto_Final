package com.fashion.web.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fashion.web.entidades.Comentario;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {

    @Query("SELECT c FROM Comentario c WHERE c.publicacion.id = :id ORDER BY c.id DESC")
    public List<Comentario> buscarPorIdPublicacion(@Param("id") Long id);
}
