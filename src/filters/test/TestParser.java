package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {
    @Test
    public void testBasic() throws SyntaxError {
        Filter f = new Parser("trump").parse();
        assertTrue(f instanceof BasicFilter);
        assertTrue(((BasicFilter)f).getWord().equals("trump"));
    }
    
    @Test
    public void testHairy() throws SyntaxError {
        Filter x = new Parser("trump and (evil or blue) and red or green and not not purple").parse();
        assertTrue(x.toString().equals("(((trump and (evil or blue)) and red) or (green and not not purple))"));
    }

    
    @Test
    public void testOr() throws SyntaxError {
    	Filter f1 = new Parser("green or banana").parse();
    	assertTrue(f1.toString().equals("(green or banana)"));
    }
    
    @Test
    public void testAnd() throws SyntaxError {
    	Filter f2 = new Parser("green and banana").parse();
    	assertTrue(f2.toString().equals("(green and banana)"));
    }
    
    @Test
    public void testNot() throws SyntaxError {
    	Filter f3 = new Parser("not banana").parse();
    	assertTrue(f3.toString().equals("not banana"));
    }
    
    @Test
    public void testOrAnd() throws SyntaxError {
    	Filter f4 = new Parser("green and banana or apple").parse();
    	assertTrue(f4.toString().equals("((green and banana) or apple)"));
    }
    
    @Test
    public void testOrNot() throws SyntaxError {
    	Filter f5 = new Parser("green or not banana").parse();
    	assertTrue(f5.toString().equals("(green or not banana)"));
    }
    
    @Test
    public void testAndNot() throws SyntaxError {
    	Filter f6 = new Parser("green and not banana").parse();
    	assertTrue(f6.toString().equals("(green and not banana)"));
    }
    
    @Test
    public void testOrAndNot() throws SyntaxError {
    	Filter f7 = new Parser("green and not banana or not apple and grape").parse();
    	assertTrue(f7.toString().equals("((green and not banana) or (not apple and grape))"));
    }
}
