package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.TipoDocumento;
import co.edu.uniquindio.proyecto.repositorios.TipoDocumentoRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class TipoDocumentoServicioImpl implements TipoDocumentoServicio{

    @Autowired
    TipoDocumentoRepo tdRepo;

    @Override
    public TipoDocumento documento(Integer codigo) {
        return tdRepo.getById(codigo);
    }
}
