package game;
public class User {
    private String username;
    private int victories;

    public User(String username, int victories) {
        this.username = username;
        this.victories = victories;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
