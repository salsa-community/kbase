package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.KbaseApp;
import com.github.danimaniarqsoft.domain.Actividad;
import com.github.danimaniarqsoft.repository.ActividadRepository;
import com.github.danimaniarqsoft.service.ActividadService;
import com.github.danimaniarqsoft.service.dto.ActividadDTO;
import com.github.danimaniarqsoft.service.mapper.ActividadMapper;
import com.github.danimaniarqsoft.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.github.danimaniarqsoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ActividadResource} REST controller.
 */
@SpringBootTest(classes = KbaseApp.class)
public class ActividadResourceIT {

    private static final String DEFAULT_CONTEXTO = "AAAAAAAAAA";
    private static final String UPDATED_CONTEXTO = "BBBBBBBBBB";

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private ActividadMapper actividadMapper;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restActividadMockMvc;

    private Actividad actividad;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActividadResource actividadResource = new ActividadResource(actividadService);
        this.restActividadMockMvc = MockMvcBuilders.standaloneSetup(actividadResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Actividad createEntity() {
        Actividad actividad = new Actividad()
            .contexto(DEFAULT_CONTEXTO)
            .valor(DEFAULT_VALOR)
            .desc(DEFAULT_DESC)
            .fecha(DEFAULT_FECHA);
        return actividad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Actividad createUpdatedEntity() {
        Actividad actividad = new Actividad()
            .contexto(UPDATED_CONTEXTO)
            .valor(UPDATED_VALOR)
            .desc(UPDATED_DESC)
            .fecha(UPDATED_FECHA);
        return actividad;
    }

    @BeforeEach
    public void initTest() {
        actividadRepository.deleteAll();
        actividad = createEntity();
    }

    @Test
    public void createActividad() throws Exception {
        int databaseSizeBeforeCreate = actividadRepository.findAll().size();

        // Create the Actividad
        ActividadDTO actividadDTO = actividadMapper.toDto(actividad);
        restActividadMockMvc.perform(post("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isCreated());

        // Validate the Actividad in the database
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeCreate + 1);
        Actividad testActividad = actividadList.get(actividadList.size() - 1);
        assertThat(testActividad.getContexto()).isEqualTo(DEFAULT_CONTEXTO);
        assertThat(testActividad.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testActividad.getDesc()).isEqualTo(DEFAULT_DESC);
        assertThat(testActividad.getFecha()).isEqualTo(DEFAULT_FECHA);
    }

    @Test
    public void createActividadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = actividadRepository.findAll().size();

        // Create the Actividad with an existing ID
        actividad.setId("existing_id");
        ActividadDTO actividadDTO = actividadMapper.toDto(actividad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActividadMockMvc.perform(post("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actividad in the database
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllActividads() throws Exception {
        // Initialize the database
        actividadRepository.save(actividad);

        // Get all the actividadList
        restActividadMockMvc.perform(get("/api/actividads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(actividad.getId())))
            .andExpect(jsonPath("$.[*].contexto").value(hasItem(DEFAULT_CONTEXTO)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }
    
    @Test
    public void getActividad() throws Exception {
        // Initialize the database
        actividadRepository.save(actividad);

        // Get the actividad
        restActividadMockMvc.perform(get("/api/actividads/{id}", actividad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(actividad.getId()))
            .andExpect(jsonPath("$.contexto").value(DEFAULT_CONTEXTO))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()));
    }

    @Test
    public void getNonExistingActividad() throws Exception {
        // Get the actividad
        restActividadMockMvc.perform(get("/api/actividads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateActividad() throws Exception {
        // Initialize the database
        actividadRepository.save(actividad);

        int databaseSizeBeforeUpdate = actividadRepository.findAll().size();

        // Update the actividad
        Actividad updatedActividad = actividadRepository.findById(actividad.getId()).get();
        updatedActividad
            .contexto(UPDATED_CONTEXTO)
            .valor(UPDATED_VALOR)
            .desc(UPDATED_DESC)
            .fecha(UPDATED_FECHA);
        ActividadDTO actividadDTO = actividadMapper.toDto(updatedActividad);

        restActividadMockMvc.perform(put("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isOk());

        // Validate the Actividad in the database
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeUpdate);
        Actividad testActividad = actividadList.get(actividadList.size() - 1);
        assertThat(testActividad.getContexto()).isEqualTo(UPDATED_CONTEXTO);
        assertThat(testActividad.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testActividad.getDesc()).isEqualTo(UPDATED_DESC);
        assertThat(testActividad.getFecha()).isEqualTo(UPDATED_FECHA);
    }

    @Test
    public void updateNonExistingActividad() throws Exception {
        int databaseSizeBeforeUpdate = actividadRepository.findAll().size();

        // Create the Actividad
        ActividadDTO actividadDTO = actividadMapper.toDto(actividad);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActividadMockMvc.perform(put("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actividad in the database
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteActividad() throws Exception {
        // Initialize the database
        actividadRepository.save(actividad);

        int databaseSizeBeforeDelete = actividadRepository.findAll().size();

        // Delete the actividad
        restActividadMockMvc.perform(delete("/api/actividads/{id}", actividad.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Actividad.class);
        Actividad actividad1 = new Actividad();
        actividad1.setId("id1");
        Actividad actividad2 = new Actividad();
        actividad2.setId(actividad1.getId());
        assertThat(actividad1).isEqualTo(actividad2);
        actividad2.setId("id2");
        assertThat(actividad1).isNotEqualTo(actividad2);
        actividad1.setId(null);
        assertThat(actividad1).isNotEqualTo(actividad2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActividadDTO.class);
        ActividadDTO actividadDTO1 = new ActividadDTO();
        actividadDTO1.setId("id1");
        ActividadDTO actividadDTO2 = new ActividadDTO();
        assertThat(actividadDTO1).isNotEqualTo(actividadDTO2);
        actividadDTO2.setId(actividadDTO1.getId());
        assertThat(actividadDTO1).isEqualTo(actividadDTO2);
        actividadDTO2.setId("id2");
        assertThat(actividadDTO1).isNotEqualTo(actividadDTO2);
        actividadDTO1.setId(null);
        assertThat(actividadDTO1).isNotEqualTo(actividadDTO2);
    }
}
