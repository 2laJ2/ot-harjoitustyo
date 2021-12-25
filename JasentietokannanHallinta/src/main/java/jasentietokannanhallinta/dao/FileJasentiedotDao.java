package jasentietokannanhallinta.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import jasentietokannanhallinta.domain.Jasentiedot;
import jasentietokannanhallinta.domain.User;

/**
 * jäsentietojen hallinnasta vastaava luokka
 */
public class FileJasentiedotDao {
    
    /**
     * 
     */
    private List<Jasentiedot> jasentiedotList;
    private String file;
    private FileUserDao users;
    private int lastMember;
    private String removedJasentiedotFile;
    private List<Jasentiedot> removedJasentiedotList;
    private int lastRemovedMember;
    
    /**
     * jäsentietojen hallinnasta vastaavan luokan konstruktori
     * @param file
     * @param users
     * @param removed
     */
    public FileJasentiedotDao(String file, FileUserDao users, String removed) {
        jasentiedotList = new ArrayList<>();
        this.file = file;
        this.users = users;
        this.lastMember = 0;
        this.removedJasentiedotFile = removed;
        this.removedJasentiedotList = new ArrayList<>();
        this.lastRemovedMember = 0;
        
        load(jasentiedotList, file);
        load(removedJasentiedotList, removedJasentiedotFile);
        checkLast();
    } 
    
    private void checkLast() {
        if (lastRemovedMember > lastMember) {
            lastMember = lastRemovedMember;
        }
    }
    
    private void load(List<Jasentiedot> listWanted, String fileWanted) {    
        try {
            Scanner reader = new Scanner(new File(fileWanted));
            while (reader.hasNextLine()) {
                String[]parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                String name = String.valueOf(parts[1]);
                String address = String.valueOf(parts[2]);
                String phone = String.valueOf(parts[3]);
                User user = users.getAll().stream().filter(j->j.getUsername().equals(parts[4])).findFirst().orElse(null);
                Jasentiedot jasentiedot = new Jasentiedot(id, parts[1], parts[2], parts[3], user);
                updateMemberId(id, fileWanted, listWanted);
                listWanted.add(jasentiedot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkLast();
    }
    
    private void updateMemberId(int temp, String fileWanted, List listWanted) {
        if (fileWanted.matches(file)) {
            if (temp > lastMember) {
                lastMember = temp;
            }
        }
        if (fileWanted.matches(removedJasentiedotFile)) {
            if (temp > lastRemovedMember) {
                lastRemovedMember = temp;
            }
        }
    }
    
    private void save(List<Jasentiedot> listWanted, String fileWanted) {
        try {
            FileWriter writer = new FileWriter(new File(fileWanted));
            for (Jasentiedot jasentiedot:listWanted) {
                writer.write(jasentiedot.getId() + ";" + jasentiedot.getName() + ";" + 
                             jasentiedot.getAddress() + ";" + jasentiedot.getPhone() + ";" + jasentiedot.getUser().getUsername() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private int generateId() {
        lastMember += 1;
        return lastMember;
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
        checkLast();
        jasentiedot.setId(generateId());
        jasentiedotList.add(jasentiedot);
        save(jasentiedotList, file);
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
        if (!jasentiedotList.contains(jasentiedot)) {
            return false;
        }
        int temp = jasentiedot.getId();
        if (temp > lastRemovedMember) {
            lastRemovedMember = temp;
        }
        removedJasentiedotList.add(jasentiedot);
        checkLast();
        save(removedJasentiedotList, removedJasentiedotFile);
        jasentiedotList.remove(jasentiedot);
        save(jasentiedotList, file);
        return true;
    }
    
    /**
     * jäsenentietojen haku jäsentiedostosta listana
     * @param 
     * @return jasentiedotlist
     */
    public List getJasentiedotList() {
        return this.jasentiedotList;
    }
    
    /**
     * tallentaa olemassaolevan Jasentiedon muutokset jäsentiedostoon
     * @param newJasentiedot 
     * @param oldJasentiedot 
     */
    public void saveEdit(Jasentiedot newJasentiedot, Jasentiedot oldJasentiedot) {
        jasentiedotList.remove(oldJasentiedot);
        jasentiedotList.add(newJasentiedot);
        save(jasentiedotList, file);
    }
    
    /**
     * suurin uudelle jäsenelle annettu jäsennumero
     * @param 
     * @return jäsennumero
     */
    public int getLastMember() {
        return this.lastMember;
    }
    
    /**
     * suurin poistetulle jäsenelle annettu jäsennumero
     * @param 
     * @return jäsennumero
     */
    public int getLastRemovedMember() {
        return this.lastRemovedMember;
    }
    
}

