
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import twitter4j.*;

public class twitterAuthTests {
    
    @Test
    
    public void testConstructor()
    {
        twitterAuth auth = new twitterAuth("consumerAPI", "apiSecret", "accessToken", "tokenSecret");
        assertEquals(true, auth.sysVarIsValid());
           
    }
    
    @Test
    
    public void testValidCredentials()
    {
        twitterAuth auth = new twitterAuth("consumerAPI", "apiSecret", "accessToken", "tokenSecret");
        assertEquals(true, auth.sysVarIsValid());
           
    }

    @Test
    
    public void testValidSystemVariableReturn()
    {
        twitterAuth auth = new twitterAuth("consumerAPI", "apiSecret", "accessToken", "tokenSecret");
        assertEquals("none", auth.invalidSysVar());
           
    }
    
    @Test
    public void testOneInvalidSystemVariable()
    {
        twitterAuth auth = new twitterAuth("consumerA", "apiSecret", "accessToken", "tokenSecret");
        assertEquals(false, auth.sysVarIsValid());  
        assertEquals("consumerA", auth.invalidSysVar()); 
    }

    @Test
    
    public void testMultipleInvalidSystemVariableReturn()
    {
        twitterAuth auth = new twitterAuth("consumerAP", "apiSecre", "accessToke", "tokenSecre");
        assertEquals("consumerAP, apiSecre, accessToke, tokenSecre", auth.invalidSysVar());
        assertEquals(false, auth.sysVarIsValid());
    }

    // Need authorize method here
        
 




}
