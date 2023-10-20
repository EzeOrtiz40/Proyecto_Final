package com.fashion.web.entidades;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import com.fashion.web.Enumeraciones.RolCategoria;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;




@Entity @Data
public class Publicacion {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid")
    private String id;

    private String titulo;
    private String contenido;

    @Enumerated(EnumType.STRING)
    private RolCategoria RolCategoria;

    @OneToOne
    private Imagen im_publicacion;

    @OneToOne
    private Usuario user_publicacion;

    @OneToMany
    private List<Comentario> coments;

    private LocalDate fechaPubli;
    
}