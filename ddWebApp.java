import java.util.ArrayList;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;



public class ddWebApp {
    

    private ArrayList <String> resultArray;

    private Twitter twitter;
 
    private Query search;

    public final int amountofResults = 5;


    public ddWebApp(String searchWord)
    {
        this.resultArray = new ArrayList<String>();
        this.authenticateTwitter();
        setSearch(searchWord);



    }

    //Authenticate
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

    // Set search query

    private void setSearch(String keyWord)
    {
        // Use fieldUsed to transfer keyword to the twitter4j ref library to create a search.
        this.search = new Query();
        search.setQuery(keyWord);
        
        // Default tweets pulled we use is 5!
        search.setCount(this.amountofResults); 
    }


    // Set Up query


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