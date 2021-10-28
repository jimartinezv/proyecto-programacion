package co.edu.uniquindio.proyecto.entidades;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @ElementCollection
    private Map<String, String> telefono;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> producto;
    private String direccion;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compra;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentario;

    @OneToMany(mappedBy = "usuario")
    private List<SubastaUsuario> subastaUsuario;

    @OneToOne(mappedBy = "usuario")
    private Carrito carrito;

    @OneToMany(mappedBy = "usuario")
    private List<Favorito> favorito;

    private LocalDate fechaNacimiento;


    public Usuario( Map<String, String> telefono, String direccion) {

        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Usuario(String nombre, String email, String contrasena, Map<String, String> telefono,  String direccion, LocalDate fechaNacimiento) {
        super(nombre, email, contrasena);
        this.telefono = telefono;
        this.producto = producto;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }
}
