"# DeezDeals-Project"

DeezDeals is an app that will webscrape Twitter for different coupons or deals to get the user the best deal. The user can search for certain keywords such as location, food, clothing, and even get more specific with different brands or types.

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