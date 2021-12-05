package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import java.io.Serializable;

@Component
public class CategoriaConverter implements Converter<Categoria>, Serializable {
    @Autowired
    ProductoServicio productoServicio;

    @Override
    public Categoria getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.out.println("aqui");
        Categoria categoria=null;

        try {
            categoria= productoServicio.buscarCategoria(Integer.parseInt(s));


        }catch (Exception e){
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Categoria categoria) {
        System.out.println("mor");
        if(categoria!=null){
            System.out.println("mor if");
            return  categoria.getCodigo().toString();

        }
        System.out.println("mor pailas");
        return "";
    }
}
