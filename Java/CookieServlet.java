import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CookieServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) 1234567890123
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie cookie = new Cookie("user", "Avadhoot");
        response.addCookie(cookie);

        out.println("<h2>Cookie Created Successfully</h2>");
    }
}
