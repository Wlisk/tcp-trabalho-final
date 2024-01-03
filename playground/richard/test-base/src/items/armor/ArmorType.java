package items.armor;

public enum ArmorType {
    PLATE_ARMOR("Plate Armor"), 
    LEATHER_ARMOR("Leather Armor"), 
    ROBE("Robe");

    private final String armorTypeName;

    private ArmorType(String armorTypeName) {
        this.armorTypeName = armorTypeName;
    }

    @Override
    public String toString() { return this.armorTypeName; }
}
