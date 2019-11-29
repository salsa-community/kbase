package com.github.danimaniarqsoft.repository;
import com.github.danimaniarqsoft.domain.Instruccion;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Instruccion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstruccionRepository extends MongoRepository<Instruccion, String> {

}
