package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {
    @Query("select c from Comentario c where c.calificacion >= :calificacionMenor and c.calificacion<=:calificacionMayor")
    List<Comentario> listarComentariosRango(int calificacionMenor, int calificacionMayor);

    @Query("select c from Comentario c where c.calificacion  between :calificacionMenor and :calificacionMayor")
    List<Comentario> listarComentariosRango2(int calificacionMenor, int calificacionMayor);
}
