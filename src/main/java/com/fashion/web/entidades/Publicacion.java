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
import com.fashion.web.Enumeraciones.RolCategoria;

import lombok.Data;

@Entity
@Data
public class Publicacion {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private Long id; 

    private String contenido;
    private Date fechaPubli;
    
    @OneToOne
    private Imagen im_publicacion;

    private Long id_user_publicacion;

    @OneToMany
    private List<Comentario> comentarios;
    
    @Enumerated(EnumType.STRING)
    private RolCategoria rolCategoria;
}












// package com.fashion.web.entidades;


// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.EnumType;
// import javax.persistence.Enumerated;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.OneToMany;
// import javax.persistence.OneToOne;

// import java.util.Date;
// import java.util.List;
// import com.fashion.web.Enumeraciones.RolCategoria;

// import org.hibernate.annotations.GenericGenerator;

// import lombok.Data;




// @Entity
// @Data
// public class Publicacion {

//     @Id
//     @GeneratedValue(generator = "uuid")
//     @GenericGenerator(name = "uuid2", strategy = "uuid2")
//     private Long id;

//     private String titulo;
//     private String contenido;

//     @Column(columnDefinition = "TIMESTAMP")
//     private Date fechaPubli;

//     @Enumerated(EnumType.STRING)
//     private RolCategoria RolCategoria;

//     @OneToOne
//     private Imagen im_publicacion;

//     @OneToOne
//     private Usuario user_publicacion;

    // @OneToMany
    // private List<Comentario> coments;

    
    
// }