package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase se agrega ya que si el usuario se desconecta de la plataforma podrá seguir con sus compras
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Carrito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @OneToOne
    private Usuario usuario;

    @ManyToMany
    private List<Producto> producto;

    private boolean activo;

    public Carrito(boolean activo) {
        this.activo = activo;
    }
}
