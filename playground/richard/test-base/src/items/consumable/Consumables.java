package items.consumable;

public class Consumables {
    private static final Consumable[] FOODS = {
        new Consumable(
            ConsumableType.FOOD, "Piece of Bread", 
            50, 0, 0
        ), 

        new Consumable(
            ConsumableType.FOOD, "Apple\'s Pie", 
            50, 50, 0
        )
    };

    public static final int INDEX_POTION_HP = 0;
    public static final int INDEX_POTION_MP = 1;
    private static final Consumable[] POTIONS = {
        new Consumable(
            ConsumableType.POTION, "Vial of Healing", 
            100, 0, 0
        ),

        new Consumable(
            ConsumableType.POTION, "Vial of Mana", 
            0, 100, 0
        )
    };

    private static final Consumable[] POISONS = {
        new Consumable(
            ConsumableType.POISON, "Drake\'s Blood", 
            0, 0, 50
        ),

        new Consumable(
            ConsumableType.POISON, "Death\'s Touch", 
            0, 0, 100
        )
    };

    public static Consumable getConsumableFood(int index) {
        if(index < 0 || index >= FOODS.length) return null;
        return FOODS[index];
    }

    public static Consumable getConsumablePotion(int index) {
        if(index < 0 || index >= POTIONS.length) return null;
        return POTIONS[index];
    }

    public static Consumable getConsumablePoison(int index) {
        if(index < 0 || index >= POISONS.length) return null;
        return POISONS[index];
    }
}
