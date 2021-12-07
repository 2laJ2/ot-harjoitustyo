package jasentietokannanhallinta.domain;

import java.util.*;
import jasentietokannanhallinta.dao.FileJasentiedotDao;
import jasentietokannanhallinta.dao.FileUserDao;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JasentiedotService {
    
    public List<Jasentiedot> jasentiedotList;
    private FileJasentiedotDao jasentiedotDao;
    private FileUserDao userDao;
    private User loggedIn;
    
    public JasentiedotService(FileJasentiedotDao jasentiedotDao, FileUserDao userDao) {
        this.userDao = userDao;
        this.jasentiedotDao = jasentiedotDao;
    }
    
    public void createJasentiedot(int id, String name, String address, String phone, Boolean done, User user) {
        Jasentiedot jasentiedot = new Jasentiedot(id, name, address, phone, done, user);
        jasentiedotDao.create(jasentiedot);
    }
    
    public List<Jasentiedot> getUndone() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
        
        return jasentiedotDao.getAll()
                .stream()
                .filter(t-> {
                    System.out.println(t.getUser());
                    return t.getUser().equals(loggedIn);
                })
                .filter(t->!t.isDone())
                .collect(Collectors.toList());
    }
    
    public void markDone(int id) {
        jasentiedotDao.setDone(id);
    }
    
    public boolean login(String username) {
        User user = userDao.findUsername(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        
        return true;
    }
    
    public User getLoggedUser() {
        return loggedIn;
    }
    
    public void logout() {
        loggedIn = null;
    }
    
    public boolean createUser(String username, String name, String password) {
        User user = new User(username, name, password);
        if (userDao.findUsername(username) != null) {
            return false;
        }
        userDao.create(user);
        return true;
        
    }
    
    public boolean createNewMember(String name, String address, String phone){
        if (jasentiedotDao.findMemberName(name) != null) {
            return false;
        }
        Jasentiedot jasentiedot = new Jasentiedot(1, name, address, phone , false, loggedIn);
        jasentiedotDao.create(jasentiedot);
        return true;
    }
    
    public boolean doesMemberNameExist(String name){
        if(jasentiedotDao.findMemberName(name) == null) return false;
        return true;
    }
    
    public Jasentiedot findMemberByName(String name){
        return jasentiedotDao.findMemberName(name);
    }
    
}
        