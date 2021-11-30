package jasentietokannanhallinta.domain;

public class User {
 
    private String username;
    private String name;
    private String password;
    
    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPassword() {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        User other = (User) obj;
        return username.equals(other.username);
    }
}

