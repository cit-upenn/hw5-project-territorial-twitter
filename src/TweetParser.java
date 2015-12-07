import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.*;


/**
 * This class takes an input of geo-tagged tweets and two search queries, and outputs 
 * @author brendonlavernia
 *
 */
public class TweetParser {
	private StateTweetTracker mapOfStates;
	private List<Status> tweets;
	private Map<String, String> mapOfAbbreviations;
	private String query1;
	private String query2;

	public TweetParser(List<Status> tweets, String query1, String query2) {
		mapOfStates = new StateTweetTracker();
		this.tweets = tweets;
		this.query1 = query1.toLowerCase();
		this.query2 = query2.toLowerCase();
		initializeStateMap();
		parseTweets();
		
	}

	public StateTweetTracker getStatesList() {
		return mapOfStates;
	}


	private void parseTweets() {
		Pattern pattern1 = Pattern.compile("[^a-zA-Z0-9]" + query1 + "[^a-zA-Z0-9]");
		Pattern pattern2 = Pattern.compile("[^a-zA-Z0-9]" + query2 + "[^a-zA-Z0-9]");
		Matcher match;
		for (Status tweet : tweets) {
			Place place = tweet.getPlace();
			String tweetLocation = place.getFullName();
			if (!tweetLocation.isEmpty()) {
				int len = tweetLocation.length();
				String abbr = Character.toString(tweetLocation.charAt(len - 2))
						+ Character.toString(tweetLocation.charAt(len - 1));
				
				if (mapOfAbbreviations.containsKey(abbr)) {
					String state = mapOfAbbreviations.get(abbr);
					match = pattern1.matcher(tweet.getText().toLowerCase());
					boolean boo1 = match.matches();
					match = pattern2.matcher(tweet.getText().toLowerCase());
					boolean boo2 = match.matches();
					if (boo1) {
						mapOfStates.getState(state).incrementQuery(1);
					}
					if (boo2) {
						mapOfStates.getState(state).incrementQuery(2);
					}
					mapOfStates.getState(state).addTweet(tweet, boo1, boo2);
				}
			}

		}

	}

	private void initializeStateMap() {
		String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", 
				"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", 
				"Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", 
				"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", 
				"New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", 
				"Ohio", "Oklahoma", "Oregon", "Pennsylvania Rhode Island", "South Carolina",
				"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
				"West Virginia", "Wisconsin", "Wyoming" };
		String[] stateAbbreviations = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", 
				"ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", 
				"NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", 
				"TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" };

		for (int i = 0; i < 50; i += 1) {
			mapOfAbbreviations.put(stateAbbreviations[i], states[i]);
		}
	}

}
