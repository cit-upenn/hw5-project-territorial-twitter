import twitter4j.Status;

/**
 * This class stores a single Tweet object and tracks whether it 
 * matched for query1 or query2.
 * @author brendonlavernia
 *
 */
public class TaggedStatus {
	private Status tweet;
	private boolean query1;
	private boolean query2;

	/**
	 * The constructor for the class
	 * @param tweet The Status object to add
	 * @param boo1 true if it matched for query1, false otherwise
	 * @param boo2 true if it matched for query2, false otherwise
	 */
	public TaggedStatus(Status tweet, boolean boo1, boolean boo2) {
		this.tweet = tweet;
		query1 = boo1;
		query2 = boo2;
	}

	/**
	 * Tells user if the tweet matched for query1
	 * @return true if the tweet matched for query1, false otherwise
	 */
	public boolean containsQuery1() {
		return query1;
	}

	/**
	 * Tells user if the tweet matched for query2
	 * @return true if the tweet matched for query2, false otherwise
	 */
	public boolean containsQuery2() {
		return query2;
	}

	/**
	 * Gets the tweet
	 * @return the Status object
	 */
	public Status getTweet() {
		return tweet;
	}

}
