package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @ToString.Include
    private String nombre;


    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuario;

    @OneToMany(mappedBy = "ciudad")
    private List<Producto> producto;

    @ManyToOne
    private Departamento departamento;

    public Ciudad(String nombre, Departamento departamento) {
        this.departamento=departamento;
        this.nombre = nombre;
    }
}
