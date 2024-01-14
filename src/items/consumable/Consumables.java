package items.consumable;

import scene.TextureId;
import utils.Randomic;

/**
 * Classe com propriedades e métodos estáticos para 
 * criação e armazenamento dos consumíveis do jogo.
 * @see items.consumable.Consumable
 * @see scene.TextureId
 */
public final class Consumables {
    private static final Consumable[] FOODS = {
        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_BREAD, "Piece of Bread", 
            250, 0,
            "Better than nothing."
        ), 

        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_CHEESE, "Cheese", 
            200, 100,
            "Just a regular ol\' piece of cheese."
        ),

        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_BANANA, "Banana", 
            150, 150, 
            "The quintessential fruit."),

        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_GRAPES, "Grapes", 
            120, 200, 
            "A little sour."),
        
        new Consumable(
            ConsumableType.FOOD, TextureId.CONSUMABLE_PIZZA, "Pizza", 
            400, 250, 
            "A staple of Italian cuisine."),
        
        new Consumable(
                ConsumableType.FOOD, TextureId.CONSUMABLE_STEAK, "Steak", 
                700, 100, 
                "A perfectly cooked slab of meat.")
    };

    

    /** Índice (int) da posição da poção de HP básica */
    public static final int INDEX_POTION_HP = 0;
    /** Índice (int) da posição da poção de MP básica */
    public static final int INDEX_POTION_MP = 1;

    private static final Consumable[] POTIONS = {
        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_RED_VIAL, "Vial of Healing", 
            200, 0, 
            "A small dose of a healing tincture."
        ),

        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_BLUE_VIAL, "Vial of Mana", 
            0, 100,
            "A small dose of an energizing tincture."
        ),
        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_RED_SMALL, "Small Flask of Healing", 
            500, 0, 
            "Can save you from dire straits."
        ),

        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_BLUE_SMALL, "Small Flask of Mana", 
            0, 250,
            "Power in liquid form."
        ),

        new Consumable(
            ConsumableType.POTION, TextureId.CONSUMABLE_RED_BIG, "Large Flask of Healing", 
            1000, 0, 
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

    /**
     * Retorna o comsumível do tipo alimento/comida, dado a posição do item na lista de alimentos
     * @param index posição (int) do alimento na lista
     * @return (Consumable) o alimento encontrada, ou null caso contrário
     */
    public static Consumable getConsumableFood(int index) {
        if(index < 0 || index >= FOODS.length) return null;
        return FOODS[index];
    }

    /**
     * Retorna o comsumível do tipo poção, dado a posição do item na lista de poções
     * @param index posição (int) da poção na lista
     * @return (Consumable) a poção encontrada, ou null caso contrário
     */
    public static Consumable getConsumablePotion(int index) {
        if(index < 0 || index >= POTIONS.length) return null;
        return POTIONS[index];
    }

    /*
     * Retorna o comsumível do tipo veneno, dado a posição do item na lista de venenos
     * @param index posição (int) do veneno na lista
     * @return (Consumable) o veneno encontrada, ou null caso contrário
     */
    /*public static Consumable getConsumablePoison(int index) {
        if(index < 0 || index >= POISONS.length) return null;
        return POISONS[index];
    }*/

    /**
     * Retorna um consumível randomicamente de todos os consumíveis criados
     * @return (Consumable) o comsumível randomicamente escolhido
     */
    public static Consumable getRandom() {
        final Consumable[][] _listAllConsumables = { POTIONS, FOODS/* , POISON*/ };
        final int _indexSelectedType = Randomic.between(0, _listAllConsumables.length - 1);

        final int _indexSelectedConsumable =
            Randomic.between(0, _listAllConsumables[_indexSelectedType].length - 1);

        return _listAllConsumables[_indexSelectedType][_indexSelectedConsumable];
    }
}
