package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleCompraImpl implements DetalleCompraServicio {

    @Autowired
    DetalleCompraRepo detalleCompraRepo;

    @Override
    public DetalleCompra obtenerDetallecompra(Integer id) {
        return detalleCompraRepo.getById(id);
    }
}
