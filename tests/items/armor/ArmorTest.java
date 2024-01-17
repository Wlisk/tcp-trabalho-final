package tests.items.armor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import items.ItemType;
import items.armor.Armor;
import scene.TextureId;
import items.armor.ArmorType;

public class ArmorTest {
    @Test
    public void initializeTest() throws NumberOverflowException, UnknownTypeException{
        Armor armor = new Armor(
            ArmorType.LEATHER, 
            TextureId.ARMOR_LIGHT_1,
            "Test", 
            30, 
            "Test2.");


        assertEquals(armor.getBoostHP(), 0);
        assertEquals(armor.getBoostDefense(), 30);
        assertEquals(armor.getArmorType(), ArmorType.LEATHER);
        assertEquals(armor.getTextureId(), TextureId.ARMOR_LIGHT_1);
        assertEquals(armor.getName(), "Test");
        assertEquals(armor.getDescription(), "Test2.");
        assertEquals(armor.getItemType(), ItemType.ARMOR);
    }
}
