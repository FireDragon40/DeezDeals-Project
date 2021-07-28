import java.util.ArrayList;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
/**
 * This simple class authorizes the Twitter api using tokens and secrets provided.
 * @author Deez Deals
 */ 
public class twitterAuth 
{
    /**
     * The consumer API from the Twitter Dev portal.
     */
    private String consumerAPI;
    /**
     * The API Secret from the Twitter Dev portal.
     */
    private String apiSecret;
    /**
     * The Account Token from the Twitter Dev portal.
     */
    private String accountToken;
    
    /**
     * The Token Secret from the Twitter Dev portal.
     */
    private String tokenSecret;

    /**
     * This is what lets us know if the authoization was succesful. True is yes, False is no.
     */
    private boolean validSysVar;

    /**
     * If there are invlaid system variables, they are recorded here
     */
    private ArrayList <String> invalidSystemVariables;

    public int authWorks = 0;

    /**
     * Constructor for the twitterAuth class, needs system variables for consumer api, api secret, account token, and token secret to function.
     * @param cAPI The system variable for the consumer API for Twitter.
     * @param apiSec The system variable for the API secret from the Twitter devleoper portal.
     * @param aTok The system variable for the Account Token from Twitter.
     * @param tokSec The systen variable for the token secret from Twitter.
     */
    public twitterAuth(String cAPI, String apiSec, String aTok, String tokSec)
    {
        this.validSysVar = true;
        this.invalidSystemVariables = new ArrayList<String>();
        this.consumerAPI = this.validSysVar(cAPI);
        this.apiSecret = this.validSysVar(apiSec);
        this.accountToken = this.validSysVar(aTok);
        this.tokenSecret = this.validSysVar(tokSec);
    }

    /**
     * Returns a twitter instance with credentials.
     * @return Twitter object from the twitter4j library, the object has the credentials passed into it
     * and lets us use the session.
     */
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
        this.authWorks = 1;
        return twitter;
    }
    /**
     * Checks to see if the value input is a valid System Variable.
     * @param sysVar The system variable name that is being checked to see if it is valid.
     * @return The actual system variable value if it is valid, and "INVALID" if it does not exist
     * on the system. 
     */
    private String validSysVar(String sysVar) 
    {
        String result = "INVALID";
        if (System.getenv(sysVar) == null)
        {
            this.validSysVar = false;
            this.invalidSystemVariables.add(sysVar);
        }
        else 
        {
            result = System.getenv(sysVar);
        }
        return result;
    }

    /**
     * Returns the invalid System Varaibles.
     * @return A comma seperated list of invalid system variables if there are any, "none" if all are valid.
     */

    public String invalidSysVar() 
    {
        String result = "";
        if (this.validSysVar)
        {
            result = "none";
        }
        else 
        {
            result = String.join(", ", this.invalidSystemVariables);
        }
        return result;
    }
    /**
     * Lets user know if Twitter authentication was successful.
     * @return True if all system variables are valid, false if at least one is not.
     */
    public boolean sysVarIsValid() 
    {
        return this.validSysVar;
    }




    
    
}