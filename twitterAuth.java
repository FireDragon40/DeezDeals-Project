import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

// This simple class authorizes the Twitter api using tokens and secrets provided 
public class twitterAuth 
{
    // The consumer API from the Twitter Dev portal.
    private String consumerAPI;

    // The API Secret from the Twitter Dev portal.
    private String apiSecret;
    
    // The Account Token from the Twitter Dev portal.
    private String accountToken;
    
    // The Token Secret from the Twitter Dev portal.
    private String tokenSecret;

    // Instantiates the twitterAuth class, needs consumer api, api secret, account token, and token secret to function.
    public twitterAuth(String cAPI, String apiSec, String aTok, String tokSec)
    {
        this.consumerAPI = cAPI;
        this.apiSecret = apiSec;
        this.accountToken = aTok;
        this.tokenSecret = tokSec;
    }

    // Returns a twitter instance
    public Twitter authorize() 
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        .setOAuthConsumerKey(this.consumerAPI)
        .setOAuthConsumerSecret(this.apiSecret)
        .setOAuthAccessToken(this.accountToken)
        .setOAuthAccessTokenSecret(this.tokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());

        // Authorization
        Twitter twitter = tf.getInstance();
        return twitter;
    }


    
    
}
