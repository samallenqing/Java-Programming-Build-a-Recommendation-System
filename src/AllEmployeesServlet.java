import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllEmployeesServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>All Employees</title></head>");
    out.println("<body>");
    out.println("<center><h1>All Employees</h1>");
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      conn = DriverManager.getConnection("jdbc:odbc:Employees");
      stmt = conn.createStatement();
      String orderBy = request.getParameter("sort");
      if ((orderBy == null) || orderBy.equals("")) {
        orderBy = "SSN";
      }
      String orderByDir = request.getParameter("sortdir");
      if ((orderByDir == null) || orderByDir.equals("")) {
        orderByDir = "asc";
      }
      String query = "SELECT Employees.SSN, Employees.Name, " + "Employees.Salary, "
          + "Employees.Hiredate, Location.Location " + "FROM Location " + "INNER JOIN Employees "
          + "ON Location.Loc_Id = Employees.Loc_Id " + "ORDER BY " + orderBy + " " + orderByDir
          + ";";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        long employeeSSN = rs.getLong("SSN");
        String employeeName = rs.getString("Name");
        long employeeSalary = rs.getLong("Salary");
        Date employeeHiredate = rs.getDate("Hiredate");
        String employeeLocation = rs.getString("Location");
        out.print(employeeSSN + "::");
        out.print(employeeName + "::");
        out.print(employeeSalary + "::");
        out.print(employeeHiredate + "::");
        out.print(employeeLocation + "::");
      }
    } catch (SQLException e) {
      out.println("An error occured while retrieving " + "all employees: " 
          + e.toString());
    } catch (ClassNotFoundException e) {
      throw (new ServletException(e.toString()));
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException ex) {
      }
    }
    out.println("</center>");
    out.println("</body>");
    out.println("</html>");
    out.close();
  }
}
