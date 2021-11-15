package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    Optional<Ciudad> findByNombre(String nombreCiudad);

    @Query("select u from Ciudad c join c.usuario u where c.nombre= :nombre ")
    List<Usuario> listaUsuarios(String nombre);
    @Query("select u from Usuario u")
    List<Usuario> listaUsuariosTodos();

    @Query("select c from Ciudad c")
    List<Ciudad> listarCiudades();

}
