"# DeezDeals-Project"

DeezDeals is an app that will webscrape Twitter for different coupons or deals to get the user the best deal. The user can search for certain keywords such as location, food, clothing, and even get more specific with different brands or types.

deezdeals.xyz

Version 0.002

- Created a new file (twitterAuth.java) in order to better organize the way the driver file will look in future versions.

Version 0.003
- Small organizational changes, mainly in the layout of the application based on folders.

Version 0.01
- Created Test Cases for the twitterAuth class.
- The twitterAuth class now takes in the string name of the system variables instead of a direct key in the main class.
- Added three new methods to the twitterAuth class, all to facilitate checking on the status of a succesful login into Twitter.
- The validSysVar method checks if the system variables exist on the computer and makes an array of those values for easy access.
- The invalidSysVar returns the string of system variables that were not found on the computer.
- The sysVarIsValid method returns true if all system variables are valid, or false if at least one of them does not exist on the computer.

Version 0.05
 - Created the geoLocation class that helps find a latitude and longitude based on zip code.
 - ddApp now takes in zip code, radius, and units of the radius for the search of a tweet.
 - CSV file is provided free by https://simplemaps.com/data/us-zips, this file is what helps locate coordinates
 - Created html and css file for what the website will look like once it is launched on the web.

Version 0.1
- Created test cases for geoLocation class.
- Created a basic filter to ignore retweets on searches.
- Added jQuery logic inside HTML logic.

Version 0.11
- Added javadoc into the java files.

Version 0.2
- Sort buttons were made. The user has the option to sort by relevance and price low to high or price hight to low.
- Filter has a slide bar to adjust the mile radius of where to look and to check boxes on if they want food or clothes.
- Setup nameservers and SSL/TLS for the website
- Switched to apache tomcat for site deployment
- Created the fieldsSet.java class in order to gracefully grab paramaters from the web side (boxes that specify if user wants to search for clothes, food, or other deals) and pass those onto the java application. 
- Created the scraper.java class that takes in paramaters from the fieldsSet class and attempts to develop a regular expression from the fileds passed in. Currently none of the regular expressions work, thus the only thing filtering the Twitter feed is the search setQuery class from the Twitter4j application.

Version 0.5
- Created the ddWebApp.java class that simplifies the authentication, pulling, and array creation of the tweets pulled from a search parameter.
- Added a simple piece to the DeesServlet.java class that glues the functionality of the Tomcat server and the java project
- Made changes to the webpage to simplify the process of creating bookmarks, but it still unfortunatly does not work.
- Servlet now creates a webpage with the latest 5 results based on the keyword. 

