package tests.entities.boss;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import entities.boss.StateType;

public class StateTypeTest {
    
    @Test
    public void testDistinctStates() {
        StateType[] states = StateType.values();
        Set<StateType> stateSet = new HashSet<>(Arrays.asList(states));

        assertEquals(states.length, stateSet.size(), "Todos os estados devem ser distintos");
    }

    @Test
    public void testValueOf() {
        assertEquals(StateType.BASE, StateType.valueOf("BASE"), "O valor de 'BASE' deve ser StateType.BASE");
        assertEquals(StateType.BERSERK, StateType.valueOf("BERSERK"), "O valor de 'BERSERK' deve ser StateType.BERSERK");
        assertEquals(StateType.WEAK, StateType.valueOf("WEAK"), "O valor de 'WEAK' deve ser StateType.WEAK");
    }

}
