package com.github.danimaniarqsoft.repository;
import com.github.danimaniarqsoft.domain.Contexto;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Contexto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContextoRepository extends MongoRepository<Contexto, String> {

}
