package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarUsuarioTest() {
        Usuario u= new Usuario("12455","Jorge Ivan", "jimv92000@gmail.com","123456", "Calle siempre viva 743",LocalDate.now(),"jimv92000",null);
       try {
           Usuario respuesta=usuarioServicio.registrarUsuario(u);
           Assertions.assertNotNull(respuesta);
       }catch (Exception e){
           e.printStackTrace();
           Assertions.assertTrue(false);
       }

    }

    @Test
    public void eliminarUsuario(){
        try {
            usuarioServicio.eliminarUsuario("124");
            Assertions.assertTrue(true);
        }catch (Exception e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
    @Test
    public void listarUsuarios(){
        List<Usuario> usuarioList=usuarioServicio.listarUsuarios();
        usuarioList.forEach(System.out::println);
    }

    @Test
    public void actualizarUsuario()  {
        try {
            Usuario u = usuarioServicio.obtenerUsuario("7666389");
            u.setContrasena("$#$&%Fdfdffd");
            usuarioServicio.actualizarUsuario(u);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void loginTest(){
        try {
            Usuario usuario= usuarioServicio.login("juan@gmail.com","contrasena");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());

        }
    }
}
