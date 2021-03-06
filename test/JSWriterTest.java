import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import twitter4j.Status;

/**
 * This class is the JUnit test for JSWriter
 * @author weiyin
 *
 */
public class JSWriterTest {
	
	
	@Test
	public void JSWriterNotNull() {
		List<Status> tweets = new LinkedList<Status>();
		TweetParser parser = new TweetParser(tweets, 1);
		StateTweetTracker parsedTweets = parser.getStatesList();
		JSWriter write = new JSWriter(parsedTweets, "test1", "test2");
		assertNotNull("JSWriter cannot be null", write);
		
	}
	
	@Test
	public void writeJSNotNull() {
		List<Status> tweets = new LinkedList<Status>();
		TweetParser parser = new TweetParser(tweets, 2);
		StateTweetTracker parsedTweets = parser.getStatesList();
		JSWriter write = new JSWriter(parsedTweets, "test1", "test2");
		String line = "This is a test String";
		write.writeJS(line);
		assertNotNull("writeJS cannot be null", write.writeJS(line));
		
	}
	
	@Test
	public void outJSWorks() {
		List<Status> tweets = new LinkedList<Status>();
		TweetParser parser = new TweetParser(tweets, 3);
		StateTweetTracker parsedTweets = parser.getStatesList();
		JSWriter write = new JSWriter(parsedTweets, "test1", "test2");
		write.outJS();	
	}

}
