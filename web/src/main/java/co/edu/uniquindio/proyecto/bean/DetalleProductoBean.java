package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Getter @Setter
    private Producto producto;

    @Setter @Getter
    private Comentario nuevoComentario;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Getter @Setter
    private int calificacionPromedio;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar()  {
        nuevoComentario = new Comentario();
        calificacionPromedio=0;
        calificacionPromedio=getCalificacionPromedio();
        try {
            if(codigoProducto!=null && !codigoProducto.isEmpty()) {

                producto = productoServicio.obtenerProducto(Integer.parseInt(codigoProducto));
                this.comentarios= producto.getComentario();
            }
        } catch (Exception e){

        }
    }

    public void crearComentario()  {
        try {
            if(usuarioSesion!=null) {
                nuevoComentario.setProducto(producto);
                nuevoComentario.setUsuario(usuarioSesion);
                productoServicio.comentarProducto(nuevoComentario);
                this.comentarios.add(nuevoComentario);
                nuevoComentario = new Comentario();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public int getCalificacionPromedio(){
        System.out.println("Sacando calificacion");
        int prom=1;/**
        if(comentarios.size()>0) {
            for (Comentario comentario : comentarios) {
                prom += comentario.getCalificacion();
            }
            return prom / comentarios.size();
        }**/
        return 4;
    }

}
