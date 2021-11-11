package co.edu.uniquindio.proyecto.entidades;

import lombok.*;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
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
    //@JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Producto> producto;
    private String direccion;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Compra> compra;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> comentario;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuario;

    @OneToOne(mappedBy = "usuario")
    @ToString.Exclude
    private Carrito carrito;

    @OneToOne
    private TipoDocumento tipoDocumento;

    //@OneToMany(mappedBy = "usuario")
    @ManyToMany(mappedBy = "favoritoUsuario")
    @ToString.Exclude
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "comprador")
    @ToString.Exclude
    private List<Chat> chatComprador;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    private List<Chat> chatVendedor;


    private LocalDate fechaNacimiento;
    @Column(nullable = false, length = 50, unique = true)
    private String userName;


    public Usuario( Map<String, String> telefono, String direccion) {

        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Usuario(String nombre, String email, String contrasena,  String direccion, LocalDate fechaNacimiento, String userName, Ciudad ciudad) {
        super(nombre, email, contrasena);
        this.ciudad=ciudad;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.userName = userName;
    }
}
