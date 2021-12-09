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
public class TipoDocumento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Column(nullable = false, length = 55)
    private String tipoDocumento;


    @OneToMany(mappedBy = "tipoDocumento")
    private List<Usuario> usuario;


    public TipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
