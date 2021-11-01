package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SubastaRepo extends JpaRepository<Subasta, Integer> {

    //La lista de subastas de una categoría específica. Tenga en cuenta que solo debe mostrar
    //las subastas que aún están disponibles (validar fecha).
    LocalDateTime l= LocalDateTime.now();
    @Query("select s from Subasta  s, IN (s.producto.categoria) c where c.codigo=:id and s.fechaLimite> :l")
    List<Subasta> listarSubastaCategoria(Integer id);
}
