package tests.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;

import utils.Number;

public class NumberTest {

    @Test
    public void testDPercentage(){
        final double tol = 0.00000001;
        for(int i = 0; i <= 100; i++){
            assertTrue(((double)i/100 - Number.dPercentage(i)) < tol);
        }
    };

}
