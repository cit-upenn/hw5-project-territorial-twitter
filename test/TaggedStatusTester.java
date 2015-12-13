import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import twitter4j.Status;

public class TaggedStatusTester {
	
	@Test
	public void testGetTweet(){
		Search test = new Search("NYC", 40.741608, -73.990568, 25, 1);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, 2);
		Status tweet  = parser.getStatesList().getTweets("New York").get(0).getTweet();
		String tweetText = tweet.getText();
		int count = tweetText.length();
		assertNotSame(count, 0);
	}
	
	@Test
	public void testQuery1Boo(){
		Search test = new Search("NYC", 40.741608, -73.990568, 25, 1);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, 1);
		TaggedStatus tweet = parser.getStatesList().getTweets("New York").get(0);
		boolean tweetBoo = tweet.containsQuery1();
		assertSame(tweetBoo, true);
	}
	
	@Test
	public void testQuery2Boo(){
		Search test = new Search("NYC", 40.741608, -73.990568, 25, 1);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, 2);
		TaggedStatus tweet = parser.getStatesList().getTweets("New York").get(0);
		boolean tweetBoo = tweet.containsQuery2();
		assertSame(tweetBoo, true);
	}
}
