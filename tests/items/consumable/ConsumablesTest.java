package tests.items.consumable;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import items.consumable.ConsumableType;
import items.consumable.Consumable;
import items.consumable.Consumables;

public class ConsumablesTest {
    @Test
    public void getConsumableTest() {
        Consumable cons = Consumables.getConsumableFood(-1);
        assertEquals(cons, null);
        cons = Consumables.getConsumableFood(0);
        assertTrue(cons.getType() == ConsumableType.FOOD);
        cons = Consumables.getConsumablePotion(0);
        assertTrue(cons.getType() == ConsumableType.POTION);
    }

    @Test
    public void getRandomConsumableTest() {
        for(int i = 0; i < 100; i++){
            Consumable cons = Consumables.getRandom();
            assertTrue(cons.getType() == ConsumableType.FOOD || cons.getType() == ConsumableType.POTION);
        }
        
    }
}
