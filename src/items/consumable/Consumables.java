package items.consumable;

import scene.TextureId;

public final class Consumables {
    private static final Consumable[] FOODS = {
        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_BREAD, "Piece of Bread", 
            50, 0, /*0,*/
            "Better than nothing."
        ), 

        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_CHEESE, "Cheese", 
            50, 50, /*0,*/
            "Just a regular ol\' piece of cheese."
        )
    };

    public static final int INDEX_POTION_HP = 0;
    public static final int INDEX_POTION_MP = 1;
    private static final Consumable[] POTIONS = {
        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_RED_SMALL, "Small Vial of Healing", 
            100, 0, 
            /*0,*/
            "Can save you from dire straits."
        ),

        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_BLUE_SMALL, "Small Vial of Mana", 
            0, 100, /*0,*/
            "Power in liquid form."
        )
    };

    /*private static final Consumable[] POISONS = {
        new Consumable(
            ConsumableType.POISON, "Drake\'s Blood", 
            0, 0, 
            50,
            "A bit diluted but still strong"
        ),

        new Consumable(
            ConsumableType.POISON, "Death\'s Touch", 
            0, 0, 
            100,
            "One touch and your are dead"
        )
    };*/

    public static Consumable getConsumableFood(int index) {
        if(index < 0 || index >= FOODS.length) return null;
        return FOODS[index];
    }

    public static Consumable getConsumablePotion(int index) {
        if(index < 0 || index >= POTIONS.length) return null;
        return POTIONS[index];
    }

    /*public static Consumable getConsumablePoison(int index) {
        if(index < 0 || index >= POISONS.length) return null;
        return POISONS[index];
    }*/
}
