package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ProductoValido {
    private String nombre;
    private String descripcion;
    private double precio;
    private Ciudad ciudad;


}
