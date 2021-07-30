package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;
import twitter4j.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestFilters {
    @Test
    public void testBasic() {
        Filter filter = new BasicFilter("fred");
        assertTrue(filter.matches(makeStatus("Fred Flintstone")));
        assertTrue(filter.matches(makeStatus("fred Flintstone")));
        assertFalse(filter.matches(makeStatus("Red Skelton")));
        assertFalse(filter.matches(makeStatus("red Skelton")));
    }

    @Test
    public void testNot() {
        Filter filter3 = new NotFilter(new BasicFilter("fred"));
        assertFalse(filter3.matches(makeStatus("Fred Flintstone")));
        assertFalse(filter3.matches(makeStatus("fred Flintstone")));
        assertTrue(filter3.matches(makeStatus("Red Skelton")));
        assertTrue(filter3.matches(makeStatus("red Skelton")));
    }

    @Test
    public void testAnd() {
    	Filter filter1 = new AndFilter(new BasicFilter("fred"), new BasicFilter("Flintstone"));
    	assertFalse(filter1.matches(makeStatus("ted and flintstone")));
    	assertFalse(filter1.matches(makeStatus("Ted and Flintstone")));
    	assertTrue(filter1.matches(makeStatus("fred and flintstone")));
    	assertTrue(filter1.matches(makeStatus("Fred and Flintstone")));
    }
    
    @Test
    public void testOr() {
    	Filter filter2 = new OrFilter(new BasicFilter("fred"), new BasicFilter("Flintstone"));
    	assertFalse(filter2.matches(makeStatus("ted or stone")));
    	assertFalse(filter2.matches(makeStatus("Ted or Stone")));
    	assertTrue(filter2.matches(makeStatus("fred or stone")));
    	assertTrue(filter2.matches(makeStatus("Fred or Stone")));
    	assertTrue(filter2.matches(makeStatus("ted or flintstone")));
    	assertTrue(filter2.matches(makeStatus("Ted or Flintstone")));
    	assertTrue(filter2.matches(makeStatus("fred or flintstone")));
    	assertTrue(filter2.matches(makeStatus("Fred or Flintstone")));
    }
    
    private Status makeStatus(String text) {
        return new Status() {
            @Override
            public Date getCreatedAt() {
                return null;
            }

            @Override
            public long getId() {
                return 0;
            }

            @Override
            public String getText() {
                return text;
            }

            @Override
            public String getSource() {
                return null;
            }

            @Override
            public boolean isTruncated() {
                return false;
            }

            @Override
            public long getInReplyToStatusId() {
                return 0;
            }

            @Override
            public long getInReplyToUserId() {
                return 0;
            }

            @Override
            public String getInReplyToScreenName() {
                return null;
            }

            @Override
            public GeoLocation getGeoLocation() {
                return null;
            }

            @Override
            public Place getPlace() {
                return null;
            }

            @Override
            public boolean isFavorited() {
                return false;
            }

            @Override
            public boolean isRetweeted() {
                return false;
            }

            @Override
            public int getFavoriteCount() {
                return 0;
            }

            @Override
            public User getUser() {
                return null;
            }

            @Override
            public boolean isRetweet() {
                return false;
            }

            @Override
            public Status getRetweetedStatus() {
                return null;
            }

            @Override
            public long[] getContributors() {
                return new long[0];
            }

            @Override
            public int getRetweetCount() {
                return 0;
            }

            @Override
            public boolean isRetweetedByMe() {
                return false;
            }

            @Override
            public long getCurrentUserRetweetId() {
                return 0;
            }

            @Override
            public boolean isPossiblySensitive() {
                return false;
            }

            @Override
            public String getLang() {
                return null;
            }

            @Override
            public Scopes getScopes() {
                return null;
            }

            @Override
            public String[] getWithheldInCountries() {
                return new String[0];
            }

            @Override
            public long getQuotedStatusId() {
                return 0;
            }

            @Override
            public Status getQuotedStatus() {
                return null;
            }

            @Override
            public int compareTo(Status o) {
                return 0;
            }

            @Override
            public UserMentionEntity[] getUserMentionEntities() {
                return new UserMentionEntity[0];
            }

            @Override
            public URLEntity[] getURLEntities() {
                return new URLEntity[0];
            }

            @Override
            public HashtagEntity[] getHashtagEntities() {
                return new HashtagEntity[0];
            }

            @Override
            public MediaEntity[] getMediaEntities() {
                return new MediaEntity[0];
            }

            @Override
            public ExtendedMediaEntity[] getExtendedMediaEntities() {
                return new ExtendedMediaEntity[0];
            }

            @Override
            public SymbolEntity[] getSymbolEntities() {
                return new SymbolEntity[0];
            }

            @Override
            public RateLimitStatus getRateLimitStatus() {
                return null;
            }

            @Override
            public int getAccessLevel() {
                return 0;
            }
        };
    }
}
