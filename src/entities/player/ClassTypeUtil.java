package entities.player;

import exceptions.UnknownTypeException;
import items.armor.Armor;
import items.armor.Armors;
import items.weapon.Weapon;
import items.weapon.Weapons;
import scene.TextureId;

public final class ClassTypeUtil {
    private static final int 
        BASE_HP = 1000,
        BASE_MP = 100,
        BASE_DAMAGE = 200,
        BASE_DEFENSE = 80;

    private static final double 
        BASE_CRIT_C = 1.0,
        BASE_CRIT_M = 1.0,
        BASE_ACCURACY = 1.0;

    private static ClassType $classType = null;
    public static final String ERR_TYPE_MESSAGE = "The class type specified is unknown";
    
    private static double getDPercentage(double percent) {
        return percent * 0.01;
    }

    public static ClassType getClassType() { return $classType; }
    public static ClassType setClassType(ClassType classType) {
        $classType = classType;
        return $classType;
    }

    public static int getHP() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return (int)( BASE_HP * getDPercentage(70) );
            case WARRIOR: return (int)( BASE_HP * getDPercentage(100) );
            case ARCHER: return (int)( BASE_HP * getDPercentage(85) );
            default: 
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }

    public static int getMP() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return (int)( BASE_MP * getDPercentage(100) );
            case WARRIOR: return (int)( BASE_MP * getDPercentage(70) );
            case ARCHER: return (int)( BASE_MP * getDPercentage(85) );
            default: 
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }

    public static int getDamage() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return (int)( BASE_DAMAGE * getDPercentage(90) );
            case WARRIOR: return (int)( BASE_DAMAGE * getDPercentage(80) );
            case ARCHER: return (int)( BASE_DAMAGE * getDPercentage(70) );
            default: 
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }

    public static int getDefense() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return (int)( BASE_DEFENSE * getDPercentage(70) );
            case WARRIOR: return (int)( BASE_DEFENSE * getDPercentage(90) );
            case ARCHER: return (int)( BASE_DEFENSE * getDPercentage(80) );
            default: 
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }

    public static double getCritChance() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return BASE_CRIT_C * getDPercentage(20);
            case WARRIOR: return BASE_CRIT_C * getDPercentage(20);
            case ARCHER: return BASE_CRIT_C * getDPercentage(20);
            default: 
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }

    public static double getCritMultiplier() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return BASE_CRIT_M * getDPercentage(115);
            case WARRIOR: return BASE_CRIT_M * getDPercentage(110);
            case ARCHER: return BASE_CRIT_M * getDPercentage(115);
            default: 
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }

    public static double getAccuracy() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return BASE_ACCURACY * getDPercentage(85);
            case WARRIOR: return BASE_ACCURACY * getDPercentage(80);
            case ARCHER: return BASE_ACCURACY * getDPercentage(90);
            default: 
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }

    public static Armor getInitialArmor() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return Armors.getArmorMage(Armors.INDEX_INITIAL_ARMOR);
            case WARRIOR: return Armors.getArmorWarrior(Armors.INDEX_INITIAL_ARMOR);
            case ARCHER: return Armors.getArmorArcher(Armors.INDEX_INITIAL_ARMOR);
            default: 
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }

    public static Weapon getInitialWeapon() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return Weapons.getWeaponMage(Weapons.INDEX_INITIAL_WEAPON);
            case WARRIOR: return Weapons.getWeaponWarrior(Weapons.INDEX_INITIAL_WEAPON);
            case ARCHER: return Weapons.getWeaponArcher(Weapons.INDEX_INITIAL_WEAPON);
            default: 
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }

    public static TextureId getTextureId() throws UnknownTypeException {
        switch($classType) {
            case MAGE: return TextureId.CLASS_1;
            case WARRIOR: return TextureId.CLASS_2;
            case ARCHER: return TextureId.CLASS_3;
            default:
                throw new UnknownTypeException(ERR_TYPE_MESSAGE);
        }
    }
}
