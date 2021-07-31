
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
        Scanner input = new Scanner(System.in);
        


        // What actually is searched for in the search bar in Twitter. Keep
        // in mind that you can change it based on Twitter API
        System.out.println("Please enter what keyword you wish to look up. Input none "
            + "if you don't want to use a keyword: ");
        
        String keyWord = input.next();
        
        // New fieldsSet class stuff goes here! Uses fieldsSet stuff



        int typeofDeal = 0;
        // Helpers to get user input
        boolean kw = false;
        boolean clothes = false;
        boolean food = false;
        boolean other = false;
        while (true)
        {
             System.out.println("Are you looking for food, clothes, or other type of deals?\n"
                + "1 for food, 2 for clothes, 3 for other, or 0 to stop adding values");
            typeofDeal = input.nextInt();
            
            // Food parameter
            if (typeofDeal == 1)
            {
                food = true;
            }
            
            // Clothes parameter
            else if (typeofDeal == 2)
            {
                clothes = true;
            }
            // Other parameter
            else if (typeofDeal == 3)
            {
                other = true;
            }
            else if (typeofDeal == 0)
            {
                break;
            }

        }
        // Check if user put none for a keyword
        if (keyWord.equalsIgnoreCase("none"))
        {
            keyWord = "";

        }
        else
        {
            kw = true;
        }

        // Initialize the fieldsSet class to catch paramaters.
        fieldsSet fieldsUsed = new fieldsSet(kw, food, clothes, other);

        if (fieldsUsed.getKeyWordOn())
        {
            fieldsUsed.setKeyword(keyWord);
        }

        // Use fieldUsed to transfer keyword to the twitter4j ref library to create a search.
        search.setQuery(fieldsUsed.getkeyWord());
        
        // Default tweets pulled we use is 5!
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


        QueryResult results = twitter.search(search); // Transfer to scraper
        int tweetsPulled = 0;
        for (Status status : results.getTweets()) 
        {
            // If status is NOT a retweet
            if (!status.isRetweet())
            {
                System.out.println("@" + status.getUser().getScreenName() + ": " + status.getText());
                System.out.println(status.getCreatedAt());
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
