package nz.ac.auckland.se281.a4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nz.ac.auckland.se281.a4.ds.Graph;
import nz.ac.auckland.se281.a4.ds.Node;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************
public class TweetGraph extends Graph {

	protected List<Tweet> tweets;
	// Change this to map
	protected Map<TwitterHandle, List<Tweet>> nodeTweets;

	public TweetGraph(List<String> edges, List<Tweet> tweets, Map<TwitterHandle, List<Tweet>> map) throws Exception {
		super(edges);
		this.tweets = tweets;
		// changed to LinkedHashMap for fixed order
		this.nodeTweets = new LinkedHashMap<>();
		nodeTweets = map;
	}

	public List<Tweet> getTweets(Node n) {
		return nodeTweets.get(n);
	}

	public List<String> getTweetsTexts(TwitterHandle n){
		List<String> texts = new ArrayList<>(); // Only allowed to use ArrayList HERE !!!
		for(Tweet t : getTweets(n)){
			texts.add(t.getTextString());
		}
		return texts;
	}

	// search for a keyword in a tweet starting from a given node
	public String searchTweet(TwitterHandle user, String tweetKeyword) {
		
		List<Node<String>> dfsOrder = this.depthFirstSearch(user, false);
		for(Node<String> handleToSearch : dfsOrder) {
			
			String currentId = handleToSearch.getValue();
			
			for(Map.Entry<TwitterHandle, List<Tweet>> entry : this.nodeTweets.entrySet()) {
				if(entry.getKey().getID().contentEquals(currentId)) {
					TwitterHandle currentHandle = entry.getKey();
					List<String> tweetsList = getTweetsTexts(currentHandle);
					for(String tweet : tweetsList) {
						if(tweet.contains(tweetKeyword)) {
							System.out.println(tweetKeyword);
							return("The tweet string found is: " + tweet + "\nUser " + currentHandle.getName() + " {" + currentId + "} tweeted " + tweetKeyword);
						}
					}
				}
			}
		}
		
		return null;
	}
}
