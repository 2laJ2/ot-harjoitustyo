package jasentietokannanhallinta;

import jasentietokannanhallinta.domain.JasentiedotService;
import jasentietokannanhallinta.domain.User;
import jasentietokannanhallinta.dao.FileJasentiedotDao;
import jasentietokannanhallinta.dao.FileUserDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileUserDaoTest {
    
    FileJasentiedotDao jasentiedotDao;
    FileUserDao userDao;
    JasentiedotService jasentiedotservice;
    User u;
    
    @Before
    public void setUp() {
        String fileusername = "fileusername";
        String filejasentiedotname = "filejasentiedotname";
        String fileremoved = "fileremoved";
        userDao = new FileUserDao(fileusername);
        jasentiedotDao = new FileJasentiedotDao(filejasentiedotname, userDao, fileremoved);
        jasentiedotservice = new JasentiedotService(jasentiedotDao, userDao);
        u = new User("newusername", "name", "newuserpassword");
        userDao.create(u);
    }
    
    @Test
    public void equalWhenUserCreated() {
        List list = new ArrayList<>();
        list = userDao.getAll();
        assertTrue(!list.isEmpty());
    }
    
    @Test
    public void equalWhenUserCreated2() {
        User a = userDao.findUsername("newusername");
        String b = a.getPassword();
        assertTrue(b.matches("newuserpassword"));
    }
    
    @Test
    public void equalWhenNewUserCreated() {
        jasentiedotservice.createUser("newbieusername", "newbiename", "newbiepassword");
        User b = userDao.findUsername("newbieusername");
        String c = b.getUsername();
        assertTrue(c.matches("newbieusername"));
    }
    
    @Test
    public void equalWhenUserCreated3() {
        User user = u;
        jasentiedotservice.createUser("newuser", "newname", "newpassword");
        assertTrue(userDao.findUsername("newuser") != null);
    }
     
    @Test
    public void equalWhenUsersListed() {
        assertTrue(!userDao.getAll().isEmpty());
    }
    
       
     
}
