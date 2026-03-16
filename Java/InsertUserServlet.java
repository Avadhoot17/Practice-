import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    "root",
                    "password");

            PreparedStatement ps = con.prepareStatement(
                    "insert into users(name) values(?)");

            ps.setString(1, name);

            ps.executeUpdate();

            response.getWriter().println("Data Inserted Successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
