package items.consumable;

import scene.TextureId;
import utils.Randomic;

public final class Consumables {
    private static final Consumable[] FOODS = {
        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_BREAD, "Piece of Bread", 
            100, 0,
            "Better than nothing."
        ), 

        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_CHEESE, "Cheese", 
            150, 50,
            "Just a regular ol\' piece of cheese."
        ),

        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_BANANA, "Banana", 
            75, 75, 
            "The quintessential fruit."),

        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_GRAPES, "Grapes", 
            50, 100, 
            "A little sour."),
        
        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_PIZZA, "Pizza", 
            250, 150, 
            "A staple of Italian cuisine."),
        
        new Consumable(
                ConsumableType.FOOD, TextureId.CONSUMABLE_STEAK, "Steak", 
                500, 100, 
                "A perfectly cooked slab of meat.")
    };

    

    public static final int INDEX_POTION_HP = 0;
    public static final int INDEX_POTION_MP = 1;
    private static final Consumable[] POTIONS = {
        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_RED_VIAL, "Vial of Healing", 
            100, 0, 
            "A small dose of a healing tincture."
        ),

        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_BLUE_VIAL, "Vial of Mana", 
            0, 100,
            "A small dose of an energizing tincture."
        ),
        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_RED_SMALL, "Small Flask of Healing", 
            250, 0, 
            "Can save you from dire straits."
        ),

        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_BLUE_SMALL, "Small Flask of Mana", 
            0, 250,
            "Power in liquid form."
        ),

        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_RED_BIG, "Large Flask of Healing", 
            500, 0, 
            "Will save you from the brink of destruction."
        ),

        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_BLUE_BIG, "Large Flask of Mana", 
            0, 500,
            "Can turn a battle around."
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
    
    public static final Consumable getRandomFood(){ return FOODS[Randomic.between(0, FOODS.length - 1)]; }
    public static final Consumable getRandomPotion(){ return POTIONS[Randomic.between(0, FOODS.length - 1)]; }

    /*public static Consumable getConsumablePoison(int index) {
        if(index < 0 || index >= POISONS.length) return null;
        return POISONS[index];
    }*/
}
