// package com.fashion.web.repositorio;



// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import com.fashion.web.entidades.Comentario;


// public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {
    
//     @Query("SELECT c FROM Comentario c WHERE c.id = :id")
//     public Comentario buscarComentario(@Param("id") Long id);
    
//     @Modifying
//     @Query("DELETE FROM Comentario c WHERE c.id = :id")
//     public Comentario eliminarById(@Param("id") Long id);
// }

