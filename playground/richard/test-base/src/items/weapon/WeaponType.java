package items.weapon;

public enum WeaponType {
    SWORD("Sword"), 
    BOW("Bow"), 
    STAFF("Staff");

    private final String weaponTypeName;

    private WeaponType(String weaponTypeName) {
        this.weaponTypeName = weaponTypeName;
    }

    @Override
    public String toString() { return this.weaponTypeName; }
}
