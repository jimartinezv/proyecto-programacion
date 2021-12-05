package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepo extends JpaRepository<TipoDocumento, Integer> {

}
