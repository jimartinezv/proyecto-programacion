package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Component
@ViewScoped
public class ProductoBean implements Serializable {
    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlUploads;


    @PostConstruct
    public void inicializar(){

        this.producto= new Producto();
        this.imagenes= new ArrayList<>();
    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen= event.getFile();
        String nombreImagen= subirImagen(imagen);
        if(nombreImagen!=null){
            imagenes.add(nombreImagen);
        }

    }

    public String subirImagen(UploadedFile imagen){
        try {
            File archivo = new File(urlUploads + "/" + imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public String publicarProducto(){
        try {
            // Se quema el usuario vendedor al producto
            if(!imagenes.isEmpty()) {
                Usuario usuario = usuarioServicio.obtenerUsuario("123");
                producto.setUsuario(usuario);
                productoServicio.publicarProducto(producto);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro exitoso");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                //return "producto_creado";
            }else{
                FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_WARN,"Alerta","Se necesita mas de una imagen");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
            return "producto_creado?faces-redutect=true";
        } catch (Exception e) {
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            e.printStackTrace();
        }
        return null;
    }
}
