package tests.items.consumable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import items.ItemType;
import items.consumable.Consumable;
import items.consumable.ConsumableType;
import scene.TextureId;

public class ConsumableTest {
    @Test
    public void initializeTest() throws NumberOverflowException, UnknownTypeException{
        Consumable consumable = new Consumable(
            ConsumableType.POTION, 
            TextureId.CONSUMABLE_BLUE_SMALL,
            "Test", 
            30, 
            30,
            "Test2.");


        assertEquals(consumable.getBoostHP(), 30);
        assertEquals(consumable.getBoostMP(), 30);
        assertEquals(consumable.getType(), ConsumableType.POTION);
        assertEquals(consumable.getTextureId(), TextureId.CONSUMABLE_BLUE_SMALL);
        assertEquals(consumable.getName(), "Test");
        assertEquals(consumable.getDescription(), "Test2.");
        assertEquals(consumable.getItemType(), ItemType.CONSUMABLE);
    }
}
