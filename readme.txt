JSWriter class
==================
The JSWriter class takes the StateTweetTracker object along withe the two search terms in order to read the data from the us-states-tweets.js file and write the query values 
into us-states-tweets-done.js file as output. The writeJS method finds the state name in a text file and edits its tweet data based on the values (gotten from the StateTweetTracker class) 
for that particular state. The instances of "ratio", "q1", "q2", "qc1", and "qc2" are edited according to the parsed data from tweets. The ratio qcomp is calculated by qc1/qc2, and a value of 0 
is returned if either qc1 or qc2 is 0. The method returns the newly edited strings for the outJS method. The outJS method takes us-states-tweets.js file as input and calls the writeJS method to 
edit it. The method then converts the returned strings into a newly generated String arraylist, which is then turned into output as us-states-tweets-done.js.

HTMLWriter class
==================

The HTMLWriter class writes the search terms as entered by the user of the program into the HTML file. The input html file is Leaflet-embed.html and its output is Leaflet-embed-done.html.
The writeHTML method finds the dummy strings searchTerm1 and searchTerm2 within the function for the bottomright legend in the html file and edits them based on user input. 
The method returns the newly edited strings for the outHTML method. The outHTML method takes the Leaflet-embed.html file as input and calls the writeHTML method to edit it. The method then 
converts the returned strings into a newly generated string arraylist, which is then turned into output as Leaflet-embed-done.html.
	 

Leaflet-embed-done.html 
==================
The .html file uses the Leaflet JavaScript library along with the map provided by mapbox.com in order to build a colored map that visualizes the frequency comparison between the two search terms in each state.
It is an output of the HTMLWriter class as its legend will reflect the user-entered search terms. The file gets its data from a GEOJSON file called us-states-tweets-done.js, which is an output of the JSWriter 
class that holds all the parsed tweeter data based on user searches, and color the US map with the results. The color for each particuar ratio value is set via the getColor function. Additionally, the 
Leaflet library allows for a great degree of interactivity via its functions. One such function, onEachFeature, allows detailed data to display for each state after the user hovers their mouse over the state 
and zooms in if he/she clicks it. 