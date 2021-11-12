package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    /*
    @NotBlank
    @Column(length=25, nullable = false)
    private String codigo;
    */
    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(length=25, nullable = false)
    private String nombre;

    @PositiveOrZero
    @Column( nullable = false)
    private Integer unidades;

    /**
     * Permite que el campo a parte de no ser nulo que no este vacio
     * Solo sirve en string
     */
    @NotBlank
    private String descripcion;

    @Positive
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
    @ToString.Exclude
    private List<DetalleCompra> detalleCompra;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentario;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> subasta;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToMany(mappedBy = "producto")
    private List<Carrito> carrito;

    @ManyToMany(mappedBy = "producto")
    private List<Categoria> categoria;

    //@OneToMany(mappedBy = "producto")
    @ManyToMany
    @ToString.Exclude
    private List<Usuario> favoritoUsuario;




}
