package com.github.danimaniarqsoft.repository;

import java.util.Optional;

import com.github.danimaniarqsoft.domain.MensajeError;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the MensajeError entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MensajeErrorRepository extends MongoRepository<MensajeError, String> {

    Optional<MensajeError> findByClave(String clave);

    Page<MensajeError> findByClaveLikeIgnoreCase(String codigo, Pageable pageable);

}
