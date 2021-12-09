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
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
@ViewScoped
public class UsuarioBeanCopia implements Serializable {

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
    private List<Ciudad> ciudades;

    @Setter @Getter
    private Departamento depto;

    @Getter @Setter
    private List<Ciudad> listaCiudades;

    private List<Departamento> listaDepartamentos;

    @Getter @Setter
    private String hora;




    @PostConstruct
    public void inicializar(){
        this.hora= LocalDateTime.now()+"";

        this.usuario = new Usuario();


        ciudad= new Ciudad();
        listaDepartamentos= ciudadServicio.obtenerDepartamento();

    }

    /**
     * En este método se guarda la información del nuevo usuario creado
     * buscando primero la ciudad y agrgandola al usuario creado
     * @return
     */
    public String registrarUsuario(){
        try {
            usuario.setNombre("el pepe");
            ciudad=buscarCiudadUsuario();
            usuario.setCiudad(ciudad);
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

    /**
     * Obtiene la lista de las ciudades dependiendo el departamento
     * @return
     */
    public List<Ciudad> getListaCiudadesporDepartamento(AjaxBehaviorEvent e) throws Exception{
        System.out.println("papi");
        listaCiudades = new ArrayList<Ciudad>();
        System.out.println(ciudadServicio.obtenerCiudadPorDepartamento(indexDepto));
        List<Ciudad> depto=ciudadServicio.obtenerCiudadPorDepartamento(indexDepto);
        listaCiudades.clear();
        for(Ciudad ciaa: depto){
            Ciudad ciuItem= ciaa;
            this.listaCiudades.add(ciuItem);

        }
        return listaCiudades;
    }

    public List<Departamento> getListaDepartamentos(){
        listaDepartamentos = new ArrayList<Departamento>();
        List<Departamento> depto=ciudadServicio.obtenerDepartamento();
        listaDepartamentos.clear();
        for(Departamento ciaa: depto){
            Departamento ciuItem= ciaa;
            this.listaDepartamentos.add(ciuItem);

        }
        return listaDepartamentos;
    }


    public Ciudad buscarCiudadUsuario() throws Exception{

        return ciudadServicio.buscarCiudadPorCodigo(indexCiudad);
    }
    public Ciudad buscarDepartamento() throws Exception{

        return ciudadServicio.buscarCiudadPorCodigo(indexCiudad);
    }


}
