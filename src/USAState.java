import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

public class USAState {
	
	private String name;
	private int query1;
	private int query2;
	private List<TaggedStatus> tweets;
	
	public USAState(String name) {
		this.name = name;
		query1 = 0;
		query2 = 0;
		tweets = new ArrayList<TaggedStatus>();
	}
	
	public void incrementQuery(int queryIndex) {
		if(queryIndex == 1) {
			query1 += 1;
		} else {
			query2 += 1;
		} 
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getQueryCount(int queryIndex) {
		if(queryIndex == 1) {
			return query1;
		} else {
			return query2;
		} 
	}
	
	public void addTaggedStatusList(List<TaggedStatus> tweets) {
		 
		this.tweets.addAll(tweets);
	}
	
	public void addTweet(Status tweet, boolean boo1, boolean boo2) {
		 
		tweets.add(new TaggedStatus(tweet, boo1, boo2));
	}
	
	public List<TaggedStatus> getTweets() {
		return tweets;
	}
	
	

	
	
	
}
