package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

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

    public void filtrarNombreTest(){
        List<Usuario> lista= usuarioRepo.findAllByNombreContaining("a");
        lista.forEach(usuario -> System.out.println(usuario));

    }

    @Test
    public void limitarNombres(){
        Pageable paginador= PageRequest.of(0,2);
        Page<Usuario> lista =usuarioRepo.findAll(paginador);
        System.out.println(lista);

    }
    @Test
    public void OrdenarNombres(){
        Pageable paginador= PageRequest.of(0,2);
        List<Usuario> lista =usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);

    }

}
