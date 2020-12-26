package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Usine;
import com.mycompany.myapp.service.UsineService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Usine}.
 */
@RestController
@RequestMapping("/api")
public class UsineResource {

    private final Logger log = LoggerFactory.getLogger(UsineResource.class);

    private static final String ENTITY_NAME = "usine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsineService usineService;

    public UsineResource(UsineService usineService) {
        this.usineService = usineService;
    }

    /**
     * {@code POST  /usines} : Create a new usine.
     *
     * @param usine the usine to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usine, or with status {@code 400 (Bad Request)} if the usine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/usines")
    public ResponseEntity<Usine> createUsine(@Valid @RequestBody Usine usine) throws URISyntaxException {
        log.debug("REST request to save Usine : {}", usine);
        if (usine.getId() != null) {
            throw new BadRequestAlertException("A new usine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Usine result = usineService.save(usine);
        return ResponseEntity.created(new URI("/api/usines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /usines} : Updates an existing usine.
     *
     * @param usine the usine to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usine,
     * or with status {@code 400 (Bad Request)} if the usine is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usine couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/usines")
    public ResponseEntity<Usine> updateUsine(@Valid @RequestBody Usine usine) throws URISyntaxException {
        log.debug("REST request to update Usine : {}", usine);
        if (usine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Usine result = usineService.save(usine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, usine.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /usines} : get all the usines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of usines in body.
     */
    @GetMapping("/usines")
    public ResponseEntity<List<Usine>> getAllUsines(Pageable pageable) {
        log.debug("REST request to get a page of Usines");
        Page<Usine> page = usineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /usines/:id} : get the "id" usine.
     *
     * @param id the id of the usine to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usine, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/usines/{id}")
    public ResponseEntity<Usine> getUsine(@PathVariable Long id) {
        log.debug("REST request to get Usine : {}", id);
        Optional<Usine> usine = usineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(usine);
    }

    /**
     * {@code DELETE  /usines/:id} : delete the "id" usine.
     *
     * @param id the id of the usine to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/usines/{id}")
    public ResponseEntity<Void> deleteUsine(@PathVariable Long id) {
        log.debug("REST request to delete Usine : {}", id);
        usineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
