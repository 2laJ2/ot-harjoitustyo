package jasentietokannanhallinta.domain;

public class User {
 
    private String username;
    private String name;
    
    public User(String username, String name) {
        this.username=username;
        this.name=name;
    }
    
    public void setUsername(String username) {
        this.username=username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setName(String name) {
        this.name=name;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj){
        User other=(User)obj;
        return username.equals(other.username);
    }
}

