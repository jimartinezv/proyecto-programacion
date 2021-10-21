package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(length=25)
    private String codigo;

    @Column(length=25, nullable = false)
    private String nombre;
    @Column( nullable = false)
    private Integer unidades;

    private String descripcion;
    @Column( nullable = false)
    private double precio;
    @Future
    private LocalDate fechaLmite;
    private float descuento;

    @ElementCollection
    private List<String> imagen;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "producto")
    private List<DetalleCompra> detalleCompra;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentario;

    @OneToMany(mappedBy = "producto")
    private List<Subasta> subasta;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToMany(mappedBy = "producto")
    private List<Carrito> carrito;

    @OneToMany(mappedBy = "producto")
    private List<Favorito> favorito;


}
