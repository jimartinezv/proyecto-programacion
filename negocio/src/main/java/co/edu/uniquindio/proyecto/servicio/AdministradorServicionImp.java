package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServicionImp implements AdministradorServicio{

    @Autowired
    AdministradorRepo administradorRepo;

    @Autowired
    CiudadRepo ciudadRepo;

    @Autowired
    DepartamentoRepo departamentoRepo;

    @Autowired
    CategoriaRepo categoriaRepo;

    @Autowired
    MedioPagoRepo medioPagoRepo;

    @Override
    public Administrador login(String email, String password) throws Exception {
        System.out.println("alguien se esta logueando: e "+email+" p: "+password);
        // return usuarioRepo.findByContrasenaAndAndEmailOruOrUserName(password, email).orElseThrow(()-> new Exception("Los datos son incorrectos"));
        return administradorRepo.login(email,password).orElseThrow(()-> new Exception("Los datos son incorrectos"));
        //return usuarioRepo.loguearseEmailOrUsername(password, email).orElseThrow(()-> new Exception("Los datos son incorrectos"));
        //return usuarioRepo.findByContrasenaAndAndEmail(password, email).orElseThrow(()-> new Exception("Los datos son incorrectos"));

    }

    @Override
    public void crearCiudad(String nombre, Departamento departamento) {
        Ciudad c= new Ciudad(nombre, departamento);
        ciudadRepo.save(c);
    }

    @Override
    public void crearDepartameto(String nombre) {
        Departamento d= new Departamento(nombre);
        departamentoRepo.save(d);

    }


    @Override
    public void crearAdmins() {

    }

    @Override
    public void crearMetodosPago(String nombre) {
        MedioPago m= new MedioPago(nombre);

        medioPagoRepo.save(m);
    }

    @Override
    public void crearCategorias(String nombre) {

        Categoria c= new Categoria(nombre);
        categoriaRepo.save(c);
    }


}
