package com.github.danimaniarqsoft.repository;
import com.github.danimaniarqsoft.domain.Contacto;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Contacto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactoRepository extends MongoRepository<Contacto, String> {

}
