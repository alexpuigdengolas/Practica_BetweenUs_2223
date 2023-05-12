package business.entities.character;

public class Npc extends Character{
    private int previousRoom;
    public Npc(String color, int xCoordinate, int yCoordinate, int previousRoom) {
        super(color, xCoordinate, yCoordinate);
        this.previousRoom = previousRoom;
    }

    // Parametrized constructor

}
