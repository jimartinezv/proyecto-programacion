package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{
    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        return buscarUsuario(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {
        try{
            Optional<Usuario> buscado= usuarioRepo.findByDocumento(u.getDocumento());

            System.out.println("Se está actualizando el usuario "+ u.getUserName());
            if(!buscado.isPresent())
                throw new Exception("Codigo de usuario no existente");
            System.out.println("Se actualizó");
            System.out.println("el usuario :( " +(u.getDocumento()+u.getNombre()+u.getEmail()+u.getDireccion()+u.getContrasena()));
            return usuarioRepo.save(u);



        }catch (Exception e){
            //throw new Exception(e.getMessage()+" Error al guardar el usuario");
            return null;

        }


    }

    private Usuario buscarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado= usuarioRepo.findByDocumento(u.getDocumento());
        if(buscado.isPresent())
            throw new Exception("El codigo del usuario ya existe");

        buscado=buscarPorEmail(u.getEmail());

        buscado= usuarioRepo.findByUserName(u.getUserName());
        if(buscado.isPresent())
            throw new Exception("Nombre de usuario existente por favor elija otro");
        return usuarioRepo.save(u);
    }

    private Optional<Usuario> buscarPorEmail(String email) throws Exception{
        if (usuarioRepo.findByEmail(email).isPresent())
            throw  new Exception("El correo ya existe");
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado= usuarioRepo.findByDocumento(codigo);
        if(!buscado.isPresent())
            throw new Exception("El codigo del usuario no existe" + codigo);
        System.out.println("Se va a eliminar el usuario "+ buscado.get().getUserName());
        usuarioRepo.deleteUsuarioByDocumento(codigo);

    }

    @Override
    public List<Usuario> listarUsuarios()  {
        List<Usuario> usuarios= usuarioRepo.findAll();
        return usuarios;
    }

    @Override
    public List<Producto> listaFavotitos(String email)  throws Exception{
        Optional<Usuario> buscado= buscarPorEmail(email);
        if(buscado.isPresent())
            throw new Exception("El correo no existe");
       return usuarioRepo.obtenerProductosFavoritos(email);

    }

    @Override
    public Usuario obtenerUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado= usuarioRepo.findByDocumento(codigo);
        if(buscado.isEmpty())
            throw new Exception("El codigo del usuario no existe");
        return buscado.get();
    }



    @Override
    public Usuario login(String email, String password) throws Exception {
        System.out.println("alguien se esta logueando: e "+email+" p: "+password);
       // return usuarioRepo.findByContrasenaAndAndEmailOruOrUserName(password, email).orElseThrow(()-> new Exception("Los datos son incorrectos"));
       return usuarioRepo.loguearseEmailOrUsername(password, email).orElseThrow(()-> new Exception("Los datos son incorrectos"));
        //return usuarioRepo.findByContrasenaAndAndEmail(password, email).orElseThrow(()-> new Exception("Los datos son incorrectos"));

    }


    @Override
    public void recuperarContrasena(String email) {

    }
}
