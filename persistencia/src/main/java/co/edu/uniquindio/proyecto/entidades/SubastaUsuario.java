package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SubastaUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private double valor;
    private LocalDateTime fechaSubasta;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Subasta subasta;

    public SubastaUsuario(double valor, LocalDateTime fechaSubasta) {
        this.valor = valor;
        this.fechaSubasta = fechaSubasta;
    }
}
