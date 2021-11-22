package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;

@Controller
@ViewScoped
@Getter
@Setter
public class EjemploComboBean {
    @Getter @Setter
    private String nombre, departamento, ciudad;
    private ArrayList<String> departamentos;
    private ArrayList<String> ciudades;

    @PostConstruct
    public void incializar() {
        this.departamentos= new ArrayList<String>();
        departamentos.add("Quindio");
        departamentos.add("Valle del cauca");
        this.ciudades= new ArrayList<String>();


    }

    public void bean(){

    }

    public void ciudades(AjaxBehaviorEvent e){
        switch (departamento){
            case "Quindio":
                ciudades.clear();
                ciudades.add("Armenia");
                ciudades.add("Calarcá");
                ciudades.add("Salento");
                break;
            case "Valle del cauca":
                ciudades.clear();
                ciudades.add("Cali");
                ciudades.add("Palmira");
                ciudades.add("Tulua");
        }
    }

}
