package jasentietokannanhallinta;

import jasentietokannanhallinta.domain.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User u1;
    User u2;
    
    @Before
    public void setUp() {
        u1 = new User("username", "nameOf", "password");
        u2 = new User("username", "nameOf", "password");
        
    }
    
    @Test
    public void equalWhenSameUsernameAndNameAndPassword() {
        assertTrue(u1.getUsername().equals(u2.getUsername())&&
                   u1.getName().equals(u2.getName())&&
                   u1.getPassword().equals(u2.getPassword()));
    }
    
    @Test
    public void nonEqualWhenDifferentUsername() {
        u2.setUsername("notsame");
        assertTrue(!u1.getUsername().equals(u2.getUsername()));
    }
    
    @Test
    public void nonEqualWhenDifferentName() {
        u2.setName("notsame");
        assertTrue(!u1.getName().equals(u2.getName()));
    }
    
    @Test
    public void nonEqualWhenDifferentPassword() {
        u2.setPassword("notsame");
        assertTrue(!u1.getPassword().equals(u2.getPassword()));
    }
    
    @Test
    public void nonEqualWhenDifferentType() {
        Object o = new Object();
        assertTrue(!o.equals(u1));
    }

}
