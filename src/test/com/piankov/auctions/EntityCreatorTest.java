package test.com.piankov.auctions;

import com.piankov.auctions.creator.UserCreator;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.InputDataException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class EntityCreatorTest {
    private Service service = new Service();
    private List<Object[]> parameters;
    private double delta = 0.001;

    @BeforeClass
    public void initializeTools() {
        String filepath = "data/entity.txt";
        List<String> tests = Arrays.asList( "5.0 0.0");

        service.setUp(filepath, tests);

        try {
            DataReader dataReader = new DataReader();
            dataReader.readData(filepath);

            UserCreator userCreator = new UserCreator();
            //userCreator.buildEntityFromMap();

            User testUser = new User();
            //parameters = entityGenerator.generateBallTestData(dataReader.getInputLines());
            //plane = entityGenerator.generateArrayOfPoints(testPlane, planePoints);
        } catch (InputDataException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "data")
    public Iterator<Object[]> retrieveData() {
        return parameters.iterator();
    }

    @Test(dataProvider = "data")
    public void ballSurfaceAreaTest() {
        //Assert.assertEquals();
    }

    @AfterClass
    public void closeFiles() {
        service.dropFile();
    }
}
