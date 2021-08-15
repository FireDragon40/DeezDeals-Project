
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;

/**
 * Class that helps us create a dynamic html page with dynamic search results on a basic html page.
 * @author Deez Deals
 */
@WebServlet("/searchServlet")
public class DeezServlet extends HttpServlet {

    /**
     * Contains the serial number of the Version UID.
     */
	private static final long serialVersionUID = -6456363214942064149L;
	/**
     *  Contains the array list of the twitter results that were pulled
     */
    private ArrayList <String> results; 
	
	//Initialized when servlet is called
    /**
     * Initializes the servlet so that it can start generating a new dyanmic webpage.
     * @param config The servlet configuration passed in.
     */
	public void init(ServletConfig config) {
		System.out.println("Initializing servlet");
	}
	
	//GET method called from website
    /**
     * Creates the dynamic web pages by calling all the needed methods to pull the tweets and out them 
     * on the webiste.
     * @param request The http servlet request that is sent.
     * @param response The http servlet response that is going to be recieved.
     * @throws ServletException If there is a missing servlet.
     * @throws IOException Catches an exception if the arraylist does not work.
     */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		//PrintWriter class to send our tags to html
		PrintWriter out = response.getWriter();
    	String searchresult = request.getParameter("searchinput");
        
    	//Class referring to the twitter4j results
    	ddWebApp app = new ddWebApp(searchresult);
    	try {
			results = app.getArrayResults();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
    	
    	//HTML output
        out.println("<html>");
        out.println("<body>");
        
        //for loop to create text of results from ddWebApp if the search result is not null
        if (searchresult != null) {
            for (int i = 0; i < results.size(); i++) {
            	out.println("<div> this is the search result: " + results.get(i) + "</div>");
            }
        } else {
            out.println("Search for something");
        }

        //HTML output 
        out.print("<form action=\"");
        out.print("searchServlet\" ");
        out.println("method=POST>");
        out.println("Search again:");
        out.println("<input type=text size=20 name=searchinput>");
        out.println("<br>");
        out.println("<input type=submit value=Search>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
	}
	
	//POST method called from website, calls GET method
    /**
     * Post method that is there as an extension of the doGet method.
     * @param request The http servlet request that is sent.
     * @param response The http servlet response that is going to be recieved.
     * @throws ServletException If there is a missing servlet.
     * @throws IOException Catches an exception if the arraylist does not work.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    
    //Method called when servlet closes
    /**
     * Destroys the servlet when it is going to be closed.
     */
    public void destroy() {
    	System.out.println("Servlet shutting down");
    }
}
