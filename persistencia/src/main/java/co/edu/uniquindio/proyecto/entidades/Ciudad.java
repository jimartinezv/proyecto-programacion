package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Column(length = 55)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuario;

    @OneToMany(mappedBy = "ciudad")
    private List<Producto> producto;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
