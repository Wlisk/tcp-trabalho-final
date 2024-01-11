package items.armor;

import items.Item;
import items.ItemType;
import scene.TextureId;

public final class Armor extends Item {
    private ArmorType armorType;

    public Armor(
        ArmorType armorType, TextureId textureId, String name, 
        int boostDefense, /*int boostHP, int boostMP,*/ String description
    ) 
    {
        super(ItemType.ARMOR, textureId, name, description);
        this.armorType = armorType;

        this.boostDefense = boostDefense;
        //this.boostHP = boostHP;
        //this.boostMP = boostMP;
    }

    public ArmorType getArmorType() { return armorType; }
}
