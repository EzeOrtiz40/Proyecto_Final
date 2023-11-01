package com.fashion.web.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class MeGusta {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private Long id;

    @ManyToOne
    private Publicacion publicacion;

    @ManyToOne
    private Usuario usuario;

}
