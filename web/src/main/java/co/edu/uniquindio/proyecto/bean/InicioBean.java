package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
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

    @Getter @Setter
    private List<Producto> productos;

    @PostConstruct
    public void inicializar(){
        this.productos=productoServicio.listarProductos();
    }

    public String irDetalle(String id){
        return "detalle_producto?faces-redirect=true&amp;producto="+id;
    }
}
