package items.armor;

import items.Item;
import items.ItemType;

public final class Armor extends Item {
    private ArmorType armorType;

    public Armor(
        ArmorType armorType, String name, 
        int boostDefense, int boostHP, int boostMP, String description
    ) 
    {
        super(ItemType.ARMOR, name, description);
        this.armorType = armorType;

        this.boostDefense = boostDefense;
        this.boostHP = boostHP;
        this.boostMP = boostMP;
    }

    public ArmorType getArmorType() { return armorType; }
}
