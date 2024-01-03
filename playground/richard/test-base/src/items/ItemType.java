package items;

public enum ItemType {
    WEAPON("Weapon"), 
    ARMOR("Armor"), 
    CONSUMABLE("Consumable");

    private final String itemTypeName;

    private ItemType(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    @Override
    public String toString() { return this.itemTypeName; }
}
