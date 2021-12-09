package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Producto;
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
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private List<Producto> productosUsuario;

    @Getter @Setter
    private Producto productoEditado;


    @Getter @Setter
    private float subtotal;

    @Getter @Setter
    private Integer unidadesDisponibles;



    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    ProductoServicio productoServicio;

    @PostConstruct
    public void inicializar() throws Exception {
        this.subtotal=0F;
        this.productosCarrito=new ArrayList<>();
        this.productosUsuario= verProductosUsuario();
        //System.out.println(productoServicio.listarProductorUsuario("1094908238").size()+" Tamaño");



    }


    public String cargarProductos(){
        try {
            this.productosUsuario = verProductosUsuario();
            return "/usuario/mis_productos.xhtml";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "#";
    }
    public List<Producto> verProductosUsuario() throws Exception  {
        try {
            System.out.println("Estamos buscando productos del usuario");
            productosUsuario=productoServicio.listarProductorUsuario(usuarioSesion.getDocumento());
            return  productosUsuario;
        }catch (Exception e){
            System.out.println(e.getMessage()+" Error");
            return null;
        }

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

    public String cargarProductoParaEditar(Integer p){
        try {
            //p= productoServicio.obtenerProducto(Integer.parseInt(codigoProducto));
            System.out.println("Estamos buscando el producto"+p);
            //System.out.println(productoEditado.getCodigo()+"Buscando p");

            productoEditado = productoServicio.obtenerProducto(p);
            return "/usuario/editar_producto?faces-redirect=true&amp;producto=" + productoEditado.getCodigo();
        }catch (Exception e){
            e.printStackTrace();
            return "/usuario/editar_producto.xhrml";
        }
    }

    public String editarProducto(Integer id) throws Exception {

        System.out.println("editando: "+ productoEditado.getCodigo());



        productoEditado= productoServicio.obtenerProducto(id);
        return "/usuario/editar_producto?faces-redirect=true&amp;producto="+id;
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
                productosUsuario= verProductosUsuario();
                email=usuarioSesion.getEmail();
                nombre=usuarioSesion.getNombre();
                direccion=usuarioSesion.getDireccion();
                username=usuarioSesion.getUserName();

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
        productosUsuario.clear();
        usuarioSesion=null;
        return "/index?faces-redirect=true";
    }
    public String cambiarDato(){
        return "index?faces-redirect=true";
    }


}
