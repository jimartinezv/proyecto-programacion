package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Departamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 55)
    @ToString.Include
    private String nombre;

    @OneToMany(mappedBy = "departamento")
    private List<Ciudad> ciudades;

    public Departamento(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
