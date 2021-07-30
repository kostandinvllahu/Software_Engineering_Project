package filters.test;

import filters.Scanner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestScanner {
    @Test
    public void testBasic() {
        Scanner scanner = new Scanner("trump");
        assertTrue(scanner.peek().equals("trump"));
        assertTrue(scanner.advance() == null);
    }

    @Test
    public void testAnd() {
        Scanner refactor = new Scanner("trump and evil");
        assertTrue(refactor.peek().equals("trump"));
        assertTrue(refactor.advance().equals("and"));
        assertTrue(refactor.peek().equals("and"));
        assertTrue(refactor.advance().equals("evil"));
        assertTrue(refactor.peek().equals("evil"));
        assertTrue(refactor.advance() == null);
    }

    @Test
    public void testAll() {
        String expected[] = { "trump", "and", "(", "evil",
                "or", "not", "(", "good", ")", ")" };
        runTest("trump and (evil or not (good))", expected);
    }

    @Test
    public void testOr() {
        String expected[] = { "trump", "or", "evil" };
        runTest("trump or evil", expected);
    }

    private void runTest(String input, String[] expected) {
        Scanner scanner = new Scanner(input);
        boolean first = true;
        for (String token : expected) {
            if (first) {
                first = false;
            } else {
                assertTrue(scanner.advance().equals(token));
            }
            assertTrue(scanner.peek().equals(token));
        }
        assertTrue(scanner.advance() == null);
    }
}
