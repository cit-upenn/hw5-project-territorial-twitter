import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import twitter4j.Status;

public class TweetParserTest {

	@Test
	public void testHillaryInNewYork() {
		Search test = new Search("Hillary", 40.672567, -73.965794, 5.0, 1);
		List<Status> tweets = test.query();
		TweetParser parser = new TweetParser(tweets, "Hillary", "Trump");
		assertNotSame(parser.getStatesList().getQuery1Count("New York"), 0); 
	}
}
