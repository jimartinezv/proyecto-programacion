package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.AdministradorServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

@Scope("session")
@Component
public class SeguridadAdminBean implements Serializable {

    @Getter @Setter
    private String email,password, categoriaNombre, departamentoNombre, metodoPagoNombre;

    @Getter @Setter
    private Administrador usuarioSesion;

    @Getter @Setter
    private Boolean autenticado;


    @Autowired
    private AdministradorServicio administradorServicio;

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

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        autenticado=false;
        usuarioSesion=null;
        return "/w-admin/index?faces-redirect=true";
    }
}
