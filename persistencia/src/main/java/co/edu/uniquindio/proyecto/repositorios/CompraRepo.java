package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    @Query("select  count(distinct d.producto)  from Compra c join c.detalleCompra d where c.usuario.codigo=:codigo")
    Long obtenerListaProductosComprados(String codigo);

    //La cantidad de compras que se hace por cada medio de pago disponible.
    @Query("select count(c), m.descripcion from Compra c, IN(c.medioPago)m group by m.codigo ")
    List<Object[]> listarComprasPorMedioPago();

    //El valor total de cada una de las compras que ha hecho un usuario específico.
    @Query("select sum(d.precioPoducto*d.unidades) from Compra c, IN(c.usuario) u, IN (c.detalleCompra) d where u.codigo=:id")
    Double valorTotalComprasUsuario(Integer id);
}
