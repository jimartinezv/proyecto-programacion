package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable(name = "id") String id){
        try {
            return ResponseEntity.status(200).body(usuarioServicio.obtenerUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    @PostMapping
    public ResponseEntity<?>  crearUsuario(@RequestBody Usuario usuario){
        try {
            usuarioServicio.registrarUsuario(usuario);
            return ResponseEntity.status(200).body(new Mensaje("Se ha creado el usuario"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>  borrarUsuario(@PathVariable(name = "id") String id){
        try{
            System.out.println("se eliminarĂ¡ el usuario" +id);
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.status(200).body(new Mensaje("Usuario eliminado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }


    }

    @PutMapping
    public ResponseEntity<?>  actualizarUsuario(@RequestBody Usuario usuario){
        try {
            System.out.println(usuario.getDocumento()+"Api usuario actualiza");
            usuarioServicio.actualizarUsuario(usuario);
            return ResponseEntity.status(200).body(new Mensaje("Usuario actualizado"));
        }catch (Exception e){
             e.printStackTrace();
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
}
