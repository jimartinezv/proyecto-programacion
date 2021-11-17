package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.CiudadServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Controller
@ViewScoped
public class UsuarioBean implements Serializable {

    @Getter @Setter
    private Usuario usuario;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private Integer labeled;

    @Setter @Getter
    private Ciudad ciu;

    @Getter @Setter
    private List<SelectItem> listaCiudades;

    public List<SelectItem> getListaCiudades(){
        this.listaCiudades = new ArrayList<SelectItem>();
        List<Ciudad> ciudades=ciudadServicio.obtenerCiudades();
        listaCiudades.clear();
        for(Ciudad ciaa: ciudades){
            SelectItem ciuItem= new SelectItem(ciaa.getCodigo(),ciaa.getNombre());
            this.listaCiudades.add(ciuItem);

        }


        return listaCiudades;
    }

    @PostConstruct
    public void inicializar(){
        System.out.println("Lo primero que se sabe es :"+this.labeled);

        this.usuario = new Usuario();
        ciu= new Ciudad();

    }

    public String registrarUsuario(){
        try {
            ciu=buscarCiudadUsuario();
            usuario.setCiudad(ciu);
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return "producto_creado";
        } catch (Exception e) {
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            e.printStackTrace();
        }
        return null;
    }

    public Ciudad buscarCiudadUsuario() throws Exception{

        return ciudadServicio.buscarCiudadPorCodigo(labeled);
    }



}
