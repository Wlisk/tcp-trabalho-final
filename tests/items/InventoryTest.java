package tests.items;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import entities.player.ClassType;
import entities.player.Player;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import items.Inventory;
import items.Item;
import items.consumable.Consumable;
import items.consumable.ConsumableType;
import items.weapon.Weapon;
import items.weapon.WeaponType;
import scene.TextureId;

public class InventoryTest {
     

    @Test
    public void testAddItem() throws NumberOverflowException {
        
        Inventory inventory = new Inventory();

        Item item1 = new Weapon(
                    WeaponType.STAFF, 
                    TextureId.WEAPON_STAFF_1,
                    "Rotting Staff", 
                    50, 
                    0, 
                    0.05, 
                    0.05, 
                    0.1,
                    "Made from a branch from a magical tree."
                );

        assertNotNull(inventory.add(item1)); 
        inventory.add(item1); 
        inventory.add(item1);
        inventory.add(item1);


        Item itemExtra = new Consumable(
                        ConsumableType.FOOD, TextureId.CONSUMABLE_CHEESE, "Cheese", 
                        200, 100,
                        "Just a regular ol\' piece of cheese."
                    );
        assertNull(inventory.add(itemExtra));
    }

    @Test
    public void testUseConsumable() {
        
        Inventory inventory = new Inventory();
        Consumable usedPotion = inventory.use(0);

        assertNotNull(usedPotion);
        assertEquals("Vial of Healing", usedPotion.getName());
        
        assertFalse(inventory.isFull());
        assertEquals(1, inventory.size());
    }


    @Test
    public void testRemoveItem() {
        
        Inventory inventory = new Inventory();
        Item RemovedItem = inventory.remove(1);
    
        assertNotNull(RemovedItem);
        assertEquals("Vial of Mana", RemovedItem.getName());
        assertEquals(1, inventory.size());
    }


    @Test
    public void testApplyItemBuff() throws NumberOverflowException, UnknownTypeException {
        
        Player player = new Player("TestPlayer", ClassType.WARRIOR);

        Weapon weapon = new Weapon(
                            WeaponType.STAFF, 
                            TextureId.WEAPON_STAFF_1,
                            "Rotting Staff", 
                            50, 
                            0, 
                            0.05, 
                            0.05, 
                            0.1,
                            "Made from a branch from a magical tree."
                        );

        
        player.addItemInventory(weapon);
        
        assertEquals(200, player.getCurrDamage());

        player.equipInventory(2);
        
        assertEquals(210, player.getCurrDamage()); // o dano aumentou em 10, a arma antiga tinha 40 de boost de dano e a nova tem um boost de 50, assim aumentando o dano em 10
        

    }


    @Test
    public void testApplyItemDebuff() throws NumberOverflowException, UnknownTypeException {
        
        Player player = new Player("TestPlayer", ClassType.WARRIOR);
        
        Weapon weapon = new Weapon(
            WeaponType.STAFF, 
            TextureId.WEAPON_STAFF_1,
            "Rotting Staff", 
            30, 
            0, 
            0.05, 
            0.05, 
            0.1,
            "Made from a branch from a magical tree."
        );

        
        player.addItemInventory(weapon);
        
        assertEquals(200, player.getCurrDamage()); //com uma arma de boost de dano 50

        player.equipInventory(2);
        
        assertEquals(190, player.getCurrDamage()); // o dano diminui em 10 com uma arma de boos 40
        

    }

}
