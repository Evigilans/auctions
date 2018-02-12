package test.com.piankov.auctions;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class Service {
    private Path testFile;

    void setUp(String filepath, List<String> tests) {
        testFile = Paths.get(filepath);
        try {
            Files.write(testFile, tests, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void dropFile() {
        try {
            Files.delete(testFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
