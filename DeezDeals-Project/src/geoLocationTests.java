import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import twitter4j.*;

public class geoLocationTests {
    
    @Test
    
    public void testValidConstructor() throws IOException
    {
        Query.Unit units = Query.MILES;
        geoLocation test = new geoLocation(50, units , 68134);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(68134, test.getZipCode());
        assertEquals(50, test.getRadius(), 0.0001);

    }
    
    @Test
    
    public void testInvalidRadius() throws IOException
    {
        Query.Unit units = Query.MILES;
        geoLocation test = new geoLocation(-50, units , 68134);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(68134, test.getZipCode());
        assertEquals(0, test.getRadius(), 0.0001);

  
    }

   @Test

    public void testInvalidZipCodeTooLong() throws IOException
    {
        Query.Unit units = Query.MILES;
        geoLocation test = new geoLocation(-50, units, 681344);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(00000, test.getZipCode());
        assertEquals(0, test.getRadius(), 0.0001);
  
    }

    @Test

    public void testInvalidZipCodeTooSmall() throws IOException
    {
        Query.Unit units = Query.MILES;
        geoLocation test = new geoLocation(-50, units, 6813);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(00000, test.getZipCode());
        assertEquals(0, test.getRadius(), 0.0001);
  
    }

    @Test

    public void testInvalidZipCodeDNE() throws IOException
    {
        Query.Unit units = Query.MILES;
        geoLocation test = new geoLocation(-50, units, 11111);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(11111, test.getZipCode());
        assertEquals(0, test.getRadius(), 0.0001);
  
    }
}
