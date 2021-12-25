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
        String fileremoved = "fileremoved";
        userDao = new FileUserDao(fileusername);
        jasentiedotDao = new FileJasentiedotDao(filejasentiedotname, userDao, fileremoved);
        jasentiedotservice = new JasentiedotService(jasentiedotDao, userDao);
        jasentiedotservice.createUser("username", "name", "password");
        jasentiedotservice.login("username");
        jasentiedotservice.createNewMember("notsame", "address", "phone");
        jasentiedotservice.createNewMember("name", "notsame", "notsame");
    }
    
    @Test
    public void equalWhenBiggerIdRemoved() {
        int before = jasentiedotDao.getLastRemovedMember();
        jasentiedotservice.deleteMember("notsame");
        jasentiedotservice.deleteMember("name");
        int after = jasentiedotDao.getLastRemovedMember();
        assertTrue(after > before);
    }
    
    @Test
    public void equalWhenBiggerIdForNewMember() {
        int existing = jasentiedotDao.getLastMember();
        jasentiedotservice.deleteMember("name");
        jasentiedotservice.createNewMember("newname", "newaddress", "newphone");
        int newmember = jasentiedotservice.findMemberByName("newname").getId();
        assertTrue(existing != newmember);
    }
    
    @Test
    public void equalWhenUserCreatedWhileLogged() {
        jasentiedotservice.createUser("newuser", "newname", "newpassword");
        assertTrue(userDao.findUsername("newuser") != null);
    }
    
    @Test
    public void equalWhenUserCreatedWhileLoggedOut() {
        jasentiedotservice.logout();
        jasentiedotservice.createUser("newbieUser", "newname", "newpassword");
        assertTrue(userDao.findUsername("newbieUser") != null);
    }
      
    @Test 
    public void equalWhenJasentiedotCreated() {
        jasentiedotservice.createJasentiedot(1, "newName", "address", "phone", user);
        String name= jasentiedotservice.findMemberByName("newName").getName();
        assertTrue(name.matches("newName"));
    }
    
    
    @Test 
    public void equalWhenJasentiedotCreated2() {
        assertTrue(!jasentiedotDao.getJasentiedotList().isEmpty());
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

    @Test
    public void equalWhenChangesSaved() {
        Jasentiedot oldJasentiedot = jasentiedotservice.findMemberByName("notsame");
        Jasentiedot newJasentiedot = oldJasentiedot;
        newJasentiedot.setAddress("newAddress");
        jasentiedotservice.saveChanges(newJasentiedot, oldJasentiedot);
        assertTrue(!jasentiedotservice.findMemberByName("notsame").getAddress().matches("address"));
    }
    
    @Test
    public void equalWhenChangesSaved2() {
        Jasentiedot oldJasentiedot = jasentiedotservice.findMemberByName("notsame");
        Jasentiedot newJasentiedot = oldJasentiedot;
        newJasentiedot.setAddress("newAddress");
        jasentiedotservice.saveChanges(newJasentiedot, oldJasentiedot);
        assertTrue(!jasentiedotservice.getFoundMemberAddressByName("notsame").matches("address"));
    }
    
    @Test
    public void equalWhenFindsMemberPhoneByName() {
        String phonenumber = jasentiedotservice.getFoundMemberPhoneByName("notsame");
        jasentiedotservice.deleteMember("notsame");
        assertTrue(phonenumber.matches("phone")&&jasentiedotservice.doesMemberNameExist("notsame") == false);
    }
    
    @Test
    public void equalWhenMemberNotExistNotDeleted() {
        assertTrue(!jasentiedotservice.deleteMember("notexist"));
    }
    
    @Test
    public void equalWhenMemberNotExistNotDeleted2() {
        User user = userDao.findUsername("username");
        Jasentiedot jasentiedot = new Jasentiedot(5, "newmembername", "newmemberaddress", "newmemberphone", user);
        jasentiedotservice.deleteMember("notsame");
        assertTrue(!jasentiedotDao.removeMember(jasentiedot));
    }
    
}

    