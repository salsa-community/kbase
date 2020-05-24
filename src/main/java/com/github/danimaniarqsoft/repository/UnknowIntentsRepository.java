package com.github.danimaniarqsoft.repository;
import com.github.danimaniarqsoft.domain.UnknowIntents;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the UnknowIntents entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnknowIntentsRepository extends MongoRepository<UnknowIntents, String> {

}
