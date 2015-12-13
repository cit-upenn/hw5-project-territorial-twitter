import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import twitter4j.Status;

public class StateTweetTrackerTester {

	@Test
	public void testQuery2Works() {
		Search test = new Search("NYC", 40.741608, -73.990568, 25, 1);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, 2);
		int count  = parser.getStatesList().getQuery2Count("New York");
		assertNotSame(count, 0);
		
	}
	
	@Test
	public void testReturnsTweets() {
		Search test = new Search("NYC", 40.741608, -73.990568, 25, 1);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, 2);
		int count  = parser.getStatesList().getTweets("New York").size();
		assertNotSame(count, 0);
		
	}
	
	@Test
	public void testAddingParsedTweets() {
		Search test = new Search("NYC", 40.741608, -73.990568, 25, 1);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, 1);
		StateTweetTracker tracker = parser.getStatesList();
		test = new Search("NYC", 40.741608, -73.990568, 10, 1);
		tweets = test.query();
		parser = new TweetParser(tweets, 1);
		tracker.addParsedTweets(parser.getStatesList(), 2);
		int count  = tracker.getQuery2Count("New York");
		assertNotSame(count, 0);
		
	}
	
	
}
