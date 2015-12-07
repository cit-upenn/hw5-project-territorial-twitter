
import java.util.List;

import twitter4j.*;

public class Search {
	
	private Twitter twitter;
	private String searchTerm;
	private GeoLocation location;
	private double radius;
	private double numberOfPages;
	
	public Search(String term, double latitude, double longitude, double radius, int pages) {
		twitter = Connect.getTwitter();
		searchTerm = term;
		location = new GeoLocation(latitude, longitude);
		this.radius = radius;
		numberOfPages = pages;
	}
	
	public List<Status> query() {
		
		Query q = new Query(searchTerm);
		q.setGeoCode(location, radius, Query.MILES);
		q.setLang("en");
		q.setResultType(Query.RECENT);
		q.setCount(100);
		
		int numberOfTweets = 0;
		
		try {
			QueryResult qResult = twitter.search(q);
			List<Status> tweets = qResult.getTweets();
			List<Status> totalTweets = qResult.getTweets();
			
			for (Status status : tweets) {
				printStatus(status);
				numberOfTweets++;
			}
			numberOfPages--;
			
			while (qResult.hasNext() && numberOfPages > 0) {
				q = qResult.nextQuery();
				qResult = twitter.search(q);
				tweets = qResult.getTweets();
				totalTweets.addAll(qResult.getTweets());
				
				for (Status status : tweets) {
					printStatus(status);
					numberOfTweets++;
				}
				numberOfPages--;
			}
			
			System.out.println(numberOfTweets + " results.");
			
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
		System.out.println(status.getText());
		System.out.println("-----------------------------------------------------------");
		System.out.println();
	}

}