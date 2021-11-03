package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Producto;
;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, String> {

    Page<Producto> findAll(Pageable paginador);

    @Query("select p.usuario.nombre from Producto p where p.codigo = :id")
    String obtenerNombreVendedor(Integer id);

    @Query("select distinct c.usuario from Producto p join p.comentario c where p.codigo=:id")
    List<Usuario> listarUsuariosComentario(Integer id);

    @Query("select p.nombre, p.descripcion, p.precio, p.ciudad.nombre from Producto p where p.fechaLmite> :fechaActual")
    List<Object[]> listarProductosValidos(LocalDate fechaActual);

  //  @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido ( p.nombre, p.descripcion, p.precio, p.ciudad.nombre) from Producto p where p.fechaLmite> :fechaActual")
    //List<ProductoValido> listarProductosValidos2(LocalDate fechaActual);

    @Query("select count(p), p.categoria from Producto p join p.categoria c group by c")
    List<Object[]> obtenerTotalProductosCategoria();

    /**
     * sentencia para utilizar el like en jql
     * @param nombre
     * @return
     */
    @Query("select p from Producto p where p.nombre like concat('%',:nombre,'%') ")
    List<Producto> buscarProductoNombre(String nombre);
    /**
    @Query("select count(p) from Producto p  join p.categoria c group by c");
    List<Object[]> obtenerTotalProductosPorSubasta();
    */

    //La cantidad de productos que están en subasta por cada categoría.
    @Query("select count(p), c.nombre from Producto p, IN(p.subasta) s, IN(p.categoria) c group by c")
    List<Object[]> obtenerTotalProductosPorCategoria();

    @Query("select count(p), c.nombre from Categoria c, IN(c.producto) p, IN(p.subasta) s group by c.codigo")
    List<Object[]> obtenerTotalProductosPorCategoria2();

    //Una lista de productos que tienen un descuento que está dentro de un rango que se pase
    //por parámetro. Solo muestre los productos que tengan unidades disponibles.

    @Query("select p from Producto p where p.descuento < :descuento and p.unidades>0")
    List<Producto> listarProductosPorDescuento(float descuento);

    //El producto más vendido de una categoría específica.
    @Query("select max(count(d.unidades)), c.nombre from Producto p , IN(p.categoria) c, IN(p.detalleCompra) d  group by c.nombre")
    List<Object[]> mostarProductoMasVendidoCategoria();


}
