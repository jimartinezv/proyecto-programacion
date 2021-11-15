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
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ViewScoped
public class UsuarioBean implements Serializable {

    @Getter @Setter
    private Usuario usuario;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;
    @Getter
    private String login= logearse();

    //@Getter @Setter
    //private String city;
    @Getter @Setter
    private Map<Integer, String> cities = new HashMap<>();

    @PostConstruct
    public void inicializar(){
        this.usuario = new Usuario();
        this.cities=mostrarCiudades();
    }

    public void registrarUsuario(){
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception e) {
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            e.printStackTrace();
        }
    }

    public Map<Integer,String> mostrarCiudades(){
        Map<Integer,String> c= new HashMap<>();
        List<Ciudad>ciudades=ciudadServicio.obtenerCiudades();
        ciudades.forEach(e-> c.put(e.getCodigo(),e.getNombre()));
        return c;
    }

    public String logearse(){
        String p="";
        for (int i=1;i<=5;i++){
            p+="<h"+i+"> Este es el ciclo numero: "+i+"</h"+i+">";
        }
        return p;
    }
}
