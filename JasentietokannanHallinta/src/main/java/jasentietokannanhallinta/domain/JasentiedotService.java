package jasentietokannanhallinta.domain;

import java.util.*;
import jasentietokannanhallinta.dao.FileJasentiedotDao;
import jasentietokannanhallinta.dao.FileUserDao;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Sovelluslogiikasta vastaava luokka
 */
public class JasentiedotService {
    
    /**
     *
     */
    public List<Jasentiedot> jasentiedotList;
    private FileJasentiedotDao jasentiedotDao;
    private FileUserDao userDao;
    private User loggedIn;
    
    /**
     * Sovelluslogiikasta vastaavan luokan konstruktori
     * @param jasentiedotDao
     * @param userDao
     */
    public JasentiedotService(FileJasentiedotDao jasentiedotDao, FileUserDao userDao) {
        this.userDao = userDao;
        this.jasentiedotDao = jasentiedotDao;
    }
    
    /**
     * uuden jäsenen luominen
     * @param id jäsenen numero
     * @param name jäsenen nimi
     * @param address jäsenen osoite
     * @param phone jäsenen puhelinnumero
     * @param user jäsenen luoneen käyttäjän käyttäjätunnus
     */
    public void createJasentiedot(int id, String name, String address, String phone, User user) {
        Jasentiedot jasentiedot = new Jasentiedot(id, name, address, phone, user);
        jasentiedotDao.create(jasentiedot);
    }
    
    /**
     * sisäänkirjautuminen
     * @param username
     * @return true, jos käyttäjätunnus on olemassa, muuten false
     */
    public boolean login(String username) {
        User user = userDao.findUsername(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        
        return true;
    }
    
    /**
     * kirjautunut käyttäjä
     * @return kirjautunut käyttäjä
     */
    public User getLoggedUser() {
        return loggedIn;
    }
    
    /**
     * uloskirjautuminen
     */
    public void logout() {
        loggedIn = null;
    }
    
    /**
     * uuden käyttäjän luominen
     * @param username käyttäjätunnus
     * @param name käyttäjän nimi
     * @param password käyttäjän salasana
     * @return true, jos käyttäjätunnuksen luominen on onnistunut, muuten false
     */
    public boolean createUser(String username, String name, String password) {
        if (userDao.findUsername(username) == null) {
            User user = new User(username, name, password);
            userDao.create(user);
            return true;
        }
        return false;
        
    }
        
    /**
     * uuden jäsenen luominen
     * @param name jäsenen nimi
     * @param address jäsenen osoite
     * @param phone jäsenen puhelinnumero
     * @return true, jos jäsenen luominen on onnistunut, muulloin false
     */
    public boolean createNewMember(String name, String address, String phone) {
        if (jasentiedotDao.findMemberName(name) != null) {
            return false;
        }
        Jasentiedot jasentiedot = new Jasentiedot(1, name, address, phone, loggedIn);
        jasentiedotDao.create(jasentiedot);
        return true;
    }
    
    /**
     * jäsenen olemassaolon tarkistus 
     * @param name jäsenen nimi
     * @return true, jos jäsenen nimi on ennestään olemassa
     */
    public boolean doesMemberNameExist(String name) {
        if (jasentiedotDao.findMemberName(name) == null) {
            return false;
        }
        return true;
    }
    
    /**
     * olemassaolevan jäsenen haku
     * @param name jäsenen nimi
     * @return jäsentiedot
     */
    public Jasentiedot findMemberByName(String name) {
        return jasentiedotDao.findMemberName(name);
    }
    
    /**
     * olemassaolevan jäsenen osoitetietojen haku
     * @param name jäsenen nimi
     * @return osoite
     */
    public String getFoundMemberAddressByName(String name) {
        Jasentiedot jasentiedot = jasentiedotDao.findMemberName(name);
        return jasentiedot.getAddress();
    }
    
    /**
     * olemassaolevan jäsenen puhelinnumeron haku
     * @param name jäsenen nimi
     * @return puhelinnumero
     */
    public String getFoundMemberPhoneByName(String name) {
        Jasentiedot jasentiedot = jasentiedotDao.findMemberName(name);
        return jasentiedot.getPhone();
    }
    
    /**
     * olemassaolevan jäsenen poisto
     * @param name jäsenen nimi
     * @return true, jos jäsen on olemassa ja poistaminen onnistuu, muuten false
     */
    public boolean deleteMember(String name) {
        if (doesMemberNameExist(name)) {
            Jasentiedot jasentiedot = findMemberByName(name);
            jasentiedotDao.removeMember(jasentiedot);
            return true;
        }
        return false;
    }
    
}
        