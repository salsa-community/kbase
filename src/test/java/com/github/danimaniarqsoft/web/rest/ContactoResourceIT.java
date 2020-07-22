package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.KbaseApp;
import com.github.danimaniarqsoft.domain.Contacto;
import com.github.danimaniarqsoft.repository.ContactoRepository;
import com.github.danimaniarqsoft.service.ContactoService;
import com.github.danimaniarqsoft.service.dto.ContactoDTO;
import com.github.danimaniarqsoft.service.mapper.ContactoMapper;
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


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.github.danimaniarqsoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.github.danimaniarqsoft.domain.enumeration.EstadoContacto;
/**
 * Integration tests for the {@link ContactoResource} REST controller.
 */
@SpringBootTest(classes = KbaseApp.class)
public class ContactoResourceIT {

    private static final String DEFAULT_CONTEXT = "AAAAAAAAAA";
    private static final String UPDATED_CONTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    private static final String DEFAULT_RFC = "AAAAAAAAAA";
    private static final String UPDATED_RFC = "BBBBBBBBBB";

    private static final EstadoContacto DEFAULT_ESTADO = EstadoContacto.NUEVO;
    private static final EstadoContacto UPDATED_ESTADO = EstadoContacto.PENDIENTE;

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LAST_MODIFIED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_MODIFIED = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private ContactoMapper contactoMapper;

    @Autowired
    private ContactoService contactoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restContactoMockMvc;

