
import java.util.List;

import twitter4j.*;

public class Search {
	
	private Twitter twitter;
	private String searchTerm;
	private GeoLocation location;
	private double radius;
	private double numberOfPages;
	private boolean geoSearch;
	
	public Search(String term, double latitude, double longitude, double radius, int pages) {
		twitter = Connect.getTwitter();
		searchTerm = term;
		location = new GeoLocation(latitude, longitude);
		this.radius = radius;
		numberOfPages = pages;
		geoSearch = true; 
	} 
	
	public Search(String term, int pages) {
		this(term, 0.0, 0.0, 0.0, pages);
		geoSearch = false;
	}
	
	public List<Status> query() {
		
		Query q = new Query(searchTerm);
		if (geoSearch) {
			q.setGeoCode(location, radius, Query.MILES);
		}
		q.setLang("en");
		q.setResultType(Query.RECENT);
		q.setCount(100);
		
//		int numberOfTweets = 0;
		
		try {
			QueryResult qResult = twitter.search(q);
			List<Status> tweets = qResult.getTweets();
			List<Status> totalTweets = qResult.getTweets();
			
			//Removed printing statuses for testing.
//			for (Status status : tweets) {
////				printStatus(status);
//				numberOfTweets++;
//			}
			numberOfPages--;
			
			while (qResult.hasNext() && numberOfPages > 0) {
				q = qResult.nextQuery();
				qResult = twitter.search(q);
				tweets = qResult.getTweets();
				totalTweets.addAll(qResult.getTweets());
				
				//Removed printing statuses for testing.
//				for (Status status : tweets) {
//					printStatus(status);
//					numberOfTweets++;
//				}
				numberOfPages--;
			}
			
			System.out.println("\"" + searchTerm + "\"" + " had " + totalTweets.size() + " results.");
			
			return totalTweets;
			
		} catch (TwitterException e) {
			e.printStackTrace();
			System.out.println("Error getting query result!");
		}
		
		return null;
	}
	
	private void printStatus(Status status) {
		System.out.println(status.getUser().getName() + " | " + status.getUser().getScreenName());
		System.out.print(status.getCreatedAt());
		if (status.getGeoLocation() != null) {
			System.out.println(" | (" + status.getGeoLocation().getLatitude() + ", " + status.getGeoLocation().getLongitude() + ")");
		} else {
			System.out.println();
		}
		if (status.getPlace() != null) {
			System.out.println("Place: " + status.getPlace().getFullName());
		} else {
			System.out.println();
		}
		if (status.getUser().getLocation() != "") {
			System.out.println("User Location: " + status.getUser().getLocation());
		} else {
			System.out.println();
		}
		System.out.println(status.getText());
		System.out.println(status.getRetweetCount() + " retweets | " + status.getFavoriteCount() + " favorites");
		System.out.println("-----------------------------------------------------------");
		System.out.println();
	}

}