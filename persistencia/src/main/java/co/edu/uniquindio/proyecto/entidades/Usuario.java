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
public class Usuario extends Persona implements Serializable {

    @ElementCollection
    private List<String> telefono;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> producto;
    private String direccion;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compra;


    public Usuario( List<String> telefono, String direccion) {

        this.telefono = telefono;
        this.direccion = direccion;
    }
}
