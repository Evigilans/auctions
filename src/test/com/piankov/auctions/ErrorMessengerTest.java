package test.com.piankov.auctions;

import com.piankov.auctions.messenger.ErrorMessenger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ErrorMessengerTest {
    private String key;

    @BeforeClass
    public void initializeTools() {
        key = "error.find.user";
    }

    @Test
    public void getMessageRUTest() {
        String language = "ru_RU";
        String expected = "Не удалось найти профиль пользователя.";
        Assert.assertEquals(expected, ErrorMessenger.getMessage(language, key));
    }

    @Test
    public void getMessageENTest() {
        String language = "en_US";
        String expected = "Can't find user profile.";
        Assert.assertEquals(expected, ErrorMessenger.getMessage(language, key));
    }
}
