package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.ActividadService;
import com.github.danimaniarqsoft.domain.Actividad;
import com.github.danimaniarqsoft.repository.ActividadRepository;
import com.github.danimaniarqsoft.service.dto.ActividadDTO;
import com.github.danimaniarqsoft.service.dto.CodigoErrorCount;
import com.github.danimaniarqsoft.service.dto.Filtro;
import com.github.danimaniarqsoft.service.dto.ReporteDTO;
import com.github.danimaniarqsoft.service.mapper.ActividadMapper;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//imports as static
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service Implementation for managing {@link Actividad}.
 */
@Service
public class ActividadServiceImpl implements ActividadService {

    private final Logger log = LoggerFactory.getLogger(ActividadServiceImpl.class);

    private final ActividadRepository actividadRepository;

    private final ActividadMapper actividadMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    public ActividadServiceImpl(ActividadRepository actividadRepository, ActividadMapper actividadMapper) {
        this.actividadRepository = actividadRepository;
        this.actividadMapper = actividadMapper;
    }

    /**
     * Save a actividad.
     *
     * @param actividadDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ActividadDTO save(ActividadDTO actividadDTO) {
        log.debug("Request to save Actividad : {}", actividadDTO);
        Actividad actividad = actividadMapper.toEntity(actividadDTO);
        actividad = actividadRepository.save(actividad);
        return actividadMapper.toDto(actividad);
    }

    /**
     * Get all the actividads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<ActividadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Actividads");
        return actividadRepository.findAll(pageable)
            .map(actividadMapper::toDto);
    }


    /**
     * Get one actividad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ActividadDTO> findOne(String id) {
        log.debug("Request to get Actividad : {}", id);
        return actividadRepository.findById(id)
            .map(actividadMapper::toDto);
    }

    /**
     * Delete the actividad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Actividad : {}", id);
        actividadRepository.deleteById(id);
    }

	@Override
    public ReporteDTO loadReporte() {
        long numContactos = actividadRepository.countByEvento("REGISTRAR_CONTACTO");

		Aggregation agg = newAggregation(
			match(Criteria.where("evento").is("CODIGO_ERROR")),
			group("valor").count().as("total"),
			project("total").and("valor").previousOperation(),
			sort(Sort.Direction.DESC, "total")
		);

        AggregationResults<CodigoErrorCount> groupResults = mongoTemplate.aggregate(agg, Actividad.class, CodigoErrorCount.class);

		return mapToReporteDTO(numContactos, groupResults.getMappedResults());
    }
    
    private ReporteDTO mapToReporteDTO(long numContactos, List<CodigoErrorCount> counts) {
        ReporteDTO reporte = new ReporteDTO();
        reporte.setNumContactos(String.valueOf(numContactos));
        return resolveCodigoErrorCount(reporte, counts);
    }

    private ReporteDTO resolveCodigoErrorCount(ReporteDTO reporte, List<CodigoErrorCount> counts) {
        if (counts == null || counts.isEmpty()) {
            reporte.setCountCodigoError("0");
            reporte.setTopCodigoError("Sin registro");
        } else {
            CodigoErrorCount count = counts.get(0);
            reporte.setCountCodigoError(String.valueOf(count.getTotal()));
            reporte.setTopCodigoError(count.getValor());
        }
        return reporte;
    }

    @Override
    public Page<ActividadDTO> findByFilter(Filtro filtro, Pageable pageable) {
        log.debug("Request to get all Actividads");
        Query query = new Query();
        if(!filtro.isEmpty()){
            query.addCriteria(createCriteria(filtro));
        }
        query.with(pageable);
        List<Actividad> actividades = mongoTemplate.find(query, Actividad.class);
        return PageableExecutionUtils.getPage(actividades, pageable, () -> mongoTemplate.count(query, Actividad.class))
                .map(actividadMapper::toDto);
    }

    @Override
    public Workbook findByFilterAllReport(Filtro filtro) throws IOException {
        log.debug("Request to get all Actividades");
        List<ActividadDTO> actividadesDto = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 500, Sort.by("fecha").descending());

        Query query = new Query();
        if (!filtro.isEmpty()) {
            query.addCriteria(createCriteria(filtro));
        }
        query.with(pageable);
        List<Actividad> actividades = mongoTemplate.find(query, Actividad.class);
        Page<ActividadDTO> page = PageableExecutionUtils.getPage(actividades, pageable, () -> mongoTemplate.count(query, Actividad.class))
                .map(actividadMapper::toDto);

        while (!page.isEmpty()) {
            pageable = pageable.next();
            query.with(pageable);
            actividadesDto.addAll(page.getContent());
            actividades = mongoTemplate.find(query, Actividad.class);
            page = PageableExecutionUtils.getPage(actividades, pageable, () -> mongoTemplate.count(query, Actividad.class))
                    .map(actividadMapper::toDto);
        }
        return toWorkbook(actividadesDto);
    }

      public static Workbook toWorkbook(List<ActividadDTO> customers) throws IOException {
        String[] COLUMNs = { "Contexto", "Evento", "Valor","Fecha" };
        try {
            Workbook workbook = new SXSSFWorkbook();
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("reporte");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (ActividadDTO customer : customers) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(customer.getContexto());
                row.createCell(1).setCellValue(customer.getEvento());
                row.createCell(2).setCellValue(customer.getValor());
                row.createCell(3).setCellValue(customer.getFecha().toString());
            }
            return workbook;
        } catch (Exception e) {
        }
        return null;

    }

    private static Criteria createCriteria(Filtro filtro) {
        List<Criteria> andOperators = new ArrayList<>();
        
        if (!filtro.getContexto().isEmpty()) {
            List<Criteria> contextoCriteriaList = new ArrayList<>();
            filtro.getContexto().forEach(contexto -> {
                contextoCriteriaList.add(Criteria.where("contexto").is(contexto));
            });
            Criteria contextoCriteria = new Criteria()
                    .orOperator(contextoCriteriaList.toArray(new Criteria[contextoCriteriaList.size()]));
            andOperators.add(contextoCriteria);
        }

        if (!filtro.getEvento().isEmpty()) {
            List<Criteria> eventoCriteriaList = new ArrayList<>();
            filtro.getEvento().forEach(evento -> {
                eventoCriteriaList.add(Criteria.where("evento").is(evento));
            });
    
            Criteria eventoCriteria = new Criteria().orOperator(eventoCriteriaList.toArray(new Criteria[eventoCriteriaList.size()]));
            andOperators.add(eventoCriteria);
        }


        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
            andOperators.add(Criteria.where("fecha").gte(filtro.getFechaInicio()).lt(filtro.getFechaFin()));
        }

        return new Criteria().andOperator(andOperators.toArray(new Criteria[andOperators.size()]) );
    }
    
    @Override
    public List<ActividadDTO> findAllByFilter(Filtro filtro, Pageable pageable) {
        return null;
    }
}
