package items.consumable;

import items.Item;
import items.ItemType;
import scene.TextureId;

public final class Consumable extends Item {
    private ConsumableType consumableType;
    private int turnsDuration;

    public Consumable(
        ConsumableType consumableType, TextureId textureId, String name, 
        int boostHP, int boostMP, /*int boostDamage,*/ String description
    ) {
        super(ItemType.CONSUMABLE, textureId, name, description);
        this.consumableType = consumableType;

        this.boostHP = boostHP;
        this.boostMP = boostMP;
        //this.boostDamage = boostDamage;
    }

    public ConsumableType getConsumableType() { return consumableType; }
    public int getTurnsDuration() { return turnsDuration; }
}
