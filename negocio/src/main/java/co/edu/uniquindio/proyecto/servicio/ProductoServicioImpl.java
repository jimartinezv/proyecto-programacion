package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private DetalleCompraRepo detalleCompra;

    @Autowired
    private MedioPagoRepo medioPagoRepo;

    @Override
    public void actualizarProducto(Producto p) throws Exception {
        try{
            Optional<Producto> buscado= productoRepo.findById(p.getCodigo());


            System.out.println("Se está actualizando el producto "+ p.getCodigo());
            if(!buscado.isPresent())
                throw new Exception("Codigo de prductoo no existente");
            System.out.println("Se actualizó");
            System.out.println("el producto :( " +(p.getCodigo()+p.getNombre()+p.getDescripcion()+p.getPrecio()+p.getUnidades()));
            productoRepo.save(p);



        }catch (Exception e){
            throw new Exception(e.getMessage());

        }

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
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFechaComentario(LocalDateTime.now());
        comentarioRepo.save(comentario);
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
    public void guardarImagenProducto(Integer codigo, String imagen) {

    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto, String[] filtros) {

        return productoRepo.buscarProductoNombreAndCategoria(nombreProducto);
    }

    @Override
    public List<Producto> listarProductorUsuario(String codigoUsuario) throws Exception {
        return productoRepo.obtenerProductoPorUsuario(codigoUsuario);
    }

    @Override
    public Categoria buscarCategoria(Integer codigo) {
        return productoRepo.buscarCategoria(codigo);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return productoRepo.listarCategoria();
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepo.findAll();
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productoCarrito, String medioPago) throws Exception {
        try{


        Compra c= new Compra();
       c.setFechaCompra(LocalDateTime.now(ZoneId.of("America/Bogota")));
       c.setUsuario(usuario);
       c.setMedioPago(medioPagoRepo.getById(1));
        //TODO enviar correo utilizar JAVA MAIL
       Compra compraGuardada= compraRepo.save(c);
       DetalleCompra dc;
       for(ProductoCarrito p: productoCarrito){
           dc= new DetalleCompra();
           dc.setCompra(compraGuardada);
           dc.setPrecioPoducto(p.getPrecio());
           dc.setUnidades(p.getUnidades());
           dc.setProducto(productoRepo.findById(p.getId()).get());
           //TODO verificar que klals unidades del producto si esten disponibles
           detalleCompra.save(dc);
       }
       return compraGuardada;
        }catch (Exception e){
            throw new Exception((e.getMessage()));
        }
    }
}
