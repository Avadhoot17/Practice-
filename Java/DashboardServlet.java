import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session != null){
            response.getWriter().println("Welcome " + session.getAttribute("user"));
        }else{
            response.getWriter().println("Please Login First");
        }
    }
}
