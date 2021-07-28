import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import twitter4j.*;
/**
 * geoLocation.java - A class that facilitates the geolocation process of the twitter API and twitter4j process.
 * Also reads a CSV file to translate a zip code in the USA to latitude and longitude coordinates.
 * @author Deez Deals
 */
public class geoLocation 
{
    /**
     * The radius of the location that the user wishes to use.
     */
    private double radius;
    /**
    * The units in Query.Unit enumuration of the radius set forth. 
    */
    private Query.Unit units;
    /**
     * The zipcode the user wants to search from.
     */
    private int zipCode;
    /**
     * The latitude of the zipcode.
     */
    private double latitude;
    /**
     * The longitude of the zipcode.
     */
    private double longitude;

    /**
    * Constructor for the geoLocation class.
    * @param rad The radius that the user wishes to search a tweet in. 
    * @param uni The units that the user wishes to input, either kilometers or miles.
    * @param zip The zipcode of the location that the user wishes to serach from.
    * @throws IOException
    */
    public geoLocation(double rad, Query.Unit uni, int zip) throws IOException 
    {
        this.radius = this.setRadius(rad);
        this.units = uni;
        this.zipCode = this.setZip(zip);
        this.getCoordinates();
    }

    /**
    * This method reads through the zip code csv file and finds the latitude and longitude in order to pass it to the setGeoCode method.
    * 
    * @throws IOException If the file is not read properly
    */
    private void getCoordinates() throws IOException
    {
        // Makes sure zip code is valid
        if (this.zipCode > 0) 
        {
            try {
                // Open file from the destination folder
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jetpa\\Desktop\\uszips.csv"));
                String line;
                int iteration = 0;
                while ( (line = br.readLine()) != null ) {
                    // Helps skip header stuff
                    if (iteration == 0)
                    {
                        iteration++;
                        continue;
                    }
                    else 
                    {
                        // Splits comma separated list into a string array
                        String[] values = line.split(",");
                        // Remove quotations that are oddly enough added into csv file
                        String zipValue = values[0].replace("\"", "");
                        // Parse the zipcode into a integer for comparison
                        int helper = Integer.parseInt(zipValue);
                        if(helper == this.zipCode) {
                            // If it is the correct zipcode then pull the first and second cell which contain latitude and longitude respectfully
                            String latValue = values[1].replace("\"", "");
                            this.latitude = Double.parseDouble(latValue);
                        
                            String longValue = values[2].replace("\"", "");
                            this.longitude = Double.parseDouble(longValue);
                            break;
                        }
                }
                }
                br.close();
            }
            catch (FileNotFoundException FNF)   
            {
                //FIle not found goes here
            }
        }
        else 
        {
            // Null island, just in case something goes wrong. Can help create a message in the ddApp driver class to tell them invalid zip
            this.latitude = 0;
            this.longitude = 0;
        }
    }
    /**
     * Checks if the radius is a valid radius, meaning greater than zero.
     * @param rad The radius that will be checked.
     * @return The value of the raidus, it will keep its value if it is greater than zero, but it will become zero if it is not.
     */
    private double setRadius(double rad)
    {
        double result = 0;
        if (rad > 0) 
        {
            result = rad;
        }
        return result;
    }

    /**
     * A simple check to make sure the zip code is not too big or too small.
     * @param zip The zipcode that will be checked.
     * @return The value of the zipcode, if it is valid it will keep its value and if not it will become zero.
     */
    private int setZip(int zip) 
    {
        int result = 0;
        if (zip >= 10000 && zip < 100000)
        {
            result = zip;
        }
        return result;
    }

    /**
     * Sets the geoCode so that a query search can be performed from the location input.
     * @param search The query from the twitter4j class that will be changed to incorporate geoLocation. 
     * @return The Query that has the geolocation put into it so that it searches by location.
     */
    public Query setGCode(Query search) 
    {
        GeoLocation location = new GeoLocation(this.latitude, this.longitude);
        search.setGeoCode(location, this.radius, this.units);
        return search;
    }
    /**
     * Returns a GeoLocation class from the twitter4j refrence library with the latitude and lonmgitude of the zipcode input.
     * @return The GeoLocation object.
     */
    public GeoLocation getLocation() 
    {
        GeoLocation location = new GeoLocation(this.latitude,this.longitude);
        return location;
    }

    /**
     * Returns the radius of the object.
     * @return The radius input by the user.
     */
    public double getRadius() 
    {
        return this.radius;
    }
    
    /**
     * Returns the units that the radius is in.
     * @return The Query.Unit enumuration of the twitter4j refrence library. Either Kilometers or miles.
     */
    public Query.Unit getUnits() 
    {
        return this.units;
    }
    /**
     * Returns the latitude of the object, found from the input zip code.
     * @return The latitude from the zip code.
     */
    public double getLatitude() 
    {
        return this.latitude;
    }

    /**
     * Returns the longitude of the object, found from the input zip code.
     * @return The longitude from the zip code
     */
    public double getLongitude() 
    {
        return this.longitude;
    }
    
    /**
     * Returns the zip code of the object.
     * @return The zip code of the object.
     */
    public int getZipCode() 
    {
        return this.zipCode;
    }


}
