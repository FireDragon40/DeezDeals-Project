import java.io.*;
import java.rmi.server.ServerCloneException;

import javax.servlet.*;
import javax.servlet.http.*;

public class DeezServlet extends HttpServlet {

    private String msg;

    public void init() throws ServletException {
        msg = "test message";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //response content type
        response.setContentType("text/html");

        //logic
        PrintWriter out = response.getWriter();
        out.println("<p>" + msg + "</p>");

    }

    public void destroy(){
        //stuff
    }
}
