import java.util.List;
import java.util.Map;
import twitter4j.*;

public class Search {
	
	private Twitter twitter;
	private String searchTerm;
	private GeoLocation location;
	private double radius;
	private double numberOfPages;
	private boolean geoSearch;
	
	/**
	 * Constructor
	 * @param term, to search Tweets for
	 * @param latitude, of geolocation based search
	 * @param longitude, of geolocation based search
	 * @param radius, of geolocation based search
	 * @param pages, of Tweet results (potential maximum), 100 Tweets per page
	 */
	public Search(String term, double latitude, double longitude, double radius, int pages) {
		twitter = Connect.getTwitter(); // creates twitter object to access the API
		searchTerm = term;
		location = new GeoLocation(latitude, longitude); // creates geolocation object
		this.radius = radius;
		numberOfPages = pages;
		geoSearch = true; // geolocation based search
	} 
	
	/**
	 * Constructor
	 * @param term, to search Tweets for
	 * @param pages, of Tweet results (potential maximum), 100 Tweets per page
	 */
	public Search(String term, int pages) {
		this(term, 0.0, 0.0, 0.0, pages); // default values for a non-geolocation based search
		geoSearch = false; // non-geolocation based search
	}
	
	/**
	 * This method returns a list of relevant Tweets matching a specific query.
	 * @return totalTweets, list of Status objects containing the searched Tweets
	 */
	public List<Status> query() {
		
		Query q = new Query(searchTerm); // creates formatted query for searchTerm
		 // sets query's geolocation if desired
		if (geoSearch) q.setGeoCode(location, radius, Query.MILES);
		q.setLang("en"); // sets query's Tweet language to English
		q.setResultType(Query.RECENT); // sets query's results to recent Tweets
		q.setCount(100); // sets query's count to 100 Tweets, the maximum per page
		
//		int numberOfTweets = 0;
		
		// checks if this search will hit Twitter API's rate limit
		if (this.getSearchLimit(false) >= numberOfPages) {
			try {
				// calls Twitter's API to search based on the query
				QueryResult qResult = twitter.search(q);
//				List<Status> tweets = qResult.getTweets();
				// extracts Tweets from the query result
				List<Status> totalTweets = qResult.getTweets();
				
//				Removed this class's independent status printing
//				for (Status status : tweets) {
//					printStatus(status);
//					numberOfTweets++;
//				}
				numberOfPages--;
				
				// iterates search for additional pages
				while (qResult.hasNext() && numberOfPages > 0) {
					q = qResult.nextQuery(); // creates formatted query for the next page
					qResult = twitter.search(q);
//					tweets = qResult.getTweets();
					totalTweets.addAll(qResult.getTweets());
					
//					Removed this class's independent status printing
//					for (Status status : tweets) {
//						printStatus(status);
//						numberOfTweets++;
//					}
					numberOfPages--;
				}
				
				System.out.println("\"" + searchTerm + "\"" + " had " + totalTweets.size() + " results.");
				
				return totalTweets;
				
			} catch (TwitterException e) {
				System.out.println("Error getting query result!");
			}
			
		} else {
			System.out.println("Insufficient number of Twitter requests to perform search.");
			System.out.println("Please wait until search limit reset.");
			System.out.println();
			this.getSearchLimit(true);
		}
		
		return null;
	}
	
	/**
	 * This method returns the number of remaining Twitter search requests and can print relevant information.
	 * @param print, true to print remaining Twitter search requests and time until search limit reset
	 * @return the number of remaining Twitter search requests
	 */
	public int getSearchLimit(boolean print) {
		try {
			// calls Twitter's API to get the rate limits
			Map<String, RateLimitStatus> rateLimits = twitter.getRateLimitStatus();
			// extracts the search rate limit
			RateLimitStatus searchLimit = rateLimits.get("/search/tweets");
			
			if (print) {
				System.out.println(searchLimit.getRemaining() + "/" + searchLimit.getLimit() + " requests remaining.");
				System.out.println(searchLimit.getSecondsUntilReset() + " seconds until search limit reset.");
			}
			
			return searchLimit.getRemaining();
			
		} catch (TwitterException e) {
			System.out.println("Error getting rate limit!");
		}
		
		return 0;
	}
	
//	Removed this class's independent status printing
//	private void printStatus(Status status) {
//		System.out.println(status.getUser().getName() + " | " + status.getUser().getScreenName());
//		System.out.print(status.getCreatedAt());
//		if (status.getGeoLocation() != null) {
//			System.out.println(" | (" + status.getGeoLocation().getLatitude() + ", " + status.getGeoLocation().getLongitude() + ")");
//		} else {
//			System.out.println();
//		}
//		if (status.getPlace() != null) {
//			System.out.println("Place: " + status.getPlace().getFullName());
//		} else {
//			System.out.println();
//		}
//		if (status.getUser().getLocation() != "") {
//			System.out.println("User Location: " + status.getUser().getLocation());
//		} else {
//			System.out.println();
//		}
//		System.out.println(status.getText());
//		System.out.println(status.getRetweetCount() + " retweets | " + status.getFavoriteCount() + " favorites");
//		System.out.println("-----------------------------------------------------------");
//		System.out.println();
//	}

}