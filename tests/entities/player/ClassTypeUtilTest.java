package tests.entities.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import entities.player.ClassType;
import entities.player.ClassTypeUtil;
import exceptions.UnknownTypeException;

public class ClassTypeUtilTest {
    
    @Test
    public void testGetHP() throws UnknownTypeException {
        ClassTypeUtil.setClassType(ClassType.MAGE);
        assertEquals(700, ClassTypeUtil.getHP());
        
        ClassTypeUtil.setClassType(ClassType.WARRIOR);
        assertEquals(1000, ClassTypeUtil.getHP());
        
        ClassTypeUtil.setClassType(ClassType.ARCHER);
        assertEquals(850, ClassTypeUtil.getHP());
        
    }

    @Test
    public void testGetMP() throws UnknownTypeException {
        ClassTypeUtil.setClassType(ClassType.MAGE);
        assertEquals(100, ClassTypeUtil.getMP());
        
        ClassTypeUtil.setClassType(ClassType.WARRIOR);
        assertEquals(70, ClassTypeUtil.getMP());
        
        ClassTypeUtil.setClassType(ClassType.ARCHER);
        assertEquals(85, ClassTypeUtil.getMP());
        
    }


    @Test
    public void testGetDamage() throws UnknownTypeException {
        ClassTypeUtil.setClassType(ClassType.MAGE);
        assertEquals(180, ClassTypeUtil.getDamage());

        ClassTypeUtil.setClassType(ClassType.WARRIOR);
        assertEquals(160, ClassTypeUtil.getDamage());

        ClassTypeUtil.setClassType(ClassType.ARCHER);
        assertEquals(140, ClassTypeUtil.getDamage());
    }

    @Test
    public void testGetDefense() throws UnknownTypeException {
        ClassTypeUtil.setClassType(ClassType.MAGE);
        assertEquals(56, ClassTypeUtil.getDefense());

        ClassTypeUtil.setClassType(ClassType.WARRIOR);
        assertEquals(72, ClassTypeUtil.getDefense());

        ClassTypeUtil.setClassType(ClassType.ARCHER);
        assertEquals(64, ClassTypeUtil.getDefense());
    }

    @Test
    public void testGetCritChance() throws UnknownTypeException {
        ClassTypeUtil.setClassType(ClassType.MAGE);
        assertEquals(0.2, ClassTypeUtil.getCritChance());

        ClassTypeUtil.setClassType(ClassType.WARRIOR);
        assertEquals(0.2, ClassTypeUtil.getCritChance());

        ClassTypeUtil.setClassType(ClassType.ARCHER);
        assertEquals(0.2, ClassTypeUtil.getCritChance());
    }

    @Test
    public void testGetAccuracy() throws UnknownTypeException {
        ClassTypeUtil.setClassType(ClassType.MAGE);
        assertEquals(0.85, ClassTypeUtil.getAccuracy());

        ClassTypeUtil.setClassType(ClassType.WARRIOR);
        assertEquals(0.8, ClassTypeUtil.getAccuracy());

        ClassTypeUtil.setClassType(ClassType.ARCHER);
        assertEquals(0.9, ClassTypeUtil.getAccuracy());
    }

}
