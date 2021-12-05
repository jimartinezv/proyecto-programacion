package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.servicio.CiudadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

/**
 * Esta clase se crea para hacer una conversión de datos de web al programa java
 */
@Component
public class CiudadConverter implements Converter<Ciudad>, Serializable {

   @Autowired
    CiudadServicio ciudadServicio;

    @Override
    public Ciudad getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.out.println("aqui");
        Ciudad ciudad=null;
        try {

            ciudad=ciudadServicio.buscarCiudadPorCodigo(Integer.parseInt(s));

        }catch (Exception e){
            e.printStackTrace();
        }
        return ciudad;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Ciudad ciudad) {
        System.out.println("mor");
        if(ciudad!=null){
            System.out.println("mor if");
            return  ciudad.getCodigo().toString();

        }
        System.out.println("mor pailas");
        return "";
    }
}