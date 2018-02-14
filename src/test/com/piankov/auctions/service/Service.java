package test.com.piankov.auctions.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Service {
    private Path testFile;

    public void setUp(String filepath, List<String> tests) {
        testFile = Paths.get(filepath);
        try {
            Files.write(testFile, tests, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dropFile() {
        try {
            Files.delete(testFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
