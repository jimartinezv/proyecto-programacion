package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.email.EmailService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Controller
/**
 * ViewScope conserva todas las variables que se estan viendo en el momento
 * hasta que se refresca la página
 */
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private EmailService emailService;

    @Getter @Setter
    private List<Producto> productos;

    private boolean enviarCorreo;

    @PostConstruct
    public void inicializar(){
        this.productos=productoServicio.listarProductos();
        System.out.println("Se va a enviar un correo");
        //this.enviarCorreo=emailService.sendEmailTool("Hola esto es una prueba","jimv9200@gmail.com","Ejemplo");
        //System.out.println("Se envio un correo");
    }

    public String irDetalle(String id){
        return "detalle_producto?faces-redirect=true&amp;producto="+id;
    }
}
