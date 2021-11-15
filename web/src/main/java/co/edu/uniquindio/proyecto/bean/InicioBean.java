package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import org.springframework.stereotype.Controller;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Controller
@ViewScoped
public class InicioBean implements Serializable {
    @Getter
    private String mensaje="Mi primer codigo java";
}
