package examens;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.IOException;
import com.opencsv.exceptions.CsvException;

public abstract class FileProcessor {
    protected File file;

    public FileProcessor(File file) {
        this.file = file;
    }

    public abstract void process() throws IOException, CsvException;
}
