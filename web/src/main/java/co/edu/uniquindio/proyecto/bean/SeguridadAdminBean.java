package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.AdministradorServicio;
import co.edu.uniquindio.proyecto.servicio.CiudadServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Scope("session")
@Component
public class SeguridadAdminBean implements Serializable {

    @Getter @Setter
    private String email,password, categoriaNombre, departamentoNombre, metodoPagoNombre;

    @Setter @Getter
    private String ciudad;

    @Getter @Setter
    private Integer indexDepto;

    @Getter @Setter
    private Administrador usuarioSesion;

    @Getter @Setter
    private Boolean autenticado;

    @Getter @Setter
    private Departamento departamento;

    @Getter @Setter
    private List<SelectItem>  listaDepartamentos;

    @Getter @Setter
    private List<Ciudad> ciudads;

    @Autowired
    CiudadServicio ciudadServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @PostConstruct
    public void iniciar(){
        ciudad= "";
    }

    public String iniciarSesion(){

        if(!email.isEmpty() && !password.isEmpty()){
            try {


                usuarioSesion=administradorServicio.login(email,password);
                autenticado=true;
                //productosUsuario= verProductosUsuario();
                //email=usuarioSesion.getEmail();
                //nombre=usuarioSesion.getNombre();
                //direccion=usuarioSesion.getDireccion();
                //username=usuarioSesion.getUserName();

                return "/w-admin/index?faces-redirect=true";
            }catch (Exception e){
                System.out.println("Yo no se naa");
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
                System.out.println(e.getMessage());


            }


        }
        return null;
    }

    public List<Ciudad> getListaCiudadesporDepartamento1(AjaxBehaviorEvent e) throws Exception{
        System.out.println("papi"+ indexDepto);
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

    public void crearCategoria(){
        administradorServicio.crearCategorias(categoriaNombre.toUpperCase());
        categoriaNombre="";
    }

    public void crearDepartamento(){
        administradorServicio.crearDepartameto(departamentoNombre.toUpperCase());
        departamentoNombre="";
    }

    public void crearMetodoPago(){
        administradorServicio.crearMetodosPago(metodoPagoNombre.toUpperCase());
        metodoPagoNombre="";
    }

    public void crearCiudad(){
        System.out.println(indexDepto+" Departamento");
        Departamento d= ciudadServicio.obtenerDepartamento(indexDepto).get();
        administradorServicio.crearCiudad(ciudad, d);
        ciudad="";
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        autenticado=false;
        usuarioSesion=null;
        return "/w-admin/index?faces-redirect=true";
    }
}
