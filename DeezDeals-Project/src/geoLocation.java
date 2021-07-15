import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import twitter4j.*;

public class geoLocation 
{
    private double radius;

    private Query.Unit units;

    private int zipCode;

    private double latitude;

    private double longitude;

    // Constructor  that gets all necessary parameters
    public geoLocation(double rad, Query.Unit uni, int zip) throws IOException 
    {
        this.radius = this.setRadius(rad);
        this.units = uni;
        this.zipCode = this.setZip(zip);
        this.getCoordinates();
    }

    // This method reads through the zip code csv file and finds the latitude and longitude in order to pass it to the setGeoCode method.
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

    private double setRadius(double rad)
    {
        double result = 0;
        if (rad > 0) 
        {
            result = rad;
        }
        return result;
    }

    private int setZip(int zip) 
    {
        int result = 0;
        if (zip >= 10000 && zip < 100000)
        {
            result = zip;
        }
        return result;
    }

    public Query setGCode(Query search) 
    {
        GeoLocation location = new GeoLocation(this.latitude, this.longitude);
        search.setGeoCode(location, this.radius, this.units);
        return search;
    }

    public GeoLocation getLocation() 
    {
        GeoLocation location = new GeoLocation(this.latitude,this.longitude);
        return location;
    }

    public double getRadius() 
    {
        return this.radius;
    }

    public Query.Unit getUnits() 
    {
        return this.units;
    }

    public double getLatitude() 
    {
        return this.latitude;
    }

    public double getLongitude() 
    {
        return this.longitude;
    }

    public int getZipCode() 
    {
        return this.zipCode;
    }


}
