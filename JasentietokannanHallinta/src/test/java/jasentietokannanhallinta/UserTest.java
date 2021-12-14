package jasentietokannanhallinta;

import jasentietokannanhallinta.domain.Jasentiedot;
import jasentietokannanhallinta.domain.JasentiedotService;
import jasentietokannanhallinta.domain.User;
//import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User user;
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void equalWhenSameUsername() {
        User u1 = new User("username", "nameOf", "password");
        User u2 = new User("username", "nameOf", "password");
        assertTrue(u1.getUsername().equals(u2.getUsername())&&
                   u1.getName().equals(u2.getName()));
    }
    
    @Test
    public void nonEqualWhenDifferentUsername() {
        User u1 = new User("username", "nameOf", "password");
        User u2 = new User("notsame", "nameOf", "password");
        assertTrue(!u1.getUsername().equals(u2.getUsername())&&
                   u1.getName().equals(u2.getName()));
    }
    
    @Test
    public void nonEqualWhenDifferentName() {
        User u1 = new User("username", "nameOf", "password");
        User u2 = new User("username", "notsame", "password");
        assertTrue(u1.getUsername().equals(u2.getUsername())&&
                   !u1.getName().equals(u2.getName()));
    }
        
    @Test
    public void nonEqualWhenDifferentType() {
        User u = new User("username", "nameOf", "password");
        Object o = new Object();
        assertTrue(!o.equals(u));
    }
    
    @Test
    public void nonEqualWhenDifferentNameSet(){
        User u1 = new User("username", "nameOf", "password");
        User u2 = new User("username", "nameOf", "password");
        u2.setName("notsame");
        assertTrue(!u1.getName().equals(u2.getName()));
    }
    
    @Test
    public void nonEqualWhenDifferentUsernameSet(){
        User u1 = new User("username", "nameOf", "password");
        User u2 = new User("username", "nameOf", "password");
        u2.setUsername("notsame");
        assertTrue(!u1.getUsername().equals(u2.getUsername()));
    }

}
