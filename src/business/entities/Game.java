package business.entities;

/**
 * Esta clase sirve para representar las partidas
 */
public class Game {

    private String gameName;
    private int players;
    private int impostors;
    private String color;
    private String map;
    private String creator;


    /**
     * Este es el constructor de la clase
     * @param gameName Este es el nombre de la partida
     * @param players entero con los jugadores que se pueden tener en esta partida
     * @param impostors numero de impostores
     * @param color el color del personaje del usuario
     * @param map Nombre del mapa
     * @param creator nombre del ususario que creo la partida
     */
    public Game(String gameName, int players, int impostors, String color, String map, String creator) {
        this.gameName = gameName;
        this.players = players;
        this.impostors = impostors;
        this.color = color;
        this.map = map;
        this.creator = creator;
    }

    /**
     * getter del nombre de la partida
     * @return el nombre de la partida
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Setter del nombre de la partida
     * @param gameName el nombre de la partida
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Getter del numero de jugadores
     * @return el numero de jugadores
     */
    public int getPlayers() {
        return players;
    }

    /**
     * Setter del numero de jugadores
     * @param players el numero de jugadores
     */
    public void setPlayers(int players) {
        this.players = players;
    }

    /**
     * Getter del numero de impostores
     * @return el numero de impostores
     */
    public int getImpostors() {
        return impostors;
    }

    /**
     * Setter del numero de impostores
     * @param impostors el numero de impostores
     */
    public void setImpostors(int impostors) {
        this.impostors = impostors;
    }

    /**
     * Getter del color del personaje del ususario
     * @return el color del usuario
     */
    public String getColor() {
        return color;
    }

    /**
     * Setter del color del usuario
     * @param color el color del usuario
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Getter del nombre del mapa
     * @return el nombre del mapa
     */
    public String getMap() {
        return map;
    }

    /**
     * Setter del nombre del mapa
     * @param map el nombre del mapa
     */
    public void setMap(String map) {
        this.map = map;
    }

    /**
     * Getter del nombre del creador de la partida
     * @return el nombre del creador
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Setter del nombre del creador de la partida
     * @param creator el nombre del creador
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}
