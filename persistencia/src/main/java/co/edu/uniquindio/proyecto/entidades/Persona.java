package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS )
@MappedSuperclass
public class Persona implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    @Column(nullable = false, length = 55)
    private String nombre;
    @Column(nullable = false, length = 155, unique = true)
    private String email;
    @Column(nullable = false, length = 55)
    private String contrasena;

    public Persona(String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }
}
