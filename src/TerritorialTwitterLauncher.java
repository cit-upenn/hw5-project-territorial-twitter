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
		Scanner in = new Scanner(System.in);
		
		while (run) {
			
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
			
			//This should take in the parsed tweets and make updates to JavaScript and HTML files as needed
			JSWriter js = new JSWriter(parsedTweets, firstTerm, secondTerm);
		
			js.outJS();
			
			HTMLWriter html = new HTMLWriter( firstTerm, secondTerm);
			
			html.outHTML();
			
			File htmlFile = new File("Leaflet-embed-done.html");
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
			} else {
				run = false;
			}
		}
		in.close();

	}

}
