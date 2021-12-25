package jasentietokannanhallinta.dao;

import java.io.*;
import java.util.*;
import jasentietokannanhallinta.domain.User;

/**
 * käyttäjätietojen hallinnasta vastaava luokka
 */
public class FileUserDao {
    
    private List<User> users;
    private String file;
    
    /**
     * käyttäjätietojen hallinnasta vastaavan luokan konstruktori
     * @param file
     */
    public FileUserDao(String file) {
        users = new ArrayList<>();
        this.file = file;
        load();
    }
    
    private void load() {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[]parts = reader.nextLine().split(";");
                User u = new User(parts[0], parts[1], parts[2]);
                users.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void save() {
        try {
            FileWriter writer = new FileWriter(new File(file));
            for (User user:users) {
                writer.write(user.getUsername() + ";" + user.getName() + ";" + user.getPassword() + ";" + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * käyttäjätietojen haku tiedostosta listana
     * @return lista, joka sisältää kaikki käyttäjät
     */
    public List<User> getAll() {
        return users;
    }
    
    /**
     * käyttäjätietojen haku käyttäjätiedostosta
     * @param username
     * @return käyttäjä
     */
    public User findUsername(String username) {
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }
    
    /**
     * käyttäjän lisääminen käyttäjätiedostoon
     * @param user
     */
    public void create(User user) {
        users.add(user);
        save();
    }
    
}
