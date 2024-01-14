package tests.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.Entity;

public class EntityTest {

    Entity entity;
    
    @BeforeEach
    void init(){
        entity = new Entity("TestEntity");
    }

    @Test
    void testEntityInitialization() {
        assertEquals("TestEntity", entity.getName());
        assertEquals(0, entity.getMaxHP());
        assertEquals(0, entity.getCurrHP());
        assertFalse(entity.getIsDead());
    }
    
    

}
