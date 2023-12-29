package Items;

import Utils.ArmorType;
import Utils.ItemType;
import Utils.PlayerClass;

public class Armor extends Item {
    // Constants
    private final static String CLASS1_STARTING_ARMOR_NAME = "Rusty Plate Armor", CLASS2_STARTING_ARMOR_NAME = "Old Leather Armor", CLASS3_STARTING_ARMOR_NAME = "Tattered Robe";
    private final static int CLASS1_STARTING_DEF = 20, CLASS2_STARTING_DEF = 10, CLASS3_STARTING_DEF = 5;
    private final static ArmorType CLASS1_STARTING_WEAPON_TYPE = ArmorType.PLATE_ARMOR, CLASS2_STARTING_WEAPON_TYPE = ArmorType.LEATHER_ARMOR, CLASS3_STARTING_WEAPON_TYPE = ArmorType.ROBE;
    // End of constants
    
    private int defence;
    private ArmorType armorType;

    public Armor(PlayerClass playerClass){
        // Starting armor constructor, only receives player class
        switch (playerClass){
            case PlayerClass.CLASS1:
                this.name = CLASS1_STARTING_ARMOR_NAME;
                this.itemType = ItemType.ARMOR;
                this.defence = CLASS1_STARTING_DEF;
                this.armorType = CLASS1_STARTING_WEAPON_TYPE;
                break;
            case PlayerClass.CLASS2:
                this.name = CLASS2_STARTING_ARMOR_NAME;
                this.itemType = ItemType.ARMOR;
                this.defence = CLASS2_STARTING_DEF;
                this.armorType = CLASS2_STARTING_WEAPON_TYPE;
                break;
            case PlayerClass.CLASS3:
                this.name = CLASS3_STARTING_ARMOR_NAME;
                this.itemType = ItemType.ARMOR;
                this.defence = CLASS3_STARTING_DEF;
                this.armorType = CLASS3_STARTING_WEAPON_TYPE;
                break;
        }
    }

    public Armor(String name, int defence, ArmorType armorType){
        // Generic constructor
        this.name = name;
        this.itemType = ItemType.ARMOR;
        this.defence = defence;
        this.armorType = armorType;
    }

    public int getDefence(){
        return this.defence;
    }

    public void setDefence(int defence){
        this.defence = defence;
    }

    public ArmorType getArmorType(){
        return this.armorType;
    }

    public void setArmorType(ArmorType armorType){
        this.armorType = armorType;
    }
}
