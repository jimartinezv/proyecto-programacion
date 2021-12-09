package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producto")
public class ProductoRestControl {
    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> listarProductos(){
        return productoServicio.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable(name = "id") Integer id){
        try {
            return ResponseEntity.status(200).body(productoServicio.obtenerProducto(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    @PostMapping
    public ResponseEntity<?>  crearProducto(@RequestBody Producto producto){
        try {
            productoServicio.publicarProducto(producto);

            return ResponseEntity.status(200).body(new Mensaje("Se ha creado el producto"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>  borrarProducto(@PathVariable(name = "id") Integer id){
        try{
            System.out.println("se eliminará el usuario" +id);
            productoServicio.eliminarProducto(id);

            return ResponseEntity.status(200).body(new Mensaje("Producto eliminado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }


    }

    @PutMapping
    public ResponseEntity<?>  actualizarProducto(@RequestBody Producto producto){
        try {
            productoServicio.actualizarProducto(producto);

            return ResponseEntity.status(200).body(new Mensaje("Producto actualizado"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
}
