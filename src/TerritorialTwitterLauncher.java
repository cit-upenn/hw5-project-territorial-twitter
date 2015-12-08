import java.util.Scanner;

public class TerritorialTwitterLauncher {

	public static void main(String[] args) {
		
		System.out.println("===================");
		System.out.println("Territorial Twitter");
		System.out.println("===================");
		System.out.println("Compares the popularity of any two search terms against each other.");
		System.out.println();
		boolean run = true;
		int numberOfPages = 10;
		int rateLimit = 2*numberOfPages;
		Scanner in = new Scanner(System.in);
		
		while (run && rateLimit < 480) {
			
			// Asks for and reads search terms and runs Search
			System.out.print("Enter the first search term: ");
			String firstTerm = in.nextLine();
			System.out.print("Enter the second search term: ");
			String secondTerm = in.nextLine();
			
			Search firstSearch = new Search(firstTerm, numberOfPages);
			firstSearch.query();
			Search secondSearch = new Search(secondTerm, numberOfPages);
			secondSearch.query();
			
			
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
