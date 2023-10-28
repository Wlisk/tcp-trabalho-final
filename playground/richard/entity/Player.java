package entity;
import items.Item;

//*********************************************//
// CONTROLS AND MANAGES THE PLAYER
//*********************************************//
public class Player extends Entity implements IPlayer {
    private static final int MAX_INVENTORY_SIZE = 10;
    final Item[] inventory = new Item[MAX_INVENTORY_SIZE];

    public Player() {
        inventory[0].type = Item.Type.CONSUMABLE;
    }

    public void draw() {}

    public void attack(Entity enimy) {}
}
