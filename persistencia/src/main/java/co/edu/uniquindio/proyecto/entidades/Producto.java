package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @NotBlank(message = "La descripción del producto es obligatoria")
    @Column(nullable = false)
    private String descripcion;

    @Positive(message = "El precio del producto no es válido")
    @Column( nullable = false)
    private float precio;
    @Future
    private LocalDateTime fechaLmite;
    @PositiveOrZero
    private float descuento;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> imagen;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> detalleCompra;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentario;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    @JsonIgnore
    private List<Subasta> subasta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @ManyToMany(mappedBy = "producto")
    @JsonIgnore
    private List<Carrito> carrito;

    @ManyToMany
    @JsonIgnore
    private List<Categoria> categoria;

    //@OneToMany(mappedBy = "producto")
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Usuario> favoritoUsuario;

    public Producto(String nombre, Integer unidades, String descripcion, float precio, LocalDateTime fechaLmite, float descuento, Usuario usuario) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLmite = fechaLmite;
        this.descuento = descuento;
        this.usuario = usuario;
        this.favoritoUsuario= new ArrayList<>();
    }

    public void agregarFavorito(Usuario usuarioFav){
        favoritoUsuario.add(usuarioFav);
    }

    public String getImagenPrincipal(){
        if(imagen!=null && !imagen.isEmpty()){
            return imagen.get(0);
        }
        return "default.png";
    }



}
