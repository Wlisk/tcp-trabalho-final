package items;

//*********************************************//
// BASE FOR EVERY ITEM IN THE GAME (lIKE OPTIONS, WEAPONS, CLOTHES, ARMOR, ETC.)
//*********************************************//
public class Item {
    public static enum Type { CONSUMABLE, WEAPON, ARMOR }

    String name;
    public Type type;
}
