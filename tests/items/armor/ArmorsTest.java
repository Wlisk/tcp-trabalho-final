package tests.items.armor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

import entities.player.ClassType;
import exceptions.UnknownTypeException;
import items.armor.Armor;
import items.armor.Armors;
import items.armor.ArmorType;

public class ArmorsTest {
    @Test
    public void getWeaponTest() {
        Armor armor = Armors.getArmorArcher(-1);
        assertEquals(armor, null);
        armor = Armors.getArmorArcher(0);
        assertNotEquals(armor, null);
    }

    @Test
    public void getRandomByClassTest() throws UnknownTypeException {
        for (int i = 0; i < 100; i++){
            Armor armorMage = Armors.getRandomByClass(ClassType.MAGE),
                  armorWarrior = Armors.getRandomByClass(ClassType.WARRIOR),
                  armorArcher = Armors.getRandomByClass(ClassType.ARCHER);
            assertEquals(armorMage.getType(), ArmorType.ROBE);
            assertEquals(armorWarrior.getType(), ArmorType.PLATE);
            assertEquals(armorArcher.getType(), ArmorType.LEATHER);
        }
        
        
    }
}
