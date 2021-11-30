package jasentietokannanhallinta;

import jasentietokannanhallinta.domain.Jasentiedot;
import jasentietokannanhallinta.domain.JasentiedotService;
import jasentietokannanhallinta.domain.User;
import jasentietokannanhallinta.dao.FileJasentiedotDao;
import jasentietokannanhallinta.dao.FileUserDao;
import java.util.ArrayList;
import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JasentiedotServiceTest {
    
    FileJasentiedotDao jasentiedotDao;
    FileUserDao userDao;
    JasentiedotService jasentiedotservice;
    Jasentiedot jasentiedot;
    User user;
    
    @Before
    public void setUp() {
        List<User> users = new ArrayList<>();
        String fileusername = "fileusername";
    
        List<Jasentiedot> jasentiedotList = new ArrayList<>();
        String filejasentiedotname = "filejasentiedotname";
        
        userDao = new FileUserDao(fileusername);
        jasentiedotDao = new FileJasentiedotDao(filejasentiedotname, userDao);
    
        jasentiedotservice = new JasentiedotService(jasentiedotDao, userDao);
    }
 
    @Test
    public void equalWhenUserCreated() {
        jasentiedotservice.createUser("username", "name", "password");
        List list = new ArrayList<>();
        list = userDao.getAll();
        assertTrue(!list.isEmpty());
    }
    
    @Test 
    public void equalWhenJasentiedotCreated() {
        jasentiedotservice.createJasentiedot(1, "name", "address", "phone", Boolean.TRUE, user);
        assertTrue(!jasentiedotDao.jasentiedotList.isEmpty());
    }
    
    @Test
    public void equalWhenUserLoggedIn() {
        jasentiedotservice.createUser("username", "name", "password");
        jasentiedotservice.login("username");
        User loggedinuser = jasentiedotservice.getLoggedUser();
        assertTrue(userDao.findUsername("username").equals(loggedinuser));
    }
    
    @Test
    public void equalWhenLoggedOut() {
        jasentiedotservice.createUser("username", "name", "password");
        jasentiedotservice.login("username");
        User loggedinuser = jasentiedotservice.getLoggedUser();
        jasentiedotservice.logout();
        assertTrue(jasentiedotservice.getLoggedUser() == null && loggedinuser != null);
    }
    
    @Test
    public void equalWhenUserNotLoggedIn() {
        jasentiedotservice.createUser("username", "name", "password");
        jasentiedotservice.login("notsame");
        assertTrue(jasentiedotservice.getLoggedUser() == null);
    }

}

