package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {

    @Query("select u from Usuario  u where u.nombre= :nombre")
    List<Usuario> obtenerUsuarioNombre(String nombre);

    List<Usuario> findAllByNombre(String nombre);

    List<Usuario> findAllByNombreContaining(String nombre);

    @Query("select u from Usuario u where u.email= :email and u.contrasena= :contrasena" )
    Optional<Usuario> verificarAutenticacion( String email, String contrasena);

    Optional<Usuario> findByEmailAndContrasena(String email, String contrasena);



    Page<Usuario> findAll(Pageable paginador);


}
