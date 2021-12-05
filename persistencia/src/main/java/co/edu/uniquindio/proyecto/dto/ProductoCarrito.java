package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
public class ProductoCarrito {



    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre, imagen;
    private Integer unidades;
    private Integer unidadesDisponibles;
    private float precio;


}
