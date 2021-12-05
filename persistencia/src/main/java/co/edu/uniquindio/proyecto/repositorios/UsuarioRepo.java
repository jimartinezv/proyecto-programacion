package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.TipoDocumento;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {




    List<Usuario> findAllByNombreContaining(String nombre);

    Page<Usuario> findAll(Pageable paginador);

    @Query("select p from Producto p, IN (p.favoritoUsuario) u where u.email=:email")
    List<Producto> obtenerProductosFavoritos(String email);

    @Query("select u.email, p from Usuario u left join u.producto p")
    List<Object[]> obtenerTodosUsuarios();

    //Dado el código de una subasta, devolver el usuario ganador de dicha subasta.
    @Query("select max(s.valor),s.usuario.nombre from Usuario u, IN(u.subastaUsuario) s, IN(s.subasta) su  where su.codigo =:id")
    List<Object[]> usuarioGandorSubasta(Integer id);

    //Eliminar usuario por codigo
    void deleteUsuarioByDocumento(String codigo);

    Optional<Usuario> findByDocumento(String codigo);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUserName(String userName);

    @Query("Select u from Usuario u where (u.email=:emailOrUsername or u.userName=:emailOrUsername) and u.contrasena=:clave")
    Optional<Usuario> loguearseEmailOrUsername(String clave, String emailOrUsername);

    @Query("select td from TipoDocumento td ")
    List<TipoDocumento> listarTipoDocumento();

}
