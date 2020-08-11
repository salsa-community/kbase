package com.github.danimaniarqsoft.util;

import com.github.danimaniarqsoft.service.dto.ActividadDTO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.io.IOException;
import java.util.List;


/**
 * ExcelUtil
 */
public class ExcelUtil {

    public static Workbook customersToExcel(List<ActividadDTO> customers) throws IOException {
    
        String[] COLUMNs = { "contexto", "evento", "valor" };

        try {
            Workbook workbook = new SXSSFWorkbook();
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("reporte");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);

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
            }
            return workbook;
        } catch (Exception e) {
        }
        return null;

    }
    
}