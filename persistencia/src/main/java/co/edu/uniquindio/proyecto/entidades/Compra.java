package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;



    @Column(name="timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCompra;
    //@Column(nullable = false, length = 50)
    //private String medioPago;
    @ManyToOne
    private MedioPago medioPago;

    @OneToMany(mappedBy = "compra")
    private List<DetalleCompra> detalleCompra;

    @ManyToOne
    private Usuario usuario;

    public Compra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;

    }
}
