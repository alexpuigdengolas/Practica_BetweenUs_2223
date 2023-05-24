package business;

/**
 * Es la clase para representar los logs de las partidas
 */
public class Log {
    private final String name;
    private final String room;
    private final int instant;

    /**
     * El constructor de la clase
     * @param name el nombre de los jugadores
     * @param room la habitación en la que se encuentran
     * @param instant el instante ne le que han accedido a la habitacion
     */
    public Log(String name, String room, int instant) {
        this.name = name;
        this.room = room;
        this.instant = instant;
    }

    /**
     * Getter del nombre del jugador
     * @return el nombre del jugador
     */
    public String getName() {
        return name;
    }

    /**
     * Getter del nombre de la habitacion a la que ha a ccedido el jugador
     * @return el nombre de la habitacion
     */
    public String getRoom() {
        return room;
    }

    /**
     * Getter del instante en el que se ha accedido a la habitacion
     * @return el segundo en el que se ha accedido
     */
    public int getInstant() {
        return instant;
    }
}
