package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Controller
@ViewScoped
@Getter
@Setter
public class EjemploBean implements Serializable {
    private String atributoUno, atributoDos;

    public void cambiar(){
        String aux= atributoUno;
        atributoUno=atributoDos;
        atributoDos=aux;
    }
}
