package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;

import java.util.List;

public interface CiudadServicio {

    List<Ciudad> obtenerCiudades();

    Ciudad buscarCiudadPorCodigo(Integer codigo);


}
