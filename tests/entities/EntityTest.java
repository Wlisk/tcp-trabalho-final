package tests.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.Entity;

class EntityTest {

    private Entity entity;

    private static class entity extends Entity {
        public entity(String name) {
            super(name);
        }
    }

    @BeforeEach
    void init() {
        entity = new entity("TestEntity");
    }


    @Test
    void testDefend() {
        int damage = 50;
        int initialHP = entity.getCurrHP();
        int damageTaken = entity.defend(damage);

        assertTrue(damageTaken >= 0);
        assertEquals(initialHP - damageTaken, entity.getCurrHP());
    }

    @Test
    void testAttack() {
        Entity enemy = new entity("TestEnemy");
        int damageDone = entity.attack(enemy);

        assertTrue(damageDone >= 0);
        assertFalse(enemy.getIsDead());
    }


    @Test
    void testUpdateEndTurn() {
        int initialHP = entity.getCurrHP();
        int initialMP = entity.getCurrMP();

        entity.updateEndTurn();

        assertTrue(entity.getCurrHP() >= initialHP);
        assertTrue(entity.getCurrMP() >= initialMP);
    }

}

