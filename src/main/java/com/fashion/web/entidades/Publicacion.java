package com.fashion.web.entidades;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;
import com.fashion.web.Enumeraciones.RolCategoria;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;


@Entity 
@Data
public class Publicacion {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid")
    private Long id;

    private String titulo;
    private String contenido;

    @Enumerated(EnumType.STRING)
    private RolCategoria RolCategoria;

    @OneToOne
    private Imagen imagenPublicacion;
    
    @OneToMany(mappedBy = "publicacion")
    private List<Comentario> comentarios;

    @OneToMany
    private List<Usuario> userPublicacion;

    private Date fechaCreacion;
    
}