    private Contacto contacto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactoResource contactoResource = new ContactoResource(contactoService);
        this.restContactoMockMvc = MockMvcBuilders.standaloneSetup(contactoResource)
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
    public static Contacto createEntity() {
        Contacto contacto = new Contacto()
            .context(DEFAULT_CONTEXT)
            .correo(DEFAULT_CORREO)
            .rfc(DEFAULT_RFC)
            .estado(DEFAULT_ESTADO)
            .desc(DEFAULT_DESC)
            .lastModified(DEFAULT_LAST_MODIFIED);
        return contacto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contacto createUpdatedEntity() {
        Contacto contacto = new Contacto()
            .context(UPDATED_CONTEXT)
            .correo(UPDATED_CORREO)
            .rfc(UPDATED_RFC)
            .estado(UPDATED_ESTADO)
            .desc(UPDATED_DESC)
            .lastModified(UPDATED_LAST_MODIFIED);
        return contacto;
    }

    @BeforeEach
    public void initTest() {
        contactoRepository.deleteAll();
        contacto = createEntity();
    }

    @Test
    public void createContacto() throws Exception {
        int databaseSizeBeforeCreate = contactoRepository.findAll().size();

        // Create the Contacto
        ContactoDTO contactoDTO = contactoMapper.toDto(contacto);
        restContactoMockMvc.perform(post("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactoDTO)))
            .andExpect(status().isCreated());

        // Validate the Contacto in the database
        List<Contacto> contactoList = contactoRepository.findAll();
        assertThat(contactoList).hasSize(databaseSizeBeforeCreate + 1);
        Contacto testContacto = contactoList.get(contactoList.size() - 1);
        assertThat(testContacto.getContext()).isEqualTo(DEFAULT_CONTEXT);
        assertThat(testContacto.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testContacto.getRfc()).isEqualTo(DEFAULT_RFC);
        assertThat(testContacto.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testContacto.getDesc()).isEqualTo(DEFAULT_DESC);
        assertThat(testContacto.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
    }

    @Test
    public void createContactoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactoRepository.findAll().size();

        // Create the Contacto with an existing ID
        contacto.setId("existing_id");
        ContactoDTO contactoDTO = contactoMapper.toDto(contacto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactoMockMvc.perform(post("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contacto in the database
        List<Contacto> contactoList = contactoRepository.findAll();
        assertThat(contactoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllContactos() throws Exception {
        // Initialize the database
        contactoRepository.save(contacto);

        // Get all the contactoList
        restContactoMockMvc.perform(get("/api/contactos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contacto.getId())))
            .andExpect(jsonPath("$.[*].context").value(hasItem(DEFAULT_CONTEXT)))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)))
            .andExpect(jsonPath("$.[*].rfc").value(hasItem(DEFAULT_RFC)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())));
    }
    
    @Test
    public void getContacto() throws Exception {
        // Initialize the database
        contactoRepository.save(contacto);

        // Get the contacto
        restContactoMockMvc.perform(get("/api/contactos/{id}", contacto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contacto.getId()))
            .andExpect(jsonPath("$.context").value(DEFAULT_CONTEXT))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO))
            .andExpect(jsonPath("$.rfc").value(DEFAULT_RFC))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()));
    }

    @Test
    public void getNonExistingContacto() throws Exception {
        // Get the contacto
        restContactoMockMvc.perform(get("/api/contactos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateContacto() throws Exception {
        // Initialize the database
        contactoRepository.save(contacto);

        int databaseSizeBeforeUpdate = contactoRepository.findAll().size();

        // Update the contacto
        Contacto updatedContacto = contactoRepository.findById(contacto.getId()).get();
        updatedContacto
            .context(UPDATED_CONTEXT)
            .correo(UPDATED_CORREO)
            .rfc(UPDATED_RFC)
            .estado(UPDATED_ESTADO)
            .desc(UPDATED_DESC)
            .lastModified(UPDATED_LAST_MODIFIED);
        ContactoDTO contactoDTO = contactoMapper.toDto(updatedContacto);

        restContactoMockMvc.perform(put("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactoDTO)))
            .andExpect(status().isOk());

        // Validate the Contacto in the database
        List<Contacto> contactoList = contactoRepository.findAll();
        assertThat(contactoList).hasSize(databaseSizeBeforeUpdate);
        Contacto testContacto = contactoList.get(contactoList.size() - 1);
        assertThat(testContacto.getContext()).isEqualTo(UPDATED_CONTEXT);
        assertThat(testContacto.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testContacto.getRfc()).isEqualTo(UPDATED_RFC);
        assertThat(testContacto.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testContacto.getDesc()).isEqualTo(UPDATED_DESC);
        assertThat(testContacto.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    public void updateNonExistingContacto() throws Exception {
        int databaseSizeBeforeUpdate = contactoRepository.findAll().size();

        // Create the Contacto
        ContactoDTO contactoDTO = contactoMapper.toDto(contacto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactoMockMvc.perform(put("/api/contactos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contacto in the database
        List<Contacto> contactoList = contactoRepository.findAll();
        assertThat(contactoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteContacto() throws Exception {
        // Initialize the database
        contactoRepository.save(contacto);

        int databaseSizeBeforeDelete = contactoRepository.findAll().size();

        // Delete the contacto
        restContactoMockMvc.perform(delete("/api/contactos/{id}", contacto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contacto> contactoList = contactoRepository.findAll();
        assertThat(contactoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contacto.class);
        Contacto contacto1 = new Contacto();
        contacto1.setId("id1");
        Contacto contacto2 = new Contacto();
        contacto2.setId(contacto1.getId());
        assertThat(contacto1).isEqualTo(contacto2);
        contacto2.setId("id2");
        assertThat(contacto1).isNotEqualTo(contacto2);
        contacto1.setId(null);
        assertThat(contacto1).isNotEqualTo(contacto2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactoDTO.class);
        ContactoDTO contactoDTO1 = new ContactoDTO();
        contactoDTO1.setId("id1");
        ContactoDTO contactoDTO2 = new ContactoDTO();
        assertThat(contactoDTO1).isNotEqualTo(contactoDTO2);
        contactoDTO2.setId(contactoDTO1.getId());
        assertThat(contactoDTO1).isEqualTo(contactoDTO2);
        contactoDTO2.setId("id2");
        assertThat(contactoDTO1).isNotEqualTo(contactoDTO2);
        contactoDTO1.setId(null);
        assertThat(contactoDTO1).isNotEqualTo(contactoDTO2);
    }
}
