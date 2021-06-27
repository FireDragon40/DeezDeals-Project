
import java.util.Scanner;

import twitter4j.*;

public class ddApp {
    public static void main(String[] args) throws Exception {

        twitterAuth auth = new twitterAuth(System.getenv("consumerAPI"), System.getenv("apiSecret"), 
            System.getenv("accessToken"), System.getenv("tokenSecret"));
        Twitter twitter = auth.authorize(); 

        Query search = new Query();

        // What actually is searched for in the search bar in Twitter. Keep
        // in mind that you can change it based on Twitter API
        System.out.println("Please enter what keyword you wish to look up");
        Scanner input = new Scanner(System.in);
        String keyWord = input.next();
        System.out.println("-------------------------------------");
        search.setQuery(keyWord);
        input.close();
        
        // Sets the number of tweets to return per page, up to a max of 100
        search.setCount(5); 

        // Sets geoLocation for the tweets (Set on omaha) 
        GeoLocation location = new GeoLocation(41.2565, -95.9345);
        double radius = 100;
        Query.Unit units = Query.MILES;
        search.setGeoCode(location, radius, units);

        QueryResult results = twitter.search(search);
        for (Status status : results.getTweets()) 
        {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            System.out.print("-------------------------------------------------------------------\n");
        }

        // Displays amount of results pulled
        System.out.println("Total amount of Tweets pulled: " + search.getCount());




        
    }
}
