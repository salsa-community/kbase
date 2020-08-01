package com.github.danimaniarqsoft.repository;
import com.github.danimaniarqsoft.domain.Evento;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Evento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {

}
