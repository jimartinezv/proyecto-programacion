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
public class DetalleCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private Integer unidades;
    private double precioPoducto;


    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Compra compra;


    public DetalleCompra(Integer unidades, double precioPoducto) {
        this.unidades = unidades;
        this.precioPoducto = precioPoducto;
    }
}
