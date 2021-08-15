import java.util.ArrayList;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


/**
 * Class that facilitates the creation of a search term on the website with a keyword.
 * @author Deez Deals
 */
public class ddWebApp {
    
    /**
     * Has the array list of the results generated from the twitter search results.
     */
    private ArrayList <String> resultArray;

    /**
     * An instance of the Twitter twitter4j class that allows us to authenitcate with the server.
     */
    private Twitter twitter;
 
    /**
     * An instance of the Query twitter4j class that allows us to build the search paramater for the twitter scrape.
     */
    private Query search;

    /**
     * The maximum amount of tweets that will be pulled in one search.
     */
    public final int amountofResults = 5;


    /**
     * Constructor that takes in the keyword that will be used to filter results.
     * @param searchWord The word that the user want to search for in the tweets.
     */
    public ddWebApp(String searchWord)
    {
        this.resultArray = new ArrayList<String>();
        this.authenticateTwitter();
        setSearch(searchWord);



    }

    /**
     * Private method that simply authenticates the Twitter credentials using
     * the hidden api_keys class.
     */
    private void authenticateTwitter()
    {
        api_keys helper = new api_keys();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        .setOAuthConsumerKey(helper.getconsumerAPI())
        .setOAuthConsumerSecret(helper.getapiSecret())
        .setOAuthAccessToken(helper.getaccessToken())
        .setOAuthAccessTokenSecret(helper.gettokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());

        // Authorization
        this.twitter = tf.getInstance();
    }

    
    /**
     * Sets up the query so that in can search the keyword passed in.
     * @param keyWord The string that is being used for the search query.
     */
    private void setSearch(String keyWord)
    {
        // Use fieldUsed to transfer keyword to the twitter4j ref library to create a search.
        this.search = new Query();
        search.setQuery(keyWord);
        
        // Default tweets pulled we use is 5!
        search.setCount(this.amountofResults); 
    }


    

    /**
     * Creates the Array List of the results from the twitter scrape.
     * @return The ArrayList <String> of the tweet results matcihing the search keyword.
     * @throws TwitterException To catch an invalid search.
     */
    public ArrayList <String> getArrayResults() throws TwitterException
    {
        QueryResult results = twitter.search(this.search); // Transfer to scraper
        for (Status status : results.getTweets()) 
        {
            // If status is NOT a retweet
            if (!status.isRetweet())
            {
                
                this.resultArray.add( "@" + status.getUser().getScreenName() + ": " + status.getText() + " at " + status.getCreatedAt());
                //System.out.println("@" + status.getUser().getScreenName() + ": " + status.getText() + "at " + status.getCreatedAt());
            }
        }
        return this.resultArray;
        
    }
}