package jasentietokannanhallinta;

import jasentietokannanhallinta.domain.Jasentiedot;
import jasentietokannanhallinta.domain.JasentiedotService;
import jasentietokannanhallinta.domain.User;
import jasentietokannanhallinta.dao.FileJasentiedotDao;
import jasentietokannanhallinta.dao.FileUserDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JasentiedotServiceTest {
    
    FileJasentiedotDao jasentiedotDao;
    FileUserDao userDao;
    JasentiedotService jasentiedotservice;
    User user;
    
    @Before
    public void setUp() {
        String fileusername = "fileusername";
        String filejasentiedotname = "filejasentiedotname";
        userDao = new FileUserDao(fileusername);
        jasentiedotDao = new FileJasentiedotDao(filejasentiedotname, userDao);
        jasentiedotservice = new JasentiedotService(jasentiedotDao, userDao);
        jasentiedotservice.createUser("username", "name", "password");
        jasentiedotservice.login("username");
        jasentiedotservice.createNewMember("notsame", "address", "phone");
        jasentiedotservice.createNewMember("name", "notsame", "notsame");
    }
    
    @Test
    public void equalWhenUserCreated() {
        List list = new ArrayList<>();
        list = userDao.getAll();
        assertTrue(!list.isEmpty());
    }
      
    @Test 
    public void equalWhenJasentiedotCreated() {
        jasentiedotservice.createJasentiedot(1, "newName", "address", "phone", user);
        String name= jasentiedotservice.findMemberByName("newName").getName();
        assertTrue(name.matches("newName"));
    }
    
    
    @Test 
    public void equalWhenJasentiedotCreated2() {
        assertTrue(!jasentiedotDao.jasentiedotList.isEmpty());
    }
    
    
    @Test
    public void equalWhenUserLoggedIn() {
        User loggedinuser = jasentiedotservice.getLoggedUser();
        assertTrue(userDao.findUsername("username").equals(loggedinuser));
    }
    
    @Test 
    public void equalWhenLoggingUserNotExist() {
        assertTrue(!jasentiedotservice.login("nousername"));
    }
    
    @Test
    public void equalWhenLoggedOut() {
        User loggedinuser = jasentiedotservice.getLoggedUser();
        jasentiedotservice.logout();
        assertTrue(jasentiedotservice.getLoggedUser() == null && loggedinuser != null);
    }
    
    @Test
    public void equalWhenUserNotLoggedIn() {
        jasentiedotservice.createUser("notsame", "newname", "newpassword");
        assertTrue(!jasentiedotservice.getLoggedUser().getUsername().matches("notsame"));
    }
    
    @Test
    public void equalWhenFindsMemberByName() {
        User loggedinuser = jasentiedotservice.getLoggedUser();
        jasentiedotservice.createJasentiedot(0, "newmembername", "address", "phone", loggedinuser);
        assertTrue(jasentiedotDao.findMemberName("newmembername") != null);
    }
    
    @Test
    public void equalWhenFindsMemberList() {
        List <Jasentiedot> memberList = jasentiedotDao.getAll();
        assertTrue(memberList.size() == 2);
    }

}

