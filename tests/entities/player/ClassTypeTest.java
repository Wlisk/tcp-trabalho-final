package tests.entities.player;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import entities.player.ClassType;

public class ClassTypeTest {
    
    @Test
    public void testGetTypeName() {
        assertEquals("Mage", ClassType.MAGE.getTypeName());
        assertEquals("Warrior", ClassType.WARRIOR.getTypeName());
        assertEquals("Archer", ClassType.ARCHER.getTypeName());
    }

    @Test
    public void testGetIndex() {
        assertEquals(0, ClassType.MAGE.getIndex());
        assertEquals(1, ClassType.WARRIOR.getIndex());
        assertEquals(2, ClassType.ARCHER.getIndex());
    }

    @Test
    public void testGetImageSrc() {
        assertEquals("resources/sprites/classes/mage.png", ClassType.MAGE.getImageSrc());
        assertEquals("resources/sprites/classes/warrior.png", ClassType.WARRIOR.getImageSrc());
        assertEquals("resources/sprites/classes/ranger.png", ClassType.ARCHER.getImageSrc());
    }

    @Test
    public void testGetSpriteSheetSrc() {
        assertEquals("resources/sprites/sheet/wizard-sheet.png", ClassType.MAGE.getSpriteSheetSrc());
        assertEquals("resources/sprites/sheet/warrior-sheet.png", ClassType.WARRIOR.getSpriteSheetSrc());
        assertEquals("resources/sprites/sheet/ranger-sheet.png", ClassType.ARCHER.getSpriteSheetSrc());
    }

    @Test
    public void testSize() {
        assertEquals(3, ClassType.size());
    }

    @Test
    public void testGetStrings() {
        String[] classStrings = ClassType.getStrings();
        assertNotNull(classStrings);
        assertEquals(3, classStrings.length);
        assertArrayEquals(new String[]{"MAGE", "WARRIOR", "ARCHER"}, classStrings);
    }


}
