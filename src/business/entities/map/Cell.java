package business.entities.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

/**
 * Esta clase representara las casillas de nuestro mapa
 */
public class Cell {

    @SerializedName("x")
    @Expose
    private int x;
    @SerializedName("y")
    @Expose
    private int y;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("roomName")
    @Expose
    private String roomName;
    @SerializedName("mobility")
    @Expose
    private Mobility mobility;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("alcantarilla")
    @Expose
    private final LinkedList<String> alcantarilla = null;
    private int numCorpses = 0;

    /**
     * Getter de la coordenada x de la posicion de la casilla
     * @return un entero que representa la coordenada X
     */
    public int getX() {
        return x;
    }

    /**
     * Getter de la coordenada y de la posicion de la casilla
     * @return un entero que representa la coordenada Y
     */
    public int getY() {
        return y;
    }

    /**
     * Getter del tipo de casilla
     * @return Un string con el tipo de casilla
     */
    public String getType() {
        return type;
    }

    /**
     * Getter del nombre de la casilla
     * @return El nombre de la casilla
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Getter de las direcciones a las que se puede mover un personaje que se encuentre ne esta casilla
     * @return Las direcciones a las que se puede mover
     */
    public Mobility getMobility() {
        return mobility;
    }

    /**
     * Getter del color que tendra la casilla
     * @return el nombre del color de la casilla
     */
    public String getColor() {
        return color;
    }

    /**
     * Getter de las alcantarillas de la habitacion
     * @return Un listado de los sitios hacia los que se puede ir por una alcantarilla
     */
    public LinkedList<String> getAlcantarilla() {
        return alcantarilla;
    }

    /**
     * Getter del numero de muertos que hay en esa habitacion
     * @return el numero de muertos
     */
    public int getNumCorpses() {
        return numCorpses;
    }

    /**
     * Setter del numero de muertos de la habitacion
     * @param numCorpses el numero de muertos
     */
    public void setNumCorpses(int numCorpses) {
        this.numCorpses = numCorpses;
    }

}
