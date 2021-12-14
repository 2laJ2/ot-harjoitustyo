package jasentietokannanhallinta.domain;

/**
 * Jäsentä kuvaava luokka
 */
public class Jasentiedot {
    
    private int id;
    private String name;
    private String address;
    private String phone;
    private User user;
    
    /**
     * Jäsentä kuvaavan luokan konstruktori
     * @param id
     * @param name
     * @param address
     * @param phone
     * @param user
     */
    public Jasentiedot(int id, String name, String address, String phone, User user) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.user = user;
    }
    
    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * 
     * @return 
     */
    public int getId() {
        return id;
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Jasentiedot other = (Jasentiedot) obj;
        return id == other.id;
    }
    
   
}
