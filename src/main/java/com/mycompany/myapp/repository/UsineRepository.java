package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Usine;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Usine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsineRepository extends JpaRepository<Usine, Long> {
}
