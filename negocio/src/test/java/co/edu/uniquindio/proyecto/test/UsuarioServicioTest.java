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

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    private void registrarUsuarioTest() {
        Usuario u= new Usuario("Jorge Ivan", "jimv9200@gmail.com","123456", "Calle siempre viva 743",LocalDate.now(),"jimv9200",null);
       try {
           Usuario respuesta=usuarioServicio.registrarUsuario(u);
           Assertions.assertNotNull(respuesta);
       }catch (Exception e){
           e.printStackTrace();
       }

    }
}
