import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import twitter4j.Status;

public class TweetParserTest {

	@Test
	public void testNewYorkSaidSomethingAboutNYC() {
		Search test = new Search("NYC", 40.741608, -73.990568, 25, 10);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, 1);
		assertNotSame(parser.getStatesList().getQuery1Count("New York"), 0); 
	}
	
	@Test
	public void testCaliforniaSaidSomethingAboutDiets() {
		Search test = new Search("diet", 34.057049, -118.248457, 50, 10);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, 1);
		assertNotSame(parser.getStatesList().getQuery1Count("California"), 0); 
	}
	
	@Test
	public void testAbleToParseGoldenGateLocation() {
		Search test = new Search("golden gate bridge", 37.819304, -122.479389, 3, 10);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, 1);
		int count = 0;
		
		//Count = How many tweets have a Full Name place location included
		for(Status tweet : tweets) {
			if(tweet.getPlace() != null && !tweet.getPlace().getFullName().isEmpty()) {
				count++;
			}
		}
		
		int diff = parser.getStatesList().getQuery1Count("California") - count;
		
		String result;
		
		if(diff < 0) {
			result = "Failure";
		} else {
			result = "Success";
		}
		
		assertNotSame(result, "Failure"); 
	}
	
}
