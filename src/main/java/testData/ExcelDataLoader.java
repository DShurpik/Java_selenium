package testData;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataLoader {

    public static List<String> readExcelData(String filePath, int sheetIndex, int... columnIndices) {
        if (columnIndices.length == 0) {
            throw new IllegalArgumentException("At least one column index must be specified!");
        }
        List<String> data = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Пропускаем заголовок
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Cell cell = row.getCell(columnIndices[0], Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String value;
                switch (cell.getCellType()) {
                    case STRING:
                        value = cell.getStringCellValue().trim();
                        break;
                    case NUMERIC:
                        value = String.valueOf((int) cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        value = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case BLANK:
                        value = "";
                        break;
                    default:
                        value = new DataFormatter().formatCellValue(cell).trim();
                }
                if (!value.isEmpty()) {
                    data.add(value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
