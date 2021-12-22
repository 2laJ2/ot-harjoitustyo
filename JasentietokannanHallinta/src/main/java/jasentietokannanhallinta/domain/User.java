package jasentietokannanhallinta.domain;

/**
 * Käyttäjää kuvaava luokka
 */
public class User {
 
    private String username;
    private String name;
    private String password;
    
    /**
     * Käyttäjää kuvaavan luokan konstruktori
     * @param username
     * @param name
     * @param password
     */
    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }
    
    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
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
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        User other = (User) obj;
        return username.equals(other.username);
    }
}

