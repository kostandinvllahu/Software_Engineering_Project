package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {
    @Test
    public void testBasic() throws StringParseException {
        Filter filter = new Parser("trump").parse();
        assertTrue(filter instanceof BasicFilter);
        assertTrue(((BasicFilter)filter).getWord().equals("trump"));
    }
    
    @Test
    public void testHairy() throws StringParseException {
        Filter filter1 = new Parser("trump and (evil or blue) and red or green and not not purple").parse();
        assertTrue(filter1.toString().equals("(((trump and (evil or blue)) and red) or (green and not not purple))"));
    }

    
    @Test
    public void testOr() throws StringParseException {
    	Filter filter2 = new Parser("green or banana").parse();
    	assertTrue(filter2.toString().equals("(green or banana)"));
    }
    
    @Test
    public void testAnd() throws StringParseException {
    	Filter filter3 = new Parser("green and banana").parse();
    	assertTrue(filter3.toString().equals("(green and banana)"));
    }
    
    @Test
    public void testNot() throws StringParseException {
    	Filter filter4 = new Parser("not banana").parse();
    	assertTrue(filter4.toString().equals("not banana"));
    }
    
    @Test
    public void testOrAnd() throws StringParseException {
    	Filter filter5 = new Parser("green and banana or apple").parse();
    	assertTrue(filter5.toString().equals("((green and banana) or apple)"));
    }
    
    @Test
    public void testOrNot() throws StringParseException {
    	Filter filter6 = new Parser("green or not banana").parse();
    	assertTrue(filter6.toString().equals("(green or not banana)"));
    }
    
    @Test
    public void testAndNot() throws StringParseException {
    	Filter filter7 = new Parser("green and not banana").parse();
    	assertTrue(filter7.toString().equals("(green and not banana)"));
    }
    
    @Test
    public void testOrAndNot() throws StringParseException {
    	Filter filter8 = new Parser("green and not banana or not apple and grape").parse();
    	assertTrue(filter8.toString().equals("((green and not banana) or (not apple and grape))"));
    }
}
