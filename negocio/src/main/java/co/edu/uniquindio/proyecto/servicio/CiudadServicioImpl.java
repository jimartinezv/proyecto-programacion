package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CiudadServicioImpl implements CiudadServicio{
    @Autowired
    private CiudadRepo ciudadRepo;

    @Override
    public List<Ciudad> obtenerCiudades() {

        List<Ciudad> ciudades= ciudadRepo.listarCiudades();
        return ciudades;
    }

    @Override
    public Ciudad buscarCiudadPorCodigo(Integer codigo) {
        return ciudadRepo.obtenerCiudadPorCodigo(codigo);
    }
}
