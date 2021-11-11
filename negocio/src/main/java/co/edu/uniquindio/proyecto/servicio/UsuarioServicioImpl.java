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
        return buscarUsuario(u);
    }

    private Usuario buscarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado= usuarioRepo.findByCodigo(u.getCodigo());
        if(buscado.isPresent())
            throw new Exception("El codigo del usuario ya existe");

        buscado=buscarPorEmail(u.getEmail());

        buscado= usuarioRepo.findByUserName(u.getUserName());
        if(buscado.isPresent())
            throw new Exception("Nombre de usuario existente por favor elija otro");
        return usuarioRepo.save(u);
    }

    private Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado= usuarioRepo.findByCodigo(codigo);
        if(buscado.isPresent())
            throw new Exception("El codigo del usuario no existe");
        usuarioRepo.deleteUsuarioByCodigo(codigo);

    }

    @Override
    public List<Usuario> listarUsuarios() throws Exception {
        return null;
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
        Optional<Usuario> buscado= usuarioRepo.findByCodigo(codigo);
        if(buscado.isEmpty())
            throw new Exception("El codigo del usuario no existe");
        return buscado.get();

    }
}
