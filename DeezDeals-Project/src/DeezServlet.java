import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
