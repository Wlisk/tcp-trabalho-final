package tests.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.Test;
import utils.Text;

public class TextTest {

    @Test
    public void testStringIsEmpty(){
        assertTrue(Text.stringIsEmpty(""));
        assertTrue(Text.stringIsEmpty(null));
        assertFalse(Text.stringIsEmpty("asf"));
    };
}
