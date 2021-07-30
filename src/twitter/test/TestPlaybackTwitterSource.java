package twitter.test;

import org.junit.jupiter.api.Test;
import twitter.PlaybackTwitterSource;
import twitter4j.Status;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the basic functionality of the TwitterSource
 * SHTIM KODI RRJESHTI 25
 */
public class TestPlaybackTwitterSource {

    @Test
    public void testSetup() {
        PlaybackTwitterSource source = new PlaybackTwitterSource(1.0);
        TestObserver testObserver = new TestObserver();
        // TODO: Once your TwitterSource class implements Observable, you must add the TestObserver as an observer to it here
        source.addObserver(testObserver);
        source.setFilterTerms(set("food"));
        pause(3 * 1000);
        assertTrue(testObserver.getNTweets() > 0, "Expected getNTweets() to be > 0, was " + testObserver.getNTweets());
        assertTrue(testObserver.getNTweets() <= 10, "Expected getNTweets() to be <= 10, was " + testObserver.getNTweets());
        int firstBunch = testObserver.getNTweets();
        System.out.println("Now adding 'the'");
        source.setFilterTerms(set("food", "the"));
        pause(3 * 1000);
        assertTrue(testObserver.getNTweets() > 0, "Expected getNTweets() to be > 0, was " + testObserver.getNTweets());
        assertTrue(testObserver.getNTweets() > firstBunch, "Expected getNTweets() to be < firstBunch (" + firstBunch + "), was " + testObserver.getNTweets());
        assertTrue(testObserver.getNTweets() <= 10, "Expected getNTweets() to be <= 10, was " + testObserver.getNTweets());
    }

    private void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException error) {
            error.printStackTrace();
        }
    }

    private <E> Set<E> set(E ... p) {
        Set<E> ans = new HashSet<>();
        for (E a : p) {
            ans.add(a);
        }
        return ans;
    }
    private class TestObserver implements Observer {
        private int nTweets = 0;

        @Override
        public void update(Observable observable, Object arg) {
            Status status = (Status) arg;
            nTweets ++;
        }

        public int getNTweets() {
            return nTweets;
        }
    }
}
