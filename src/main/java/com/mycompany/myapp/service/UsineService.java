package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Usine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Usine}.
 */
public interface UsineService {

    /**
     * Save a usine.
     *
     * @param usine the entity to save.
     * @return the persisted entity.
     */
    Usine save(Usine usine);

    /**
     * Get all the usines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Usine> findAll(Pageable pageable);


    /**
     * Get the "id" usine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Usine> findOne(Long id);

    /**
     * Delete the "id" usine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
