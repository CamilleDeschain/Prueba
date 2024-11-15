package examens;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.util.List;

public class CsvFileProcessor extends FileProcessor {

    public CsvFileProcessor(File file) {
        super(file);
    }

    @Override
    public void process() throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> records = reader.readAll();
            System.out.println("Contenido del archivo CSV:");
            for (String[] record : records) {
                System.out.println(String.join(", ", record));
            }
        }
    }
}
