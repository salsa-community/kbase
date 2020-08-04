package com.github.danimaniarqsoft.repository;
import com.github.danimaniarqsoft.domain.Actividad;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Actividad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActividadRepository extends MongoRepository<Actividad, String> {

      long countByEvento(String evento);

}
