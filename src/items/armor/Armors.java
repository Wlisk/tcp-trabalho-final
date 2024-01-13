package items.armor;

import scene.TextureId;
import utils.Randomic;
import entities.player.ClassType;

public final class Armors {
    public static final int INDEX_INITIAL_ARMOR = 0;

    private static final Armor[] ARMORS_MAGE = {
        new Armor(
            ArmorType.ROBE, 
            TextureId.ARMOR_LIGHT_1,
            "Apprentice's Robe", 
            20, 
            "It has seen better days."
        ),

        new Armor(
            ArmorType.ROBE, 
            TextureId.ARMOR_LIGHT_2,
            "Disciple's Robe", 
            40, 
            "An item fit for a young scholar."
        ),

        new Armor(
            ArmorType.ROBE, 
            TextureId.ARMOR_LIGHT_3,
            "Master's Robe", 
            70, 
            "An ancient robe in excellent condition."
                        
        )

    };

    private static final Armor[] ARMORS_WARRIOR = {
        new Armor(
            ArmorType.PLATE, 
            TextureId.ARMOR_HEAVY_1,
            "Rusty Plate Armor", 
            50, 
            "Be aware that any punch can scrap it."
        ), 

        new Armor(
            ArmorType.PLATE, 
            TextureId.ARMOR_HEAVY_2,
            "Dwarvish Armor", 
            100, 
            "Typical dwarvish armor. Quite sturdy."
        ),

        new Armor(
            ArmorType.PLATE, 
            TextureId.ARMOR_HEAVY_3,
            "Dragonscale Armor", 
            150, 
            "Made from an ice dragon's scales."
        )
    };

    private static final Armor[] ARMORS_ARCHER = {
        new Armor(
            ArmorType.LEATHER, 
            TextureId.ARMOR_MEDIUM_1,
            "Old Chainmail Armor", 
            35, 
            "Quite old and rusty.."
        ),

        new Armor(
            ArmorType.LEATHER, 
            TextureId.ARMOR_MEDIUM_2,
            "Kusari", 
            70, 
            
            "Traditional armor in excellent condition."
        ),
        new Armor(
            ArmorType.LEATHER, 
            TextureId.ARMOR_MEDIUM_3,
            "Enchanted Chainmail", 
            120, 
            "Created by a master of the dark arts."
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

    private static final Armor getRandomArmorMage(){ return ARMORS_MAGE[Randomic.between(0, ARMORS_MAGE.length - 1)]; }
    private static final Armor getRandomArmorWarrior(){ return ARMORS_WARRIOR[Randomic.between(0, ARMORS_WARRIOR.length - 1)]; }
    private static final Armor getRandomArmorArcher(){ return ARMORS_ARCHER[Randomic.between(0, ARMORS_ARCHER.length - 1)]; }
    
    public static final Armor getRandomArmor(ClassType classType) {
        switch (classType) {
            case MAGE:
                return getRandomArmorMage();
            case WARRIOR:
                return getRandomArmorWarrior();
            case ARCHER:
                return getRandomArmorArcher();
            default:
                return null;
        }
    }
}
