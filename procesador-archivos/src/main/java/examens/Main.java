package examens;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.opencsv.exceptions.CsvException;


public class Main {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Por favor, ingrese la ruta del archivo (sin el nombre ni la extensión): ");
        String directoryPath = scanner.nextLine();
        System.out.print("Por favor, ingrese el nombre del archivo (incluyendo la extensión, ej. archivo.xlsx): ");
        String fileName = scanner.nextLine();

        Path filePath = Paths.get(directoryPath, fileName);
        File file = filePath.toFile();

        if (!file.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        String extension = getFileExtension(file);
        FileProcessor fileProcessor;

        switch (extension) {
            case "csv":
                fileProcessor = new CsvFileProcessor(file);
                break;
            case "xlsx":
            case "xls":
                fileProcessor = new ExcelFileProcessor(file);
                break;
            case "txt":
                fileProcessor = new TxtFileProcessor(file);
                break;
            default:
                System.out.println("Formato de archivo no soportado.");
                return;
        }

        try {
            fileProcessor.process();
        } catch (IOException | CsvException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int dotIndex = name.lastIndexOf('.');
        return (dotIndex > 0) ? name.substring(dotIndex + 1).toLowerCase() : "";
    }
}
