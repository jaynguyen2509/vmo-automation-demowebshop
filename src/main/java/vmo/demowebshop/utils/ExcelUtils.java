package vmo.demowebshop.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtils {
    public static String path = "src/main/resources/assignment.xlsx";

    public static Object[][] getExcelData(String sheetName) {
        Object[][] data = null;
        Workbook workbook = null;
        try {
            File f = new File(path);
            FileInputStream fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(0);

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sheet.getRow(i);
                    cell = row.getCell(j);

                    switch (cell.getCellType()) {
                        case STRING -> data[i - 1][j] = cell.getStringCellValue();
                        case NUMERIC -> data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                        case BLANK -> data[i - 1][j] = "";
                        default -> data[i - 1][j] = null;
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return data;
    }
}
