package com.fashion.web.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class ImagenPubli {
        @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private Long id;
    private String mime;
    private String nombre;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;

    public ImagenPubli(){}
    public ImagenPubli(String mime, String nombre, byte[] contenido){
        this.mime = mime;
        this.nombre = nombre;
        this.contenido = contenido;
    }
}
