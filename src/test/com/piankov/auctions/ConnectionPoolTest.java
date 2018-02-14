package test.com.piankov.auctions;

import com.piankov.auctions.connection.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConnectionPoolTest {
    @Test
    public void testGetInstance() {
        Assert.assertNotNull(ConnectionPool.getInstance());
    }

    @Test
    public void testTakeConnection() {
        Assert.assertNotNull(ConnectionPool.getInstance().takeConnection());
    }
}
