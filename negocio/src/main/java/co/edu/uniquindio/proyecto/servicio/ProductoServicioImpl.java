package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductoRepo productoRepo;

    @Override
    public void actualizarProducto(Producto p) throws Exception {

    }

    @Override
    public void eliminarProducto(Integer id) throws Exception {
        Optional<Producto> producto = productoRepo.findById(id);

        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }
        productoRepo.deleteById(id);
    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try {
            return productoRepo.save(p);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }


    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow(() -> new Exception("No se ha encontrado el producto"));
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {
        return null;
    }

    @Override
    public void comentarProducto(String mensaje, Integer calificacion, Usuario u, Producto p) throws Exception {

    }

    @Override
    public void guardarProductoFavoritos(Producto p, Usuario u) throws Exception {

    }

    @Override
    public void EliminarProductoFavoritos(Producto p, Usuario u) throws Exception {

    }

    @Override
    public void comprarProducto(Compra c) throws Exception {

    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto, String[] filtros) {

        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductorUsuario(String codigoUsuario) throws Exception {
        return null;
    }
}
