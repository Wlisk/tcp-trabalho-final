package items.weapon;

import entities.player.ClassType;
import scene.TextureId;
import utils.Randomic;

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
            "Made from a branch from a magical tree."
        ), 

        new Weapon(
            WeaponType.STAFF, 
            TextureId.WEAPON_STAFF_2,
            "Apprentice's Staff", 
            100, 
            30, 
            0.1, 
            0.1, 
            0.5, 
            "A typical staff for a student of magic."
        ),

        new Weapon(
            WeaponType.STAFF, 
            TextureId.WEAPON_STAFF_3,
            "Necromancer's Staff", 
            200, 
            50, 
            0.15, 
            0.15, 
            1.0, 
            "Belonged to a master of the dark arts."
        ),

        new Weapon(
            WeaponType.STAFF, 
            TextureId.WEAPON_STAFF_4,
            "Holy Staff", 
            400, 
            80, 
            0.2, 
            0.2, 
            1.5, 
            "Inbued with the power of a star."
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
            "Claymore", 
            80, 
            45,  
            0.15, 
            0.15, 
            0.25,
            "A classic, well balanced blade."
        ),

        new Weapon(
            WeaponType.SWORD, 
            TextureId.WEAPON_SWORD_3,
            "Shortsword", 
            150, 
            60,  
            0.10, 
            0.20, 
            0.6,
            "Don't let its small stature fool you."
        ),

        new Weapon(
            WeaponType.SWORD, 
            TextureId.WEAPON_SWORD_4,
            "Hero of Salvation Sword", 
            250, 
            100,  
            0.10, 
            0.3, 
            1.0, 
            "It has slain more foes than one can imagine."
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
            "A bow fit for a beginner archer."
        ),

        new Weapon(
            WeaponType.BOW, 
            TextureId.WEAPON_BOW_2,
            "Reinforced Bow", 
            90, 
            20, 
            0.15, 
            0.10, 
            0.1, 
            "Can hit a target from a mile away."
        ),

        new Weapon(
            WeaponType.BOW, 
            TextureId.WEAPON_BOW_3,
            "Curved Bow", 
            150, 
            40, 
            0.30, 
            0.15, 
            0.1, 
            "Its curved design is quite striking."
        ),

        new Weapon(
            WeaponType.BOW, 
            TextureId.WEAPON_BOW_4,
            "Elven Bow", 
            240, 
            60, 
            0.5, 
            0.3, 
            1.3, 
            "Not a soul shall remain alive."
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

    private static final Weapon getRandomWeaponMage(){ return WEAPONS_MAGE[Randomic.between(0, WEAPONS_MAGE.length - 1)]; }
    private static final Weapon getRandomWeaponWarrior(){ return WEAPONS_WARRIOR[Randomic.between(0, WEAPONS_WARRIOR.length - 1)]; }
    private static final Weapon getRandomWeaponArcher(){ return WEAPONS_ARCHER[Randomic.between(0, WEAPONS_ARCHER.length - 1)]; }

    public static final Weapon getRandomWeapon(ClassType classType) {
        switch (classType) {
            case MAGE:
                return getRandomWeaponMage();
            case WARRIOR:
                return getRandomWeaponWarrior();
            case ARCHER:
                return getRandomWeaponArcher();
            default:
                return null;
        }
    }
}
