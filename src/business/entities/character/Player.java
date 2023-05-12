package business.entities.character;

public class Player extends Character{
    public Player(String color, int xCoordinate, int yCoordinate) {
        super(color, xCoordinate, yCoordinate);
    }
    // Parametrized constructor
    public Player(String color) {super(color);}
}
