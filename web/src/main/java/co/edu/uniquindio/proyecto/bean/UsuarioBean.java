package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private Integer indexCiudad, indexDepto;

    @Setter @Getter
    private Ciudad ciudad;

    @Setter @Getter
    private Departamento departamento;



    @Getter @Setter
    private List<SelectItem>  listaDepartamentos;

    @Getter @Setter
    private List<Ciudad> ciudads;



    @Getter @Setter
    private String hora;


    /**
     * Metodo que se inicializa cuando se llama la clase
     * variables hora para la hora de creación del producto
     * se inicializa el usuario, el departamento y la ciudad
     */
    @PostConstruct
    public void inicializar(){
        this.hora= LocalDateTime.now()+"";

        this.usuario = new Usuario();

        ciudad= new Ciudad();


    }

    /**
     * En este método se guarda la información del nuevo usuario creado
     * buscando primero la ciudad y agrgandola al usuario creado
     * @return
     */
    public String registrarUsuario(){
        try {

            System.out.println(usuario.getDocumento());
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Registro exitoso");
            FacesContext.getCurrentInstance().addMessage("msj-bean", facesMessage);
            return "/index.xhtml";
        } catch (Exception e) {
            FacesMessage facesMessage= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", facesMessage);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene la lista de las ciudades dependiendo el departamento
     * @return
     */
    public List<Ciudad> getListaCiudadesporDepartamento1(AjaxBehaviorEvent e) throws Exception{
        System.out.println("papi");
        ciudads = new ArrayList<Ciudad>();

        List<Ciudad> depto=ciudadServicio.obtenerCiudadPorDepartamento(indexDepto);
        ciudads.clear();
        for(Ciudad ciaa: depto){
            SelectItem ciuItem= new SelectItem(ciaa.getCodigo(),ciaa.getNombre());
            this.ciudads.add(ciaa);

        }
        return ciudads;
    }

    /**
     * Se listan los departamentos y se hace la transformación a selectItem
     * @return
     */
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

    public void editarUsuario(Usuario u){
        //System.out.println("editando: "+ nombre+email+password+direccion+username);
        try{
            Usuario actualizado= u;
            actualizado.setNombre(u.getNombre());
            actualizado.setEmail(u.getEmail());
            actualizado.setContrasena(u.getContrasena());

            actualizado.setUserName(u.getUserName());
            usuarioServicio.actualizarUsuario(actualizado);
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Usuario actualizado");
            FacesContext.getCurrentInstance().addMessage("update-bean", fm);
        }catch (Exception e){
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("update-bean", fm);
        }
    }



}