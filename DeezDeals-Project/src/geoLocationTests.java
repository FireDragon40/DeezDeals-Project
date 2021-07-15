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
        GeoLocation helper = new GeoLocation(41.29854, -96.05065);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(68134, test.getZipCode());
        assertEquals(50, test.getRadius(), 0.0001);
        assertEquals(41.29854, test.getLatitude(), 0.0001);
        assertEquals(-96.05065, test.getLongitude(), 0.0001);
        assertEquals(helper, test.getLocation());

    }
    
    @Test
    
    public void testInvalidRadius() throws IOException
    {
        Query.Unit units = Query.MILES;
        geoLocation test = new geoLocation(-50, units , 68134);
        GeoLocation helper = new GeoLocation(41.29854, -96.05065);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(68134, test.getZipCode());
        assertEquals(0, test.getRadius(), 0.0001);
        assertEquals(41.29854, test.getLatitude(), 0.0001);
        assertEquals(-96.05065, test.getLongitude(), 0.0001);
        assertEquals(helper, test.getLocation());
  
    }

   @Test

    public void testInvalidZipCodeTooLong() throws IOException
    {
        Query.Unit units = Query.MILES;
        geoLocation test = new geoLocation(-50, units, 681344);
        GeoLocation helper = new GeoLocation(0, 0);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(00000, test.getZipCode());
        assertEquals(0, test.getRadius(), 0.0001);
        assertEquals(0, test.getLatitude(), 0.0001);
        assertEquals(0, test.getLongitude(), 0.0001);
        assertEquals(helper, test.getLocation());
  
    }

    @Test

    public void testInvalidZipCodeTooSmall() throws IOException
    {
        Query.Unit units = Query.MILES;
        geoLocation test = new geoLocation(-50, units, 6813);
        GeoLocation helper = new GeoLocation(0, 0);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(00000, test.getZipCode());
        assertEquals(0, test.getRadius(), 0.0001);
        assertEquals(0, test.getLatitude(), 0.0001);
        assertEquals(0, test.getLongitude(), 0.0001);
        assertEquals(helper, test.getLocation());
  
    }

    @Test

    public void testInvalidZipCodeDNE() throws IOException
    {
        Query.Unit units = Query.MILES;
        geoLocation test = new geoLocation(-50, units, 11111);
        GeoLocation helper = new GeoLocation(0, 0);
        
        assertEquals(Query.MILES, test.getUnits());
        assertEquals(11111, test.getZipCode());
        assertEquals(0, test.getRadius(), 0.0001);
        assertEquals(0, test.getLatitude(), 0.0001);
        assertEquals(0, test.getLongitude(), 0.0001);
        assertEquals(helper, test.getLocation());
  
    }
}