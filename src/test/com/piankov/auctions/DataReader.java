package test.com.piankov.auctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    private static final Logger LOGGER = LogManager.getLogger(DataReader.class);
    private List<String> inputLines = new ArrayList<>();

    public void readData(String filepath) {
        try {
            Scanner inputData = new Scanner(new File(filepath));
            while (inputData.hasNextLine()) {
                String nextLine = inputData.nextLine();
                LOGGER.debug("Next line: " + nextLine);
                inputLines.add(nextLine);
            }
            inputData.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("Wrong file!");
        }
    }

    public List<String> getInputLines() {
        return Collections.unmodifiableList(inputLines);
    }
}
