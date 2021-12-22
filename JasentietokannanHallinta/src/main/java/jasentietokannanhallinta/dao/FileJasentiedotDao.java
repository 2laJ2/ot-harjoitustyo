package jasentietokannanhallinta.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import jasentietokannanhallinta.domain.Jasentiedot;
import jasentietokannanhallinta.domain.User;

/**
 * Jasentietojen hallinnasta vastaava luokka
 */
public class FileJasentiedotDao {
    
    /**
     * 
     */
    private List<Jasentiedot> jasentiedotList;
    private String file;
    private FileUserDao users;
    
    /**
     * Jäsentietojen hallinnasta vastaavan luokan konstruktori
     * @param file
     * @param users
     */
    public FileJasentiedotDao(String file, FileUserDao users) {
        jasentiedotList = new ArrayList<>();
        this.file = file;
        this.users = users;
        load();
    }    
    
    private void load() {    
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[]parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                String name = String.valueOf(parts[1]);
                String address = String.valueOf(parts[2]);
                String phone = String.valueOf(parts[3]);
                User user = users.getAll().stream().filter(j->j.getUsername().equals(parts[4])).findFirst().orElse(null);
                Jasentiedot jasentiedot = new Jasentiedot(id, parts[1], parts[2], parts[3], user);
                jasentiedotList.add(jasentiedot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void save() {
        try {
            FileWriter writer = new FileWriter(new File(file));
            for (Jasentiedot jasentiedot:jasentiedotList) {
                writer.write(jasentiedot.getId() + ";" + jasentiedot.getName() + ";" + 
                             jasentiedot.getAddress() + ";" + jasentiedot.getPhone() + ";" + jasentiedot.getUser().getUsername() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private int generateId() {
        return jasentiedotList.size() + 1;
    }
    
    /**
     * listan luonti jäsentiedostosta
     * @return jäsentiedot listana
     */
    public List<Jasentiedot> getAll() {
        return jasentiedotList;
    }
    
    /**
     * jäsentietojen luonti ja tallennus jäsentiedostoon
     * @param jasentiedot
     */
    public void create(Jasentiedot jasentiedot) {
        jasentiedot.setId(generateId());
        jasentiedotList.add(jasentiedot);
        save();
    }
    
    /**
     * jäsentietojen haku jäsentiedostosta
     * @param name jäsenen nimi
     * @return jäsentiedot
     */
    public Jasentiedot findMemberName(String name) {
        return jasentiedotList.stream().filter(u->u.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * olemassaolevan jäsenen poistaminen jäsentiedostosta
     * @param jasentiedot
     * @return true, jos jäsen poistaminen on onnistunut, muuten false
     */
    public boolean removeMember(Jasentiedot jasentiedot) {
        jasentiedotList.remove(jasentiedot);
        save();
        return true;
    }
    
}
