package business.entities;

public class Game {

    private String gameName;
    private int players;
    private int impostors;
    private String color;
    private String map;
    private String creator;


    public Game(String gameName, int players, int impostors, String color, String map, String creator) {
        this.gameName = gameName;
        this.players = players;
        this.impostors = impostors;
        this.color = color;
        this.map = map;
        this.creator = creator;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getImpostors() {
        return impostors;
    }

    public void setImpostors(int impostors) {
        this.impostors = impostors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
