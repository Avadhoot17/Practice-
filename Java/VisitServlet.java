import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class VisitServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int count = 0;

        Cookie cookies[] = request.getCookies();

        if(cookies != null){
            for(Cookie c : cookies){
                if(c.getName().equals("visit")){
                    count = Integer.parseInt(c.getValue());
                }
            }
        }

        count++;

        Cookie newCookie = new Cookie("visit", String.valueOf(count));
        response.addCookie(newCookie);

        response.getWriter().println("Visit Count: " + count);
    }
}
