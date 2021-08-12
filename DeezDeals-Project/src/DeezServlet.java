package backend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchServlet")
public class DeezServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6456363214942064149L;
	
	
	public void init(ServletConfig config) {
		System.out.println("Initializing servlet");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html> servlet is working </html>");
		out.flush();
	}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String searchForm = "frontend/index.jsp";
    	RequestDispatcher dispatcher = request.getRequestDispatcher(searchForm);
    	dispatcher.forward(request, response);
    	
    	String searchresult = request.getParameter("searchinput");
        //response content type
        //response.setContentType("text/html");

        //printwriter to write to html
        PrintWriter out = response.getWriter();
        //out.println("<html>");
        //out.println("<div>" + searchresult + "</div>");
        //out.println("test");
        //out.println("</html>");
        
        out.println("<div>" + searchresult + "</div>");
        //response.sendRedirect("index.jsp?username=" + searchresult);
        //response.getWriter().write(searchresult);
        out.println("<html> servlet is working </html>");

    }
    
    public void destroy() {
    	System.out.println("Servlet shutting down");
    }
}
