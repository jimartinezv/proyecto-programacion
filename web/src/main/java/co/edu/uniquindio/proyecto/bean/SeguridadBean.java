package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * El alcance de la aplicación mantiene las variables durante toda la sesión
 * @ApplicationScope
 */

/**
 * Scope para alcance de sesion
 */
@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private String valor;

    @Getter @Setter
    private Boolean autenticado;

    @Getter @Setter
    private String email,password, nombre,direccion, username;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private float subtotal;

    @Getter @Setter
    private Integer unidadesDisponibles;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    ProductoServicio productoServicio;

    @PostConstruct
    public void inicializar(){
        this.subtotal=0F;
        this.productosCarrito=new ArrayList<>();



    }

    public void agregarAlCarrito(Integer id, float precio, String nombre, String imagen, Integer unidades) {
        ProductoCarrito pc= new ProductoCarrito(id, nombre,imagen,1,unidades,precio);
        if(!productosCarrito.contains(pc)) {
            productosCarrito.add(pc);
            subtotal+=precio;
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-car", fm);
        }


    }

    public void eliminarDelCarrito(int p){
        subtotal-=productosCarrito.get(p).getPrecio();
        productosCarrito.remove(p);


    }

    public void actualizarSubtotal(){
        subtotal=0;
        for (ProductoCarrito p:productosCarrito) {
            subtotal+=p.getPrecio()*p.getUnidades();

        }
    }

    public void editarUsuario(){
        System.out.println("editando: "+ nombre+email+password+direccion+username);
        try{
            Usuario actualizado= usuarioSesion;
            usuarioSesion.setNombre(nombre);
            usuarioSesion.setEmail(email);
            usuarioSesion.setContrasena(password);

            usuarioSesion.setUserName(username);
            usuarioServicio.actualizarUsuario(usuarioSesion);
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Usuario actualizado");
            FacesContext.getCurrentInstance().addMessage("update-bean", fm);
        }catch (Exception e){
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("update-bean", fm);
        }

    }

    /**
     * Metodo que accede y verifica si los datos de loging son correctos y crea una sesion nueva
     * @return
     */
    public String iniciarSesion(){

        if(!email.isEmpty() && !password.isEmpty()){
            try {


                usuarioSesion=usuarioServicio.login(email,password);
                autenticado=true;

                return "/index?faces-redirect=true";
            }catch (Exception e){
                System.out.println("Yo no se naa");
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
                System.out.println(e.getMessage());


            }


        }
        return null;
    }

    public void comprar(){
        if(usuarioSesion!=null && !productosCarrito.isEmpty()){
            try {
                //Comprobar que las unidades
                productoServicio.comprarProductos(usuarioSesion, productosCarrito,"PSE");
                productosCarrito.clear();
                subtotal=0F;
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Se ha efectuado la compra");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }catch (Exception e){
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }

        }

    }

    /**
     * Metodo que invalida la sesion y por lo tanto se cierra y se elimina toodo de la sesion utilizada
     * @return
     */
    public String cerrarSession(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
    public String cambiarDato(){
        return "index?faces-redirect=true";
    }


}
