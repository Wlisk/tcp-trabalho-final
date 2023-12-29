package Items;

import Utils.WeaponType;
import Utils.ItemType;
import Utils.PlayerClass;

public class Weapon extends Item {
    // Constants
    private final static String CLASS1_STARTING_WEAPON_NAME = "Rusty Sword", CLASS2_STARTING_WEAPON_NAME = "Old Bow", CLASS3_STARTING_WEAPON_NAME = "Rotting Staff";
    private final static WeaponType CLASS1_STARTING_WEAPON_TYPE = WeaponType.SWORD, CLASS2_STARTING_WEAPON_TYPE = WeaponType.BOW, CLASS3_STARTING_WEAPON_TYPE = WeaponType.STAFF;

    // End of constants

    private double damageMultiplier;
    private double accuracyMultiplier;
    private double critChanceMultiplier;
    private WeaponType weaponType;

    public Weapon(PlayerClass playerClass){
        // Starting weapon constructor, only receives player class
        switch (playerClass){
            case PlayerClass.CLASS1:
                this.name = CLASS1_STARTING_WEAPON_NAME;
                this.itemType = ItemType.WEAPON;
                this.damageMultiplier = 1;
                this.accuracyMultiplier = 1;
                this.critChanceMultiplier = 1;
                this.weaponType = CLASS1_STARTING_WEAPON_TYPE;
                break;
            case PlayerClass.CLASS2:
                this.name = CLASS2_STARTING_WEAPON_NAME;
                this.itemType = ItemType.WEAPON;
                this.damageMultiplier = 1;
                this.accuracyMultiplier = 1;
                this.critChanceMultiplier = 1;
                this.weaponType = CLASS2_STARTING_WEAPON_TYPE;
                break;
            case PlayerClass.CLASS3:
                this.name = CLASS3_STARTING_WEAPON_NAME;
                this.itemType = ItemType.WEAPON;
                this.damageMultiplier = 1;
                this.accuracyMultiplier = 1;
                this.critChanceMultiplier = 1;
                this.weaponType = CLASS3_STARTING_WEAPON_TYPE;
                break;
        }
    }

    public Weapon(String name, double dmgMult, double accMult, double critMult, WeaponType weaponType){
        // Generic constructor
        this.name = name;
        this.itemType = ItemType.WEAPON;
        this.damageMultiplier = dmgMult;
        this.accuracyMultiplier = accMult;
        this.critChanceMultiplier = critMult;
        this.weaponType = weaponType;
    }

    public double getDamageMultiplier(){
        return this.damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier){
        this.damageMultiplier = damageMultiplier;
    }

    public double getAccuracyMultiplier(){
        return this.accuracyMultiplier;
    }

    public void setAccuracyMultiplier(double accuracyMultiplier){
        this.accuracyMultiplier = accuracyMultiplier;
    }

    public double getCritChanceMultiplier(){
        return this.critChanceMultiplier;
    }

    public void setCritChanceMultiplier(double critChanceMultiplier){
        this.critChanceMultiplier = critChanceMultiplier;
    }

    public WeaponType getWeaponType(){
        return this.weaponType;
    }

    public void setWeaponType(WeaponType weaponType){
        this.weaponType = weaponType;
    }
}
