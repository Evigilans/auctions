package test.com.piankov.auctions;

import com.piankov.auctions.validator.AuctionValidator;
import com.piankov.auctions.validator.LotValidator;
import com.piankov.auctions.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidationTest {
    private AuctionValidator auctionValidator;
    private LotValidator lotValidator;
    private UserValidator userValidator;

    @BeforeClass
    public void initializeTools() {
        auctionValidator = new AuctionValidator();
        lotValidator = new LotValidator();
        userValidator = new UserValidator();
    }

    @Test
    public void correctIdTest() {
        String id = "1";
        Assert.assertTrue(auctionValidator.isPositiveInteger(id));
    }

    @Test
    public void negativeIdTest() {
        String id = "-1";
        Assert.assertFalse(auctionValidator.isPositiveInteger(id));
    }

    @Test
    public void incorrectIdTest() {
        String id = "a";
        Assert.assertFalse(auctionValidator.isPositiveInteger(id));
    }

    @Test
    public void nullIdTest() {
        Assert.assertFalse(auctionValidator.isPositiveInteger(null));
    }

    @Test
    public void correctLotName() {
        String lotName = "Lorem Ipsum";
        Assert.assertTrue(lotValidator.validateName(lotName));
    }

    @Test
    public void incorrectLotNameTest() {
        String lotName = "1234";
        Assert.assertFalse(lotValidator.validateName(lotName));
    }

    @Test
    public void descriptionTest() {
        String lotDescription = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium";
        Assert.assertTrue(lotValidator.validateName(lotDescription));
    }

    @Test
    public void correctEmailTest() {
        String email = "piankovegor@gmail.com";
        Assert.assertTrue(userValidator.validateEmail(email));
    }

    @Test
    public void incorrectEmailTest() {
        String email = "piankovegorgmail.com";
        Assert.assertFalse(userValidator.validateEmail(email));
    }

    @Test
    public void correctNameTest() {
        String name = "Egor Piankov";
        Assert.assertTrue(userValidator.validateName(name));
    }

    @Test
    public void incorrectNameTest() {
        String name = "1234";
        Assert.assertFalse(userValidator.validateName(name));
    }

    @Test
    public void correctPasswordTest() {
        String password = "HaRdPa55w0rd";
        Assert.assertTrue(userValidator.validatePassword(password));
    }

    @Test
    public void incorrectPasswordTest() {
        String password = "easypassword";
        Assert.assertFalse(userValidator.validatePassword(password));
    }
}
