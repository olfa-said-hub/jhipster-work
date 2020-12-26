package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.UsineService;
import com.mycompany.myapp.domain.Usine;
import com.mycompany.myapp.repository.UsineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Usine}.
 */
@Service
@Transactional
public class UsineServiceImpl implements UsineService {

    private final Logger log = LoggerFactory.getLogger(UsineServiceImpl.class);

    private final UsineRepository usineRepository;

    public UsineServiceImpl(UsineRepository usineRepository) {
        this.usineRepository = usineRepository;
    }

    @Override
    public Usine save(Usine usine) {
        log.debug("Request to save Usine : {}", usine);
        return usineRepository.save(usine);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usine> findAll(Pageable pageable) {
        log.debug("Request to get all Usines");
        return usineRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Usine> findOne(Long id) {
        log.debug("Request to get Usine : {}", id);
        return usineRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Usine : {}", id);
        usineRepository.deleteById(id);
    }
}
