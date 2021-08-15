package backend;

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

//the url that we go to to reach the servlet
@WebServlet("/searchServlet")
public class DeezServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6456363214942064149L;
	private ArrayList <String> results; 
	
	//Initialized when servlet is called
	public void init(ServletConfig config) {
		System.out.println("Initializing servlet");
	}
	
	//GET method called from website
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    
    //Method called when servlet closes
    public void destroy() {
    	System.out.println("Servlet shutting down");
    }
}
