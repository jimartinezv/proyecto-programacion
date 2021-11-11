package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.*;

import co.edu.uniquindio.proyecto.repositorios.*;
import org.hibernate.internal.build.AllowSysOut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UnishopTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private  ComentarioRepo comentarioRepo;

    @Test
    public void crearUsuario(){

        Usuario usuario= new Usuario();
        usuario.setCodigo("1094908238");
        usuario.setNombre("Jorge Ivan");
        usuario.setEmail("jimartinezv@uqvirtual.edu.co");
        usuario.setContrasena("12456");
        usuario.setFechaNacimiento(LocalDate.now());

        Map<String, String> telefonos= new HashMap<>();
        telefonos.put("casa","7374645");
        telefonos.put("cel", "3216371746");
        //Usuario user= new Usuario("victor","victor@q.com","123456", telefonos,"calle 24", LocalDate.now());
        usuario.setTelefono(telefonos);

        usuarioRepo.save(usuario);
        Usuario usuarioBuscado =usuarioRepo.findById("1094908238").orElse(null);


        Assertions.assertNotNull(usuarioBuscado);
    }
    @Test
    public void eliminarUsuario(){
        Usuario usuario= new Usuario();
        usuario.setCodigo("1094908238");
        usuario.setNombre("Jorge Ivan");
        usuario.setEmail("jimartinezv@uqvirtual.edu.co");
        usuario.setContrasena("12456");
        usuario.setFechaNacimiento(LocalDate.now());

        Map<String, String> telefonos= new HashMap<>();
        telefonos.put("casa","7374645");
        telefonos.put("cel", "3216371746");

        usuario.setTelefono(telefonos);



        Usuario usuarioBuscado=usuarioRepo.findById("1094908238").orElse(null);

        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    public void actualizarUsuario(){
        Usuario usuario= new Usuario();
        usuario.setCodigo("1094908238");
        usuario.setNombre("Jorge Ivan");
        usuario.setEmail("jimartinezv@uqvirtual.edu.co");
        usuario.setContrasena("12456");
        usuario.setFechaNacimiento(LocalDate.now());

        Map<String, String> telefonos= new HashMap<>();
        telefonos.put("casa","7374645");
        telefonos.put("cel", "3216371746");

        usuario.setTelefono(telefonos);



        Usuario guardado= usuarioRepo.save(usuario);
        guardado.setEmail("jimv92300@gmail.com");

        usuarioRepo.save(guardado);
        Usuario usuarioBuscado = usuarioRepo.findById("1094908238").orElse(null);

        Assertions.assertEquals("jimv92300@gmail.com", usuarioBuscado.getEmail());

    }

    @Test
    @Sql("classpath:datos.sql")
    public void filtrarNombreTest(){
        List<Usuario> lista= usuarioRepo.findAllByNombreContaining("a");
        lista.forEach(usuario -> System.out.println(usuario));

    }

    @Test
    public void limitarNombresTest(){
        Pageable paginador= PageRequest.of(0,2);
        Page<Usuario> lista =usuarioRepo.findAll(paginador);
        System.out.println(lista);

    }
    @Test
    @Sql("classpath: datos.sql")
    public void OrdenarNombresTest(){
        Pageable paginador= PageRequest.of(0,2);
        List<Usuario> lista =usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);

    }

    @Test
    public void obtenerNombreVendedorTest(){
        String nombre = productoRepo.obtenerNombreVendedor(2);
        Assertions.assertEquals("Maria", nombre);

        //List<Producto> obtenerProductosFavoritos(String email);

    }
    @Test
    @Sql("classpath:datos.sql")
    public void obtenerFavoritosUsuarioTest(){
       List<Producto> favoritos = usuarioRepo.obtenerProductosFavoritos("jim@uq.com");
        Assertions.assertEquals(2,favoritos.size());
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarUsuarioCiudadTest(){
        List<Usuario> favoritos = ciudadRepo.listaUsuarios("Armenia");
        Assertions.assertEquals(2,favoritos.size());
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarUsuarioProductosTest(){
        List<Object[]> respuesta = usuarioRepo.obtenerTodosUsuarios();
        for (Object[] o: respuesta)
            System.out.println(o[0]+"---"+ o[1]);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarCategoriasTest() {
        List<Categoria> categorias = categoriaRepo.listaCategorias();
        categorias.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarProductosPorCategoriasTest() {
        List<Object[]> respuesta = productoRepo.obtenerTotalProductosPorCategoria();
        respuesta.forEach(r-> System.out.println(r[0]+"---"+r[1]));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerProductosPorCategoriasSubastaTest() {
        List<Object[]> respuesta = productoRepo.obtenerTotalProductosPorCategoria2();
        respuesta.forEach(r-> System.out.println(r[0]+"----"+r[1]));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerListaCategoriaCalificacionPromedioTest() {

        List<Object[]> respuesta = categoriaRepo.listarCategoriasPorCalificacion();
        respuesta.forEach(r-> System.out.println(r[0]+"----"+r[1]));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerListaComprasMedioPagoTest() {

        List<Object[]> respuesta = compraRepo.listarComprasPorMedioPago();
        respuesta.forEach(r-> System.out.println(r[0]+"----"+r[1]));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerListaChatVendedorTest() {

        List<Object[]> respuesta = chatRepo.listarChatVendedor();
        respuesta.forEach(r-> System.out.println(r[0]));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerListaSubastaPorCategoriaTest() {

        List<Object[]> subasta = subastaRepo.listarSubastaCategoria(1, LocalDateTime.now());
        subasta.forEach(r-> System.out.println(r[0]+"---"+r[1]));
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerListaProductosSinRespuestaTest() {

       List<Comentario> comentarios = comentarioRepo.listarComentariosPorProducto(2);
       comentarios.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerListaProductosConDescuentoTest() {

        List<Producto> producto = productoRepo.listarProductosPorDescuento(30.0f);
        producto.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:datos.sql")
    public void obtenerUsuarioGanadorSubastaTest() {
        List<Object[]> usuario = usuarioRepo.usuarioGandorSubasta(1);

        usuario.forEach(r-> System.out.println(r[1]));
    }

    @Test
    //@Sql("classpath:datos.sql")
    public void obtenerValorTotalComprasUsuarioTest() {
        List<Object[]> valorTotal = compraRepo.valorTotalComprasUsuario("1094908238");
        valorTotal.forEach(r-> System.out.println(r[0]+"---"+r[1]));
        //System.out.println((long)valorTotal);
    }

    @Test
    //@Sql("classpath:datos.sql")
    public void obtenerProductoMasVendidoPorCategoriaTest() {
        List<Object[]> producto = productoRepo.mostarProductoMasVendidoCategoria(7);
        //producto.forEach(r-> System.out.println(r[0]+"---"));
        System.out.println(producto.get(0)[1]);
        //System.out.println((long)valorTotal);
    }


    /**
     * Trae los datos distinguiendo filas

    @Test
    @Sql("classpath:datos.sql")
    public void listarUsuariosComentariosTest(){
       List<Usuario> usuarios= productoRepo.listarUsuariosComentario(1);
       usuarios.forEach(System.out::println);
    }**/
/**
    @Test
    @Sql("classpath:datos.sql")
    public void listarPorductosValidosTest(){
        List<ProductoValido> productos= productoRepo.listarProductosValidos2(LocalDate.now());
        productos.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:datos.sql")
    public void listarPorductosCompradosTest(){
        Long totalProductos= compraRepo.obtenerListaProductosComprados("88788898");
        System.out.println("El total de productos es : "+ totalProductos);

    }
    @Test
    @Sql("classpath:datos.sql")
    public void listarPorductosCategoriaTest(){
       List<Object[]> respuesta = productoRepo.obtenerTotalProductosCategoria();
       respuesta.forEach(r-> System.out.println(r[0]));

    }**/


}
