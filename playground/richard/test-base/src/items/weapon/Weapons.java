package items.weapon;

public final class Weapons {
    public static final int INDEX_INITIAL_WEAPON = 0;

    private static final Weapon[] WEAPONS_MAGE = {
        new Weapon(
            WeaponType.STAFF, 
            "Rotting Staff", 
            50, 
            0, 
            0.05, 
            0.05, 
            0.1,
            "Made from a rotting magical tree, sometimes it backfires"
        ), 

        new Weapon(
            WeaponType.STAFF, 
            "Eldritch Staff", 
            200, 
            30, 
            0.15, 
            0.2, 
            1.5, 
            "Made from a branch droped by the olsdest tree in the realm"
        )
    };

    private static final Weapon[] WEAPONS_WARRIOR = {
        new Weapon(
            WeaponType.SWORD, 
            "Rusty Sword", 
            40, 
            30,  
            0.05, 
            0.05, 
            0.1,
            "Its rustiness is a double edge sword, for you and for your enemies"
        ),

        new Weapon(
            WeaponType.SWORD, 
            "Hero of Salvation Sword", 
            180, 
            100,  
            0.15, 
            0.3, 
            1.0, 
            "Made to protect the realm against the invasion"
        )
    };

    private static final Weapon[] WEAPONS_ARCHER = {
        new Weapon(
            WeaponType.BOW, 
            "Old Bow", 
            45, 
            0, 
            0.05, 
            0.05, 
            0.1, 
            "An archer is made by their habilities not the weapon, but this one.. well.. be carefull"
        ),

        new Weapon(
            WeaponType.BOW, 
            "Chaos Bow", 
            190, 
            50, 
            0.45, 
            0.3, 
            1.2, 
            "No enemy shall enter the realm, cause its unpredictable shots shall shot them all"
        )
    };

    public static Weapon getWeaponMage(int index) {
        if(index < 0 || index >= WEAPONS_MAGE.length) return null;
        return WEAPONS_MAGE[index];
    }

    public static Weapon getWeaponWarrior(int index) {
        if(index < 0 || index >= WEAPONS_WARRIOR.length) return null;
        return WEAPONS_WARRIOR[index];
    }

    public static Weapon getWeaponArcher(int index) {
        if(index < 0 || index >= WEAPONS_ARCHER.length) return null;
        return WEAPONS_ARCHER[index];
    }
}
