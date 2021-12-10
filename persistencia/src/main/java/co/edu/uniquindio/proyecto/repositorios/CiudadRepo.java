package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    Optional<Ciudad> findByNombre(String nombreCiudad);

    @Query("select u from Ciudad c join c.usuario u where c.nombre= :nombre ")
    List<Usuario> listaUsuarios(String nombre);
    @Query("select u from Usuario u")
    List<Usuario> listaUsuariosTodos();

    @Query("select c from Ciudad c ")
    List<Ciudad> listarCiudades();

    @Query("select c from Ciudad c where c.codigo=:id")
    Ciudad obtenerCiudadPorCodigo(Integer id);

    @Query("select c from Ciudad c where c.departamento.codigo=:id")
    List<Ciudad> ciudadesPorDepartamento(Integer id);

    @Query("select d from Departamento  d where d.codigo=:id")
    Optional<Departamento> obtenerDepartamento(Integer id);

    /**
     * Sentencia que trae los departamentos
     */
    @Query("select d from Departamento d")
    List<Departamento> listarDepartamentos();

    List<Ciudad> findCiudadByDepartamento(Integer id);


}
