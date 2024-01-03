package items.armor;

public class Armors {
    public static final int INDEX_INITIAL_ARMOR = 0;

    private static final Armor[] ARMOR_MAGE = {
        new Armor(
            ArmorType.ROBE, 
            "Tattered Robe" , 
            20, 
            20, 
            100
        )
    };

    private static final Armor[] ARMOR_WARRIOR = {
        new Armor(
            ArmorType.PLATE_ARMOR, 
            "Rusty Plate Armor", 
            50, 
            100, 
            20
        )
    };

    private static final Armor[] ARMOR_ARCHER = {
        new Armor(
            ArmorType.LEATHER_ARMOR, 
            "Old Leather Armor", 
            35, 
            60, 
            60
        )
    };

    public static Armor getArmorMage(int index) {
        if(index < 0 || index >= ARMOR_MAGE.length) return null;
        return ARMOR_MAGE[index];
    }

    public static Armor getArmorWarrior(int index) {
        if(index < 0 || index >= ARMOR_WARRIOR.length) return null;
        return ARMOR_WARRIOR[index];
    }

    public static Armor getArmorArcher(int index) {
        if(index < 0 || index >= ARMOR_ARCHER.length) return null;
        return ARMOR_ARCHER[index];
    }
}
