package co.edu.uniquindio.proyecto.converter;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.TipoDocumento;
import co.edu.uniquindio.proyecto.servicio.CiudadServicio;
import co.edu.uniquindio.proyecto.servicio.TipoDocumentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class TipoDocumentoConverter implements Converter<TipoDocumento>, Serializable {
    @Autowired
    CiudadServicio ciudadServicio;

    TipoDocumentoServicio tipoDocumentoServicio;

    @Override
    public TipoDocumento getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        TipoDocumento td=null;
        try {
            System.out.println("Aqui pase");
            td= tipoDocumentoServicio.documento(Integer.parseInt(s));

        }catch (Exception e){
            e.printStackTrace();
        }
        return td;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, TipoDocumento tipoDocumento) {
        if(tipoDocumento!=null){
            return  tipoDocumento.getCodigo().toString();
        }
        return "";
    }
}
