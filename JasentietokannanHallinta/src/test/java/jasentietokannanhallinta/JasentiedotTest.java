package jasentietokannanhallinta;

import jasentietokannanhallinta.domain.Jasentiedot;
//import jasentietokannanhallinta.domain.JasentiedotService;
import jasentietokannanhallinta.domain.User;
//import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JasentiedotTest {
    
    Jasentiedot jasentiedot;
    User user;
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void nonEqualWhenDifferentId(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        Jasentiedot j2=new Jasentiedot(2, "name", "address", "phone", false, user);
        assertFalse(j2.equals(j1));
    }
    
    @Test
    public void nonEqualWhenDifferentName(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        Jasentiedot j2=new Jasentiedot(2, "notsame", "address","phone", false, user);
        assertFalse(j2.getName().equals(j1.getName()));
    }
    
    @Test
    public void nonEqualWhenDifferentAddress(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        Jasentiedot j2=new Jasentiedot(2, "name", "notsame", "phone", false, user);
        assertFalse(j2.getAddress().equals(j1.getAddress()));
    }
    
    @Test
    public void nonEqualWhenDifferentPhone(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        Jasentiedot j2=new Jasentiedot(2, "name", "address", "notsame", false, user);
        assertFalse(j2.getPhone().equals(j1.getPhone()));
    }
     
    @Test
    public void nonEqualWhenDifferentDone(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        Jasentiedot j2=new Jasentiedot(2, "name", "address", "phone", true, user);
        assertFalse(j2.isDone()==j1.isDone());
    }
    
    @Test
    public void nonEqualWhenDifferentUser(){
        User u1=new User("username", "nameOf", "password");
        User u2=new User("notsame", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, u1);
        Jasentiedot j2=new Jasentiedot(2, "name", "address", "phone", false, u2);
        assertFalse(j2.getUser().equals(j1.getUser()));
    }
    
    @Test
    public void nonEqualWhenDifferentIdSet(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        j1.setId(3);
        assertFalse(j1.getId()==1);
    }
    
    @Test
    public void nonEqualWhenDifferentNameSet(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        j1.setName("notsame");
        assertFalse(j1.getName().equals("name"));
    }
    
    @Test
    public void nonEqualWhenDifferentAddressSet(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        j1.setAddress("notsame");
        assertFalse(j1.getAddress().equals("address"));
    }
    
    @Test
    public void nonEqualWhenDifferentPhoneSet(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        j1.setPhone("notsame");
        assertFalse(j1.getPhone().equals("phone"));
    }
     
    @Test
    public void nonEqualWhenDifferentDoneSet(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", true, user);
        j1.setDoneTo(false);
        assertFalse(j1.isDone());
    }
    
    @Test
    public void nonEqualWhenDifferentDoneSet2(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        j1.setDone();
        assertFalse(!j1.isDone());
    }
    
    @Test
    public void nonEqualWhenDifferentType(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        Object o=new Object();
        assertTrue(!o.equals(j1));
    }
    
    @Test
    public void nonEqualWhenDifferentId2(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        Jasentiedot j2=new Jasentiedot(1, "name", "address", "phone", false, user);
        j2.setId(2);
        assertTrue(!j1.equals(j2));
    }
    
    @Test
    public void nonEqualWhenDifferentId3(){
        User user=new User("username", "nameOf", "password");
        Jasentiedot j1=new Jasentiedot(1, "name", "address", "phone", false, user);
        Jasentiedot j2=new Jasentiedot(2, "name", "address", "phone", false, user);
        assertFalse(j2.equals(j1));
    }
    
}
