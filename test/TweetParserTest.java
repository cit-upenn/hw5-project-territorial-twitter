import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import twitter4j.Status;

public class TweetParserTest {

	@Test
	public void testHillaryInNewYork() {
		Search test = new Search("the", 42.824415, -107.607604, 150.0, 10);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, "the", "Trump");
//		System.out.println("Proof: " + parser.getStatesList().getQuery1Count("New York"));
		StateTweetTracker stateTracker = parser.getStatesList();
		String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", 
				"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", 
				"Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", 
				"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", 
				"New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", 
				"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
				"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
				"West Virginia", "Wisconsin", "Wyoming" };
		for(String state : states) {
			System.out.println(state + ": " + stateTracker.getQuery1Count(state));
		}
		assertNotSame(parser.getStatesList().getQuery1Count("Wyoming"), 0); 
	}
	
//	@Test
//	public void testNFL() {
//		Search test = new Search("NFL", 40.672567, -73.965794, 3000.0, 1);
//		List<Status> tweets = test.query();
////		System.out.println("Proof: " + parser.getStatesList().getQuery1Count("New York"));
//		
//		for(Status statuses : tweets) {
////			System.out.println("Tweet text: " + statuses.getText());
////			System.out.println("Quoted status: " + statuses.getQuotedStatus());
////			System.out.println("User name: " + statuses.getUser().getName());
//			System.out.println(statuses.toString());
//		}
//		
//		assertNotSame(tweets.size(), 0); 
//	}
}
