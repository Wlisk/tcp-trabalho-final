package tests.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import utils.Randomic;

import org.junit.Test;

public class RandomicTest {

    @Test
    public void testBetween(){
        for(int i = 0; i <= 100; i++){
            for (int j = i; j <= 100; j++){
                int randInt = Randomic.between(i, j);
                double randDouble = Randomic.between((double)i, (double)j);
                assertTrue(i <= randInt && randInt <= j);
                assertTrue(i <= randDouble && randDouble <= j);
            }
            
        }
    };
}
