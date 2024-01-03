package items.armor;

import items.Item;
import items.ItemType;

public final class Armor extends Item {
    private ArmorType armorType;

    public Armor(
        ArmorType armorType, String name, 
        int boostDefense, int boostHP, int boostMP
    ) 
    {
        super(ItemType.ARMOR, name);
        this.armorType = armorType;

        this.boostDefense = boostDefense;
        this.boostHP = boostHP;
        this.boostMP = boostMP;
    }

    public ArmorType getArmorType() { return armorType; }
}
