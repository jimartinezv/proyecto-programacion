package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ProductoServicio {

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto publicarProducto(Producto p) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    void comentarProducto(String mensaje, Integer calificacion, Usuario u, Producto p) throws Exception;

    void guardarProductoFavoritos(Producto p, Usuario u) throws Exception;

    void EliminarProductoFavoritos(Producto p, Usuario u) throws Exception;

    void comprarProducto(Compra c)throws Exception;

    /**
     * Tambien se requiere buscar por precio o ciudad o cualquier otro atributo
     * @param nombreProducto
     * @param filtros
     * @return
     */
    List<Producto> buscarProducto(String nombreProducto, String[] filtros);

    List<Producto> listarProductorUsuario(String codigoUsuario) throws Exception;
}
