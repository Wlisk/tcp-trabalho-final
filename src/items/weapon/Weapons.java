package items.weapon;

import scene.TextureId;

public final class Weapons {
    public static final int INDEX_INITIAL_WEAPON = 0;

    private static final Weapon[] WEAPONS_MAGE = {
        new Weapon(
            WeaponType.STAFF, 
            TextureId.WEAPON_STAFF_1,
            "Rotting Staff", 
            50, 
            0, 
            0.05, 
            0.05, 
            0.1,
            "Made from a rotting magical tree, sometimes it backfires."
        ), 

        new Weapon(
            WeaponType.STAFF, 
            TextureId.WEAPON_STAFF_2,
            "Eldritch Staff", 
            200, 
            30, 
            0.15, 
            0.2, 
            1.5, 
            "Made from a branch of the oldest tree in the realm."
        )
    };

    private static final Weapon[] WEAPONS_WARRIOR = {
        new Weapon(
            WeaponType.SWORD, 
            TextureId.WEAPON_SWORD_1,
            "Rusty Sword", 
            40, 
            30,  
            0.05, 
            0.05, 
            0.1,
            "Its rustiness is a double edged sword."
        ),

        new Weapon(
            WeaponType.SWORD, 
            TextureId.WEAPON_SWORD_2,
            "Hero of Salvation Sword", 
            180, 
            100,  
            0.15, 
            0.3, 
            1.0, 
            "Made to protect the realm against the invaders."
        )
    };

    private static final Weapon[] WEAPONS_ARCHER = {
        new Weapon(
            WeaponType.BOW, 
            TextureId.WEAPON_BOW_1,
            "Old Bow", 
            45, 
            0, 
            0.05, 
            0.05, 
            0.1, 
            "This one is... well... be careful."
        ),

        new Weapon(
            WeaponType.BOW, 
            TextureId.WEAPON_BOW_2,
            "Chaos Bow", 
            190, 
            50, 
            0.45, 
            0.3, 
            1.2, 
            "None shall pass."
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
