package items.armor;

public enum ArmorType {
    PLATE("Plate"), 
    LEATHER("Leather"), 
    ROBE("Robe");

    private final String armorTypeName;

    private ArmorType(String armorTypeName) {
        this.armorTypeName = armorTypeName;
    }

    @Override
    public String toString() { return this.armorTypeName; }
}
