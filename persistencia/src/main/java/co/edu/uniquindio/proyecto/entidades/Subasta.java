package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subasta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @Future
    private LocalDateTime fechaLimite;

    @ManyToOne
    private Producto producto;

    @OneToMany(mappedBy = "subasta")
    private List<SubastaUsuario> subastaUsuario;

    public Subasta(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
