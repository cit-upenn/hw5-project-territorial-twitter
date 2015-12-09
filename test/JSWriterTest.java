import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import twitter4j.Status;


public class JSWriterTest {
	
	
//	private JSWriter tweets;

	@Test
	public void JSWriterNotNull() {
		List<Status> tweets = new LinkedList<Status>();
		TweetParser parser = new TweetParser(tweets, "Hot Dog", "Burger");
		StateTweetTracker parsedTweets = parser.getStatesList();
		JSWriter write = new JSWriter(parsedTweets);
		assertNotNull("JSWriter cannot be null", write);
		
	}
	
	@Test
	public void WriteJSNotNull() {
		List<Status> tweets = new LinkedList<Status>();
		TweetParser parser = new TweetParser(tweets, "Hot Dog", "Burger");
		StateTweetTracker parsedTweets = parser.getStatesList();
		JSWriter write = new JSWriter(parsedTweets);
		String line = "This is a test String";
		write.writeJS(line);
		assertNotNull("WriteJS cannot be null", write.writeJS(line));
		
	}
	@Test
	public void testWriteWashingtonCount() {

		
	}
}
