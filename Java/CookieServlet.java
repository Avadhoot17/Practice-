import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CookieServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) 123456789012345678901
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie cookie = new Cookie("user", "Avadhoot");
        response.addCookie(cookie);

        out.println("<h2>Cookie Created Successfully</h2>");
    }
}
