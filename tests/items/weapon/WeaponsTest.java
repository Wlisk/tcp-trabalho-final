package tests.items.weapon;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import entities.player.ClassType;
import items.weapon.*;
import items.weapon.WeaponType;
import exceptions.UnknownTypeException;

public class WeaponsTest {
    @Test
    public void getWeaponTest() {
        Weapon weapon = Weapons.getWeaponArcher(-1);
        assertEquals(weapon, null);
        weapon = Weapons.getWeaponArcher(0);
        assertNotEquals(weapon, null);
    }

    @Test
    public void getRandomByClassTest() throws UnknownTypeException {
        for (int i = 0; i < 100; i++){
            Weapon weaponMage = Weapons.getRandomByClass(ClassType.MAGE),
               weaponWarrior = Weapons.getRandomByClass(ClassType.WARRIOR),
               weaponArcher = Weapons.getRandomByClass(ClassType.ARCHER);
               assertEquals(weaponMage.getWeaponType(), WeaponType.STAFF);
               assertEquals(weaponWarrior.getWeaponType(), WeaponType.SWORD);
               assertEquals(weaponArcher.getWeaponType(), WeaponType.BOW);
        }
        
        
    }
}
