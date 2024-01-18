package tests.items.weapon;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import items.weapon.Weapon;
import items.weapon.WeaponType;
import scene.TextureId;

public class WeaponTest {
    @Test
    public void initializeTest() throws NumberOverflowException, UnknownTypeException{
        Weapon weapon = new Weapon(
            WeaponType.STAFF, 
            TextureId.WEAPON_STAFF_1,
            "Rotting Staff", 
            30, 
            0, 
            0.05, 
            0.05, 
            0.1,
            "Made from a branch from a magical tree.");

        double delta = 0.000001;

        assertEquals(weapon.getBoostHP(), 0);
        assertEquals(weapon.getBoostDamage(), 30);
        assertEquals(weapon.getBoostDefense(), 0);
        assertEquals(weapon.getWeaponType(), WeaponType.STAFF);
        assertEquals(weapon.getTextureId(), TextureId.WEAPON_STAFF_1);
        assertEquals(weapon.getBoostAccuracy(), 0.05, delta);
    }
}

