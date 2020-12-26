package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.GestionUsineApp;
import com.mycompany.myapp.domain.Usine;
import com.mycompany.myapp.repository.UsineRepository;
import com.mycompany.myapp.service.UsineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UsineResource} REST controller.
 */
@SpringBootTest(classes = GestionUsineApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UsineResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_PAYS = "BBBBBBBBBB";

    @Autowired
    private UsineRepository usineRepository;

    @Autowired
    private UsineService usineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsineMockMvc;

    private Usine usine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usine createEntity(EntityManager em) {
        Usine usine = new Usine()
            .nom(DEFAULT_NOM)
            .adresse(DEFAULT_ADRESSE)
            .pays(DEFAULT_PAYS);
        return usine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usine createUpdatedEntity(EntityManager em) {
        Usine usine = new Usine()
            .nom(UPDATED_NOM)
            .adresse(UPDATED_ADRESSE)
            .pays(UPDATED_PAYS);
        return usine;
    }

    @BeforeEach
    public void initTest() {
        usine = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsine() throws Exception {
        int databaseSizeBeforeCreate = usineRepository.findAll().size();
        // Create the Usine
        restUsineMockMvc.perform(post("/api/usines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usine)))
            .andExpect(status().isCreated());

        // Validate the Usine in the database
        List<Usine> usineList = usineRepository.findAll();
        assertThat(usineList).hasSize(databaseSizeBeforeCreate + 1);
        Usine testUsine = usineList.get(usineList.size() - 1);
        assertThat(testUsine.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testUsine.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testUsine.getPays()).isEqualTo(DEFAULT_PAYS);
    }

    @Test
    @Transactional
    public void createUsineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usineRepository.findAll().size();

        // Create the Usine with an existing ID
        usine.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsineMockMvc.perform(post("/api/usines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usine)))
            .andExpect(status().isBadRequest());

        // Validate the Usine in the database
        List<Usine> usineList = usineRepository.findAll();
        assertThat(usineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = usineRepository.findAll().size();
        // set the field null
        usine.setNom(null);

        // Create the Usine, which fails.


        restUsineMockMvc.perform(post("/api/usines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usine)))
            .andExpect(status().isBadRequest());

        List<Usine> usineList = usineRepository.findAll();
        assertThat(usineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = usineRepository.findAll().size();
        // set the field null
        usine.setAdresse(null);

        // Create the Usine, which fails.


        restUsineMockMvc.perform(post("/api/usines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usine)))
            .andExpect(status().isBadRequest());

        List<Usine> usineList = usineRepository.findAll();
        assertThat(usineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = usineRepository.findAll().size();
        // set the field null
        usine.setPays(null);

        // Create the Usine, which fails.


        restUsineMockMvc.perform(post("/api/usines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usine)))
            .andExpect(status().isBadRequest());

        List<Usine> usineList = usineRepository.findAll();
        assertThat(usineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsines() throws Exception {
        // Initialize the database
        usineRepository.saveAndFlush(usine);

        // Get all the usineList
        restUsineMockMvc.perform(get("/api/usines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usine.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS)));
    }
    
    @Test
    @Transactional
    public void getUsine() throws Exception {
        // Initialize the database
        usineRepository.saveAndFlush(usine);

        // Get the usine
        restUsineMockMvc.perform(get("/api/usines/{id}", usine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(usine.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS));
    }
    @Test
    @Transactional
    public void getNonExistingUsine() throws Exception {
        // Get the usine
        restUsineMockMvc.perform(get("/api/usines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsine() throws Exception {
        // Initialize the database
        usineService.save(usine);

        int databaseSizeBeforeUpdate = usineRepository.findAll().size();

        // Update the usine
        Usine updatedUsine = usineRepository.findById(usine.getId()).get();
        // Disconnect from session so that the updates on updatedUsine are not directly saved in db
        em.detach(updatedUsine);
        updatedUsine
            .nom(UPDATED_NOM)
            .adresse(UPDATED_ADRESSE)
            .pays(UPDATED_PAYS);

        restUsineMockMvc.perform(put("/api/usines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsine)))
            .andExpect(status().isOk());

        // Validate the Usine in the database
        List<Usine> usineList = usineRepository.findAll();
        assertThat(usineList).hasSize(databaseSizeBeforeUpdate);
        Usine testUsine = usineList.get(usineList.size() - 1);
        assertThat(testUsine.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testUsine.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testUsine.getPays()).isEqualTo(UPDATED_PAYS);
    }

    @Test
    @Transactional
    public void updateNonExistingUsine() throws Exception {
        int databaseSizeBeforeUpdate = usineRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsineMockMvc.perform(put("/api/usines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usine)))
            .andExpect(status().isBadRequest());

        // Validate the Usine in the database
        List<Usine> usineList = usineRepository.findAll();
        assertThat(usineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsine() throws Exception {
        // Initialize the database
        usineService.save(usine);

        int databaseSizeBeforeDelete = usineRepository.findAll().size();

        // Delete the usine
        restUsineMockMvc.perform(delete("/api/usines/{id}", usine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Usine> usineList = usineRepository.findAll();
        assertThat(usineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
