import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

/**
 * This class represents a USAState. It tracks how many searches were found per state
 * by query1 and query 2. It also holds all tweets found in that state.
 * @author brendonlavernia
 *
 */
public class USAState {
	
	private String name;
	private int query1;
	private int query2;
	private List<TaggedStatus> tweets;
	
	/**
	 * The constructor
	 * @param name the name of the state
	 */
	public USAState(String name) {
		this.name = name;
		query1 = 0;
		query2 = 0;
		tweets = new ArrayList<TaggedStatus>();
	}
	
	/**
	 * Increases the count for search query 1 or 2 by 1
	 * @param queryIndex search query 1 or 2
	 */
	public void incrementQuery(int queryIndex) {
		if(queryIndex == 1) {
			query1 += 1;
		} else {
			query2 += 1;
		} 
		
	}
	
	/**
	 * Gets the name of the state
	 * @return the name of the state
	 */
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
	
	/**
	 * Adds a list of TaggedStatus objects to the state
	 * @param tweets list of TaggedStatus objects
	 */
	public void addTaggedStatusList(List<TaggedStatus> tweets) {
		 
		this.tweets.addAll(tweets);
	}
	
	/**
	 * Add a single Status object to the State, specifying which search term it matched
	 * @param tweet the Status object
	 * @param boo1 true if search term 1 was a match
	 * @param boo2 true if search term 2 was a match
	 */
	public void addTweet(Status tweet, boolean boo1, boolean boo2) {
		 
		tweets.add(new TaggedStatus(tweet, boo1, boo2));
	}
	
	/**
	 * Returns the list of TaggedStatus Tweets.
	 * @return the list of TaggedStatus Tweets.
	 */
	public List<TaggedStatus> getTweets() {
		return tweets;
	}
	
	

	
	
	
}
