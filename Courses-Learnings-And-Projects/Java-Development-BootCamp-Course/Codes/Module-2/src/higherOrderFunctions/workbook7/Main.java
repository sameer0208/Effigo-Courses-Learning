package higherOrderFunctions.workbook7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("emails.txt");

        Files.lines(path)
            .filter(s -> !s.startsWith("Spam"))
            .forEach(email -> System.out.println(email));
    }
}
