package items.armor;

public final class Armors {
    public static final int INDEX_INITIAL_ARMOR = 0;

    private static final Armor[] ARMORS_MAGE = {
        new Armor(
            ArmorType.ROBE, 
            "Tattered Robe" , 
            20, 
            //20, 
            //100, 
            "It has seen better days"
        )
    };

    private static final Armor[] ARMORS_WARRIOR = {
        new Armor(
            ArmorType.PLATE, 
            "Rusty Plate Armor", 
            50, 
            //100, 
            //20,
            "Be aware that any punch can scraps it"
        )
    };

    private static final Armor[] ARMORS_ARCHER = {
        new Armor(
            ArmorType.LEATHER, 
            "Old Leather Armor", 
            35, 
            //60, 
            //60,
            "Sew with rats leather"
        )
    };

    public static Armor getArmorMage(int index) {
        if(index < 0 || index >= ARMORS_MAGE.length) return null;
        return ARMORS_MAGE[index];
    }

    public static Armor getArmorWarrior(int index) {
        if(index < 0 || index >= ARMORS_WARRIOR.length) return null;
        return ARMORS_WARRIOR[index];
    }

    public static Armor getArmorArcher(int index) {
        if(index < 0 || index >= ARMORS_ARCHER.length) return null;
        return ARMORS_ARCHER[index];
    }
}
