package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    @Query("select c from Categoria c  ")
    List<Categoria> listaCategorias();

    //Una lista con las categorías y su calificación promedio. Ordene la lista de mayor a menor
    //de acuerdo a la calificación promedio. (incluya todos las categorías así no tenga productos
    //con calificaciones)

    @Query("select avg (s.calificacion) , c.nombre from Categoria c, IN (c.producto) p, IN (p.comentario) s group by c.codigo")
    //@Query("select (s.calificacion) , c.nombre from Categoria c, IN (c.producto) p, IN (p.comentario) s ")
    List<Object[]> listarCategoriasPorCalificacion();


}
