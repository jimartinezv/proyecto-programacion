package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Departamento;

public interface AdministradorServicio {

    Administrador login(String email, String password) throws Exception;
    void crearCiudad(String nombre, Departamento departamento);
    void crearDepartameto(String nombre);
    void crearAdmins();
    void crearMetodosPago(String nombre);
    void crearCategorias(String nomre);

}
