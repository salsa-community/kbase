package com.github.danimaniarqsoft.repository;
import com.github.danimaniarqsoft.domain.Archivo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Archivo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArchivoRepository extends MongoRepository<Archivo, String> {

}
