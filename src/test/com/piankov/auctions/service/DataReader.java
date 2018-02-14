package test.com.piankov.auctions.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataReader {
    private static final Logger LOGGER = LogManager.getLogger(DataReader.class);

    private static final String MAP_DELIMITER = " : ";

    private Map<String, String[]> inputMap = new HashMap<>();

    public void readData(String filepath) {
        try {
            Scanner inputData = new Scanner(new File(filepath));
            while (inputData.hasNextLine()) {
                String nextLine = inputData.nextLine();
                LOGGER.info("Next line: " + nextLine);

                String[] parts = nextLine.split(MAP_DELIMITER, 2);
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value[] = Arrays.copyOfRange(parts, 1, parts.length);
                    inputMap.put(key, value);
                }
            }
            inputData.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("Wrong file!");
        }
    }

    public Map<String, String[]> getInputLines() {
        return Collections.unmodifiableMap(inputMap);
    }
}
