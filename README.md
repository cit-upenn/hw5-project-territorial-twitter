# Territorial Twitter

##Setup
This program can be run in an IDE or on the command line. The user simply needs to type two search queries to start. The user needs to have access to the Internet in order to run this program. 

The files **us-states-tweets.js** and **Leaflet-embed.html** need to be located in the main directory of the program. 

##Rate Limits
Twitter limits the number of search requests that can be made by a single application to 450 per 15 minutes. It is possible to hit that rate limit with a dozen or so searches in that time frame. This program notify you if you hit the rate limit. 

##External Libraries
On the backend, this project used the Twitter4J java library to handle calls to the API and help process Twitter data. 

On the backend, Leaflet.js and Mapbox powered drawing the US states' data and interactivity. 

##Class Notes

###TweetParser
The Search class takes care of finding tweets with the relevant queries. The job of this class is to parse out the locations of the tweets looking at their geotagged location and the user's profile location. The tweet location takes precedence over the user profile location. The locations are identified by the state names and the state abbreviations. Tweets labeled as "New York, NY", "New York" and "NY" will all be recorded for New York, but "NYC" will not be counted anywhere. 

The user locations are typed by the users and are not standardized. That means that tweets from users who live in Canada but self report as living in "California State of Mind" will be counted in California. If someone who lives in New York tweets while in Seattle, but the tweet's location is not recorded, then the tweet will be counted in New York. 

Less than half of the tweets pulled in search will have an identifible location to map to. In addition to no-geotagged and not clearly tagged tweets, tweets from outside of the United States and in Washington D.C. will not be used. The latter is because it is unclear which state to map those tweets, and our javascript library requires a distinction. 

###StateTweetTracker and USAState
StateTweetTracker is simply a collection of USAState objects.

The USAState objects keep track of the counts for each search query. A USAState also stores all of the tweets found for the queries in a states. In latter versions of this program, these tweets would be displayed on the map itself to give a flavor of conversations across states. 

###TaggedStatus
This object holds a Status object as well as two booleans for whether this tweet matched the first or second search term. This would be used in future versions of the project to quickly get or hide tweets associated with a search term on the map. 


###JSWriter
The JSWriter class takes the StateTweetTracker object along withe the two search terms in order to read the data from the us-states-tweets.js file and write the query values into us-states-tweets-done.js file as output. The writeJS method finds the state name in a text file and edits its tweet data based on the values (pulled from the StateTweetTracker class) for that particular state. The instances of "ratio", "q1", "q2", "qc1", and "qc2" are edited according to the parsed data from tweets. The ratio qcomp is calculated by qc1/qc2, and a value of 0 is returned if either qc1 or qc2 is 0. 

The method returns the newly edited strings for the outJS method. The outJS method takes us-states-tweets.js file as input and calls the writeJS method to edit it. The method then converts the returned strings into a newly generated String arraylist, which is then turned into output as us-states-tweets-done.js.

###HTMLWriter 
The HTMLWriter class writes the search terms as entered by the user of the program into the HTML file. The input html file is Leaflet-embed.html and its output is Leaflet-embed-done.html.
The writeHTML method finds the dummy strings searchTerm1 and searchTerm2 within the function for the bottomright legend in the html file and edits them based on user input. 

The method returns the newly edited strings for the outHTML method. The outHTML method takes the Leaflet-embed.html file as input and calls the writeHTML method to edit it. The method then converts the returned strings into a newly generated string arraylist, which is then turned into output as Leaflet-embed-done.html.
	 

###Leaflet-embed-done.html 
The .html file uses the Leaflet JavaScript library along with the map provided by mapbox.com in order to build a colored map that visualizes the frequency comparison between the two search terms in each state.

It is an output of the HTMLWriter class as its legend will reflect the user-entered search terms. The file gets its data from a GEOJSON file called us-states-tweets-done.js, which is an output of the JSWriter class that holds all the parsed tweeter data based on user searches, and color the US map with the results. The color for each particuar ratio value is set via the getColor function. 

Additionally, the Leaflet library allows for a great degree of interactivity via its functions. One such function, onEachFeature, allows detailed data to display for each state after the user hovers their mouse over the state and zooms in if he/she clicks it. 