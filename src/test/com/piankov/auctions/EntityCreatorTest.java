package test.com.piankov.auctions;

import com.piankov.auctions.creator.AuctionCreator;
import com.piankov.auctions.creator.BidCreator;
import com.piankov.auctions.creator.LotCreator;
import com.piankov.auctions.creator.UserCreator;
import com.piankov.auctions.entity.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.com.piankov.auctions.service.DataReader;
import test.com.piankov.auctions.service.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EntityCreatorTest {
    private Service service;
    private Map<String, String[]> parameterMap;

    @BeforeClass
    public void initializeTools() {
        List<String> tests = Collections.singletonList("email : piankovegor@gmail.com\n" +
                "password : S0mepass\n" +
                "name : Egor Piankov\n" +
                "category : 1\n" +
                "balance : 100\n" +
                "lotName : Lorem Ipsum\n" +
                "lotDescription : Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium\n" +
                "auctionId : 1\n" +
                "bidValue : 100\n" +
                "startPrice : 100\n" +
                "days : 5");

        service = new Service();
        String filepath = "data/entity.txt";
        service.setUp(filepath, tests);

        DataReader dataReader = new DataReader();
        dataReader.readData(filepath);
        parameterMap = dataReader.getInputLines();
    }

    @Test
    public void testAuctionCreation() {
        AuctionCreator auctionCreator = new AuctionCreator();
        Lot lot = new Lot();

        Auction testAuction = auctionCreator.createEntityFromMap(parameterMap, lot);
        Auction expectedAuction = new Auction(lot, null, AuctionType.DIRECT, 100, 5, null, null, null);

        Assert.assertEquals(expectedAuction, testAuction);
    }

    @Test
    public void testBidCreation() {
        BidCreator bidCreator = new BidCreator();
        User user = new User();

        Bid testBid = bidCreator.createEntityFromMap(parameterMap, user);
        Bid expectedBid = new Bid(user.getId(), 1, 100);

        Assert.assertEquals(expectedBid, testBid);
    }

    @Test
    public void testLotCreation() {
        LotCreator lotCreator = new LotCreator();
        User user = new User();

        Lot testLot = lotCreator.createEntityFromMap(parameterMap, user);
        Lot expectedLot = new Lot(user, "Lorem Ipsum", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium");

        Assert.assertEquals(expectedLot, testLot);
    }

    @Test
    public void testUserCreation() {
        UserCreator userCreator = new UserCreator();

        User testUser = userCreator.createEntityFromMap(parameterMap);
        User expectedUser = new User("piankovegor@gmail.com", testUser.getPasswordHash(), "Egor Piankov", 0, UserCategory.CLIENT);

        Assert.assertEquals(expectedUser, testUser);
    }

    @AfterClass
    public void closeFiles() {
        service.dropFile();
    }
}
