package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class ProductoBean implements Serializable {
    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;


    @PostConstruct
    public void inicializar(){
        this.producto= new Producto();
    }

    public String publicarProducto(){
        try {
            // Se quema el usuario vendedor al producto
            Usuario usuario= usuarioServicio.obtenerUsuario("123");
            producto.setUsuario(usuario);
            productoServicio.publicarProducto(producto);
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            //return "producto_creado";
            return "producto_creado?faces-redutect=true";
        } catch (Exception e) {
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            e.printStackTrace();
        }
        return null;
    }
}
