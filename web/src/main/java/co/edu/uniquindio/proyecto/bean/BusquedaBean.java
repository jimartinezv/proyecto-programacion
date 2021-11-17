package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {
    @Getter
    @Setter
    private String busqueda;

    @Getter @Setter
    private List<Producto> productos;

    @Autowired
    private ProductoServicio productoServicio;

    @Setter @Getter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @PostConstruct
    public void incializar(){
        if(busquedaParam!=null && !busquedaParam.isEmpty() ){
            productos = productoServicio.buscarProducto(busquedaParam,null);
        }
    }
    public String buscar(){
        return "resultado_busqueda?faces-redirect=true&amp;busqueda="+busqueda;
    }
}
