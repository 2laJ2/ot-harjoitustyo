package jasentietokannanhallinta;

import jasentietokannanhallinta.domain.Jasentiedot;
import jasentietokannanhallinta.domain.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JasentiedotTest {
    
    Jasentiedot j1;
    Jasentiedot j2;
    Jasentiedot j3;
    User u1;
    User u2;
    
    @Before
    public void setUp() {
        
        u1=new User("username", "nameOf", "password");
        u2=new User("notsame", "nameOf", "password");
        
        j1=new Jasentiedot(1, "name", "address", "phone", u1);
        j2=new Jasentiedot(2, "name", "address", "phone", u1);
        j3=new Jasentiedot(2, "name", "address", "phone", u2);
    }
    
    @Test
    public void nonEqualWhenDifferentId(){
        assertFalse(j2.equals(j1));
    }
    
    @Test
    public void nonEqualWhenDifferentIdSet(){
        j1.setId(3);
        assertFalse(j1.getId()==1);
    }
    
    @Test
    public void nonEqualWhenDifferentName(){
        j2.setName("notsame");
        assertFalse(j2.getName().equals(j1.getName()));
    }
    
    @Test
    public void nonEqualWhenDifferentAddress(){
        j2.setAddress("notsame");
        assertFalse(j2.getAddress().equals(j1.getAddress()));
    }
    
    @Test
    public void nonEqualWhenDifferentPhone(){
        j2.setPhone("notsame");
        assertFalse(j2.getPhone().equals(j1.getPhone()));
    }
     
    @Test
    public void nonEqualWhenDifferentUser(){
        assertFalse(j3.getUser().equals(j1.getUser()));
    }
    
    @Test
    public void nonEqualWhenDifferentType(){
        Object o=new Object();
        assertTrue(!o.equals(j1));
    }
    
}
