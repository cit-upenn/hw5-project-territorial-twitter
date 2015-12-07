import twitter4j.Status;
import twitter4j.User;

public class TaggedStatus {
	private Status tweet;
	private boolean query1;
	private boolean query2;

	
	public TaggedStatus(Status tweet, boolean boo1, boolean boo2) {
		this.tweet = tweet;
		query1 = boo1;
		query2 = boo2;
	}
	
	public boolean containsQuery1(){
		return query1;
	}
	
	public boolean containsQuery2(){
		return query2;
	}
	
	public Status getTweet() {
		return tweet;
	}
	
	public String getFormattedTweet() {
		User person = tweet.getUser();
		String personString = person.getName();
		String text = tweet.getText();
		return (personString + ": " + text);
	}
}
