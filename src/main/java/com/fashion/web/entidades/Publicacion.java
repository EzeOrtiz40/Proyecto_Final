package com.fashion.web.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;


import com.fashion.web.Enumeraciones.RolCategoria;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Entity
@Data
public class Publicacion {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private Long id;

    private String titulo;
    private String contenido;

    @Enumerated(EnumType.STRING)
    private RolCategoria RolCategoria;

    @OneToOne
    private Imagen imagenPublicacion;

    @ManyToOne
    private Usuario usuario;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    

}