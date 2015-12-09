import java.awt.Desktop;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import twitter4j.Status;

public class TerritorialTwitterLauncher {

	public static void main(String[] args) {
		
		System.out.println("===================");
		System.out.println("Territorial Twitter");
		System.out.println("===================");
		System.out.println("Compares the popularity of any two search terms against each other.");
		System.out.println();
		boolean run = true;
		int numberOfPages = 50;
		int rateLimit = numberOfPages * 2;
		Scanner in = new Scanner(System.in);
		
		while (run && rateLimit < 480) {
			
			// Asks for and reads search terms and runs Search
			System.out.print("Enter the first search term: ");
			String firstTerm = in.nextLine();
			System.out.print("Enter the second search term: ");
			String secondTerm = in.nextLine();
			
			//Saves the tweets to a linked list
			List<Status> firstQueryTweets = new LinkedList<Status>();
			List<Status> secondQueryTweets = new LinkedList<Status>();
			
			//Performs search and adds the lists of tweets to the Linked List
			Search firstSearch = new Search(firstTerm, numberOfPages);
			firstQueryTweets.addAll(firstSearch.query());
			Search secondSearch = new Search(secondTerm, numberOfPages);
			secondQueryTweets.addAll(secondSearch.query());
			
			//Parses tweets and saves counts/tweets to the StateTweetTracker
			TweetParser firstParser = new TweetParser(firstQueryTweets, 1);
			StateTweetTracker parsedTweets = firstParser.getStatesList();
			
			TweetParser secondParser = new TweetParser(secondQueryTweets, 1);
			
			parsedTweets.addParsedTweets(secondParser.getStatesList(), 2);
			
			//This should take in the parsed tweets make updates to Javascript and HTML files as needed
			JSWriter write = new JSWriter(parsedTweets);
			String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", 
					"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", 
					"Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", 
					"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", 
					"New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", 
					"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
					"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
					"West Virginia", "Wisconsin", "Wyoming" };
			for(String state : states) {
				System.out.println(state + ": " + parsedTweets.getQuery1Count(state) + ", " + parsedTweets.getQuery2Count(state) );
			}
			List<TaggedStatus> idahoTweets = parsedTweets.getState("Idaho").getTweets();
			for(TaggedStatus moreTweets : idahoTweets) {
				System.out.println(moreTweets.getTweet());
			}
			
			write.outJS();
			
			
			File htmlFile = new File("Leaflet-embed.html");
			try {
				Desktop.getDesktop().browse(htmlFile.toURI());
			} catch(Exception e) {
				System.out.println("The HTML file is missing. Please find on your desktop and open.");
			}
			
			
			// Asks for and reads user input to run again
			System.out.print("Would you like to run another search (y/n)?: ");
			String reply = in.nextLine();
			
			// "y" to play again, anything else to stop
			if (reply.equalsIgnoreCase("y")) {
				run = true;
				rateLimit += (2*numberOfPages);
			} else {
				run = false;
			}
		}
		
		in.close();
		
		if (rateLimit >= 480) {
			System.out.println("Your search may hit Twitter's rate limit, please wait a few minutes before rerunning the program.");
		}

	}

}
