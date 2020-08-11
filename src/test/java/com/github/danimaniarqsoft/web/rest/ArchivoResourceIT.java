package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.KbaseApp;
import com.github.danimaniarqsoft.domain.Archivo;
import com.github.danimaniarqsoft.repository.ArchivoRepository;
import com.github.danimaniarqsoft.service.ArchivoService;
import com.github.danimaniarqsoft.service.dto.ArchivoDTO;
import com.github.danimaniarqsoft.service.mapper.ArchivoMapper;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;


import java.util.List;

import static com.github.danimaniarqsoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ArchivoResource} REST controller.
 */
@SpringBootTest(classes = KbaseApp.class)
public class ArchivoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DATO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DATO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DATO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DATO_CONTENT_TYPE = "image/png";

    @Autowired
    private ArchivoRepository archivoRepository;

    @Autowired
    private ArchivoMapper archivoMapper;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restArchivoMockMvc;

    private Archivo archivo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ArchivoResource archivoResource = new ArchivoResource(archivoService);
        this.restArchivoMockMvc = MockMvcBuilders.standaloneSetup(archivoResource)
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
    public static Archivo createEntity() {
        Archivo archivo = new Archivo()
            .nombre(DEFAULT_NOMBRE)
            .dato(DEFAULT_DATO)
            .datoContentType(DEFAULT_DATO_CONTENT_TYPE);
        return archivo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Archivo createUpdatedEntity() {
        Archivo archivo = new Archivo()
            .nombre(UPDATED_NOMBRE)
            .dato(UPDATED_DATO)
            .datoContentType(UPDATED_DATO_CONTENT_TYPE);
        return archivo;
    }

    @BeforeEach
    public void initTest() {
        archivoRepository.deleteAll();
        archivo = createEntity();
    }

    @Test
    public void createArchivo() throws Exception {
        int databaseSizeBeforeCreate = archivoRepository.findAll().size();

        // Create the Archivo
        ArchivoDTO archivoDTO = archivoMapper.toDto(archivo);
        restArchivoMockMvc.perform(post("/api/archivos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(archivoDTO)))
            .andExpect(status().isCreated());

        // Validate the Archivo in the database
        List<Archivo> archivoList = archivoRepository.findAll();
        assertThat(archivoList).hasSize(databaseSizeBeforeCreate + 1);
        Archivo testArchivo = archivoList.get(archivoList.size() - 1);
        assertThat(testArchivo.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testArchivo.getDato()).isEqualTo(DEFAULT_DATO);
        assertThat(testArchivo.getDatoContentType()).isEqualTo(DEFAULT_DATO_CONTENT_TYPE);
    }

    @Test
    public void createArchivoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = archivoRepository.findAll().size();

        // Create the Archivo with an existing ID
        archivo.setId("existing_id");
        ArchivoDTO archivoDTO = archivoMapper.toDto(archivo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArchivoMockMvc.perform(post("/api/archivos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(archivoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Archivo in the database
        List<Archivo> archivoList = archivoRepository.findAll();
        assertThat(archivoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllArchivos() throws Exception {
        // Initialize the database
        archivoRepository.save(archivo);

        // Get all the archivoList
        restArchivoMockMvc.perform(get("/api/archivos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(archivo.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].datoContentType").value(hasItem(DEFAULT_DATO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].dato").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATO))));
    }
    
    @Test
    public void getArchivo() throws Exception {
        // Initialize the database
        archivoRepository.save(archivo);

        // Get the archivo
        restArchivoMockMvc.perform(get("/api/archivos/{id}", archivo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(archivo.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.datoContentType").value(DEFAULT_DATO_CONTENT_TYPE))
            .andExpect(jsonPath("$.dato").value(Base64Utils.encodeToString(DEFAULT_DATO)));
    }

    @Test
    public void getNonExistingArchivo() throws Exception {
        // Get the archivo
        restArchivoMockMvc.perform(get("/api/archivos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateArchivo() throws Exception {
        // Initialize the database
        archivoRepository.save(archivo);

        int databaseSizeBeforeUpdate = archivoRepository.findAll().size();

        // Update the archivo
        Archivo updatedArchivo = archivoRepository.findById(archivo.getId()).get();
        updatedArchivo
            .nombre(UPDATED_NOMBRE)
            .dato(UPDATED_DATO)
            .datoContentType(UPDATED_DATO_CONTENT_TYPE);
        ArchivoDTO archivoDTO = archivoMapper.toDto(updatedArchivo);

        restArchivoMockMvc.perform(put("/api/archivos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(archivoDTO)))
            .andExpect(status().isOk());

        // Validate the Archivo in the database
        List<Archivo> archivoList = archivoRepository.findAll();
        assertThat(archivoList).hasSize(databaseSizeBeforeUpdate);
        Archivo testArchivo = archivoList.get(archivoList.size() - 1);
        assertThat(testArchivo.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testArchivo.getDato()).isEqualTo(UPDATED_DATO);
        assertThat(testArchivo.getDatoContentType()).isEqualTo(UPDATED_DATO_CONTENT_TYPE);
    }

    @Test
    public void updateNonExistingArchivo() throws Exception {
        int databaseSizeBeforeUpdate = archivoRepository.findAll().size();

        // Create the Archivo
        ArchivoDTO archivoDTO = archivoMapper.toDto(archivo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArchivoMockMvc.perform(put("/api/archivos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(archivoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Archivo in the database
        List<Archivo> archivoList = archivoRepository.findAll();
        assertThat(archivoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteArchivo() throws Exception {
        // Initialize the database
        archivoRepository.save(archivo);

        int databaseSizeBeforeDelete = archivoRepository.findAll().size();

        // Delete the archivo
        restArchivoMockMvc.perform(delete("/api/archivos/{id}", archivo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Archivo> archivoList = archivoRepository.findAll();
        assertThat(archivoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Archivo.class);
        Archivo archivo1 = new Archivo();
        archivo1.setId("id1");
        Archivo archivo2 = new Archivo();
        archivo2.setId(archivo1.getId());
        assertThat(archivo1).isEqualTo(archivo2);
        archivo2.setId("id2");
        assertThat(archivo1).isNotEqualTo(archivo2);
        archivo1.setId(null);
        assertThat(archivo1).isNotEqualTo(archivo2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArchivoDTO.class);
        ArchivoDTO archivoDTO1 = new ArchivoDTO();
        archivoDTO1.setId("id1");
        ArchivoDTO archivoDTO2 = new ArchivoDTO();
        assertThat(archivoDTO1).isNotEqualTo(archivoDTO2);
        archivoDTO2.setId(archivoDTO1.getId());
        assertThat(archivoDTO1).isEqualTo(archivoDTO2);
        archivoDTO2.setId("id2");
        assertThat(archivoDTO1).isNotEqualTo(archivoDTO2);
        archivoDTO1.setId(null);
        assertThat(archivoDTO1).isNotEqualTo(archivoDTO2);
    }
}
