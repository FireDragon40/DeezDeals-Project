
import java.util.Scanner;

import twitter4j.*;
/**
 * The driver class that lets the user input a zipcode, radius, and key word to get a list of results
 * from Twitter based on those parameters.
 * @author Deez Deals
 */ 
public class ddApp {
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // Insert system variable names into twitterAuth method.
        twitterAuth auth = new twitterAuth("consumerAPI", "apiSecret", "accessToken", "tokenSecret");
        if (!(auth.sysVarIsValid())) 
        {
            String invalids = auth.invalidSysVar();
            System.out.println("It looks like there are no System Variables in place for the following: " + invalids + "\nPlease fix them and try again.");
            System.exit(0);
        }
        
        Twitter twitter = auth.authorize(); 
        // Checks to see if authorize method works
        if (auth.authWorks == 1) 
        {
            System.out.println("Authorization Succesful!");
        }
        else
        {
            System.out.println("Authorization Failed");
            System.exit(0);
        }
        // Potentially add something to catch credentials that are invalid here

        Query search = new Query();
        


        // What actually is searched for in the search bar in Twitter. Keep
        // in mind that you can change it based on Twitter API
        System.out.println("Please enter what keyword you wish to look up");
        Scanner input = new Scanner(System.in);
        String keyWord = input.next();
        search.setQuery(keyWord);
        search.setCount(5); 

        Query.Unit units = Query.MILES;
        System.out.println("Please enter the zip code you wish to search in: ");
        int zip = input.nextInt();

        System.out.println("Please enter the radius from that zip code: ");
        int rad = input.nextInt();

        System.out.println("Is this in miles(m) or kilometers(km)?");
        keyWord = input.next();
        input.close();

        if (keyWord.equals("m"))
        {
            units = Query.MILES;
        }
        else 
        {
            units = Query.KILOMETERS;
        }

        // Use geoLocation class
        geoLocation where = new geoLocation(rad, units, zip);
        // Set search based on our zip code and radius, aswell as units of the radius
        search.setGeoCode(where.getLocation(), where.getRadius(), where.getUnits());


        QueryResult results = twitter.search(search);
        int tweetsPulled = 0;
        for (Status status : results.getTweets()) 
        {
            // If status is NOT a retweet
            if (!status.isRetweet())
            {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                System.out.print("-------------------------------------------------------------------\n");
                tweetsPulled++;
            }
        }

        // Displays amount of results pulled
        System.out.println("Total amount of Tweets pulled: " + tweetsPulled);
        System.out.println("The latitude is: " + where.getLatitude() + "\nThe longitude is: " 
        + where.getLongitude());




        
    }
}
