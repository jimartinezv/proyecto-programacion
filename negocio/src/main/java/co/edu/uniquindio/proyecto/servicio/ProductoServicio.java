package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;

import java.util.ArrayList;
import java.util.List;

public interface ProductoServicio {

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto publicarProducto(Producto p) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoFavoritos(Producto p, Usuario u) throws Exception;

    void EliminarProductoFavoritos(Producto p, Usuario u) throws Exception;

    void comprarProducto(Compra c)throws Exception;

    void guardarImagenProducto(Integer codigo,String imagen);

    /**
     * Tambien se requiere buscar por precio o ciudad o cualquier otro atributo
     * @param nombreProducto
     * @param filtros
     * @return
     */
    List<Producto> buscarProducto(String nombreProducto, String[] filtros);

    List<Producto> listarProductorUsuario(String codigoUsuario) throws Exception;

    Categoria buscarCategoria(Integer codigo);

    List<Categoria> listarCategorias();

    List<Producto> listarProductos();

    Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productoCarritos, String medioPago) throws Exception;
}
