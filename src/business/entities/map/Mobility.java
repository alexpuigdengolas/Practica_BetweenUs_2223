package business.entities.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Esta clase representa las direcciones hacia las que se puede mover un jugador dentro de una casilla
 */
public class Mobility {

    @SerializedName("left")
    @Expose
    private Integer left;
    @SerializedName("up")
    @Expose
    private Integer up;
    @SerializedName("right")
    @Expose
    private Integer right;
    @SerializedName("down")
    @Expose
    private Integer down;

    /**
     * Metodo para saber si se puede mover hacia la izquierda
     * @return un entero que nos muestra si se puede o no
     */
    public Integer getLeft() {
        return left;
    }

    /**
     * Metodo para saber si se puede mover hacia la arriba
     * @return un entero que nos muestra si se puede o no
     */
    public Integer getUp() {
        return up;
    }

    /**
     * Metodo para saber si se puede mover hacia la derecha
     * @return un entero que nos muestra si se puede o no
     */
    public Integer getRight() {
        return right;
    }

    /**
     * Metodo para saber si se puede mover hacia la abajo
     * @return un entero que nos muestra si se puede o no
     */
    public Integer getDown() {
        return down;
    }
}