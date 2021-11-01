package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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

    @OneToOne(mappedBy = "tipoDocumento")
    private Usuario usuario;

    @OneToOne(mappedBy = "tipoDocumento")
    private Administrador administrador;


    public TipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
