package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
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
    @Column(nullable = false, length = 55, unique = true)
    @Length(max = 50)
    @NotBlank(message = "Debe digitar un documento válido")
    private String documento;
    @Column(nullable = false, length = 55)
    @NotBlank(message = "Campo de nombre obligatorio")
    private String nombre;
    @Column(nullable = false, length = 155, unique = true)
    @Email
    @NotBlank(message = "Campo de correo electronico obligatorio")
    private String email;
    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false, length = 55)
    @Length(max = 55, min = 8, message = "La contraseña debe tener minimo 8 caracteres y maximo 55")
    private String contrasena;





    public Persona(String documento,String nombre, String email, String contrasena) {
        this.documento=documento;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }
}
