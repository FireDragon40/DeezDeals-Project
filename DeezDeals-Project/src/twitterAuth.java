import java.util.ArrayList;

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

    // This is what lets us know if the authoization was succesful. True is yes, False is no.
    private boolean validSysVar;

    // If there are invlaid system variables, they are recorded here
    private ArrayList <String> invalidSystemVariables;

    // Instantiates the twitterAuth class, needs consumer api, api secret, account token, and token secret to function.
    public twitterAuth(String cAPI, String apiSec, String aTok, String tokSec)
    {
        this.validSysVar = true;
        this.invalidSystemVariables = new ArrayList<String>();
        this.consumerAPI = this.validSysVar(cAPI);
        this.apiSecret = this.validSysVar(apiSec);
        this.accountToken = this.validSysVar(aTok);
        this.tokenSecret = this.validSysVar(tokSec);
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
    // Checks to see if the value input is a valid System Variable
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

    // Returns the invalid System Varaibles

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

    public boolean sysVarIsValid() 
    {
        return this.validSysVar;
    }




    
    
}