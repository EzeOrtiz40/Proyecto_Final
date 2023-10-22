package com.fashion.web.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import com.fashion.web.Enumeraciones.Rol;
import lombok.Data;


@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private Long id; 

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Date fecha_creacion;
    
    @OneToOne
    private Imagen imagen;

    // @OneToMany(mappedBy = "usuario")
    // private List<Publicacion> publicaciones;

    // @OneToMany(mappedBy = "usuario")
    // private List<Comentario> comentarios;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    
}

