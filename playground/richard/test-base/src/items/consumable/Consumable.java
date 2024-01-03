package items.consumable;

import items.Item;
import items.ItemType;

public final class Consumable extends Item {
    private ConsumableType consumableType;
    private int turnsDuration;

    public Consumable(
        ConsumableType consumableType, String name, 
        int boostHP, int boostMP, int boostDamage
    ) {
        super(ItemType.CONSUMABLE, name);
        this.consumableType = consumableType;

        this.boostHP = boostHP;
        this.boostMP = boostMP;
        this.boostDamage = boostDamage;
    }

    public ConsumableType getConsumableType() { return consumableType; }
    public int getTurnsDuration() { return turnsDuration; }
}
