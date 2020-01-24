package com.github.danimaniarqsoft.repository;
import com.github.danimaniarqsoft.domain.Paso;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Paso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PasoRepository extends MongoRepository<Paso, String> {

}
