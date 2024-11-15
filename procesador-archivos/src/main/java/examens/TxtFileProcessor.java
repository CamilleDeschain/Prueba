package examens;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TxtFileProcessor extends FileProcessor {

    public TxtFileProcessor(File file) {
        super(file);
    }

    @Override
    public void process() throws IOException {
        System.out.println("Contenido del archivo de texto:");
        Files.lines(file.toPath()).forEach(System.out::println);
    }
}
