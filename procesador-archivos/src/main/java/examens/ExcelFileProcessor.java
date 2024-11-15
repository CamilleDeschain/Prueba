package examens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Date;

public class ExcelFileProcessor extends FileProcessor {

    public ExcelFileProcessor(File file) {
        super(file);
    }

    @Override
    public void process() throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Workbook newWorkbook = new XSSFWorkbook();
            Sheet newSheet = newWorkbook.createSheet(sheet.getSheetName());

            int newRowIndex = 0;
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                Row newRow = newSheet.createRow(newRowIndex);

                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    Cell cell = row.getCell(j);
                    copyCellValue(cell, newRow.createCell(j));

                    Row newRowTransformed = newSheet.createRow(newRowIndex + 1);
                    applyTransformations(cell, newRowTransformed.createCell(j));
                }

                newRowIndex += 2;
            }

            String newFilePath = getNewFilePath(file.getPath());
            try (FileOutputStream outputStream = new FileOutputStream(newFilePath)) {
                newWorkbook.write(outputStream);
                System.out.println("El archivo modificado se ha guardado como: " + newFilePath);
            }

        }
    }

    private void copyCellValue(Cell sourceCell, Cell destinationCell) {
        switch (sourceCell.getCellType()) {
            case NUMERIC:
                destinationCell.setCellValue(sourceCell.getNumericCellValue());
                break;
            case STRING:
                destinationCell.setCellValue(sourceCell.getStringCellValue());
                break;
            case BOOLEAN:
                destinationCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case FORMULA:
                destinationCell.setCellFormula(sourceCell.getCellFormula());
                break;
            default:
                break;
        }
    }

    private void applyTransformations(Cell cell, Cell transformedCell) {
        switch (cell.getCellType()) {
            case STRING:
                String oldString = cell.getStringCellValue();
                transformedCell.setCellValue(new StringBuilder(oldString).reverse().toString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date currentDate = cell.getDateCellValue();
                    long newTime = currentDate.getTime() + 86400000L;
                    transformedCell.setCellValue(new Date(newTime));
                } else {
                    transformedCell.setCellValue(cell.getNumericCellValue() + 10);
                }
                break;
            case BOOLEAN:
                transformedCell.setCellValue(!cell.getBooleanCellValue());
                break;
            default:
                transformedCell.setCellValue(cell.toString());
                break;
        }
    }

    private String getNewFilePath(String originalFilePath) {
        String fileName = new File(originalFilePath).getName();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        return originalFilePath.substring(0, originalFilePath.lastIndexOf(File.separator)) + File.separator + baseName + "-modified." + extension;
    }
}
