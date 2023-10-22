package com.fashion.web.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Entity
@Data
public class Comentario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid")
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;
    String texto;
}
