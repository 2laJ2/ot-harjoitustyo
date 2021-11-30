package jasentietokannanhallinta.domain;

public class Jasentiedot {
    
    private int id;
    private String name;
    private String address;
    private String phone;
    public boolean done;
    private User user;
    
    public Jasentiedot(int id, String name, String address, String phone, Boolean done, User user) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.done = done;
        this.user = user;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public User getUser() {
        return user;
    }
    
    public boolean isDone() {
        return done;
    }
    
    public void setDone() {
        done = true;
    }
    
    @Override
    public boolean equals(Object obj) {
        Jasentiedot other = (Jasentiedot) obj;
        return id == other.id;
    }
    
    public void setDoneTo(boolean done) {
        this.done = done;
    }
   
}
