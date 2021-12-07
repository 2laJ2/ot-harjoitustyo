package jasentietokannanhallinta.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import jasentietokannanhallinta.domain.Jasentiedot;
import jasentietokannanhallinta.domain.User;

public class FileJasentiedotDao {
    
    public List<Jasentiedot> jasentiedotList;
    private String file;
    
    public FileJasentiedotDao(String file, FileUserDao users) {
        jasentiedotList = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[]parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                String name = String.valueOf(parts[1]);
                String address = String.valueOf(parts[2]);
                String phone = String.valueOf(parts[3]);
                boolean done = Boolean.parseBoolean(parts[4]);
                User user = users.getAll().stream().filter(j->j.getUsername().equals(parts[4])).findFirst().orElse(null);
                Jasentiedot jasentiedot = new Jasentiedot(id, parts[1], parts[2], parts[3], done, user);
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
                writer.write(jasentiedot.getId() + "," + jasentiedot.getName() + ";" + 
                             jasentiedot.getAddress() + ";" + jasentiedot.getPhone() + ";" + jasentiedot.isDone() + "," + jasentiedot.getUser().getUsername() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private int generateId() {
        return jasentiedotList.size() + 1;
    }
    
    public List<Jasentiedot> getAll() {
        return jasentiedotList;
    }
    
    public void create(Jasentiedot jasentiedot) {
        jasentiedot.setId(generateId());
        jasentiedotList.add(jasentiedot);
        save();
    }
    
    public void setDone(int id) {
        for (Jasentiedot j:jasentiedotList) {
            if (j.getId() == id) {
                j.setDone();
            }
        }
        save();
    }
    
    public Jasentiedot findMemberName(String name) {
        return jasentiedotList.stream().filter(u->u.getName().equals(name)).findFirst().orElse(null);
    }
}
