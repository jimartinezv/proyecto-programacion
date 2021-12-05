package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicio.CiudadServicio;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Autowired
    CiudadServicio ciudadServicio;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private List<Categoria> categorias;

    @Setter @Getter
    private Categoria categoria;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<SelectItem>  listaDepartamentos;

    @Getter @Setter
    private Integer indexDepto;

    @Autowired
    private ProductoServicio productoServicio;

    private ArrayList<String> imagenes;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Value("${upload.url}")
    private String urlUploads;


    @PostConstruct
    public void inicializar(){

        this.producto= new Producto();
        this.imagenes= new ArrayList<>();
        categorias= productoServicio.listarCategorias();


        ciudad= new Ciudad();
    }

    public void subirImagenes(FileUploadEvent event){
        System.out.println("aqui estoy en subir imagenes");
        UploadedFile imagen= event.getFile();
        String nombreImagen= subirImagen(imagen);
        if(nombreImagen!=null){
            imagenes.add(nombreImagen);
            System.out.println(nombreImagen);
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
            if(usuarioSesion!=null) {
                if (!imagenes.isEmpty()) {
                    //Usuario usuario = usuarioServicio.obtenerUsuario("9990");
                    /**
                     * prgunta profesor me toca quemar las imagenes porque no se guarda automaticamente
                     */
                    producto.setImagen(imagenes);
                    producto.setUsuario(usuarioSesion);
                    producto.setFechaLmite(LocalDateTime.now().plusMonths(1));
                    productoServicio.publicarProducto(producto);

                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro exitoso");
                    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                    return "producto_creado?faces-redutect=true";
                    //return "producto_creado";
                } else {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Se necesita mas de una imagen");
                    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                }
            }
        } catch (Exception e) {
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            e.printStackTrace();
        }
        return null;
    }

    public List<SelectItem> getListaDepartamentos(){
        listaDepartamentos = new ArrayList<SelectItem>();
        List<Departamento> depto=ciudadServicio.obtenerDepartamento();
        listaDepartamentos.clear();
        for(Departamento ciaa: depto){
            SelectItem ciuItem= new SelectItem(ciaa.getCodigo(),ciaa.getNombre());
            this.listaDepartamentos.add(ciuItem);

        }
        return listaDepartamentos;
    }

    public List<Ciudad> getListaCiudadesporDepartamento1(AjaxBehaviorEvent e) throws Exception{

        ciudades = new ArrayList<Ciudad>();

        List<Ciudad> depto=ciudadServicio.obtenerCiudadPorDepartamento(indexDepto);
        ciudades.clear();
        for(Ciudad ciaa: depto){
            SelectItem ciuItem= new SelectItem(ciaa.getCodigo(),ciaa.getNombre());
            this.ciudades.add(ciaa);

        }
        return ciudades;
    }
}
