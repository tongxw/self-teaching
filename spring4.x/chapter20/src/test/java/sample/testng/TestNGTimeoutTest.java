package sample.testng;

import com.smart.domain.User;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class TestNGTimeoutTest {
	private User user;
	
    @BeforeMethod
    public void init() { 
    	user = new User();
        user.setUserName("admin");
    } 

	@Test(timeOut = 2000)
	public void testUser() {
        assertNotNull(user);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals( user.getUserName(),"admin");
	}
}
