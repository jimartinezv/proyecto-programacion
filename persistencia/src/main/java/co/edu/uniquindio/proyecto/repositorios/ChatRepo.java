package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Integer> {
    /**
     * En este punto en la clase chat se crean dos variables
     * para el codigo del comprador y el codigo del vendedor
     *
     * @return
     */
    @Query("select c from Chat c ,IN (c.vendedor) v")
    List<Object[]> listarChatVendedor();
}
