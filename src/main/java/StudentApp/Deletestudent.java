package StudentApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deletelink")
public class Deletestudent extends HttpServlet {
	Connection con;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1CCA14", "root", "sql123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("studentid");
		
		int sid=Integer.parseInt(id);
        PreparedStatement pstmt=null;
        String query="delete from student_info where Student_Id=?";
//        String query="insert into student_info values(?,?,?)";
        try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, sid);
//			pstmt.setString(2, name);
//			pstmt.setString(3, stream);
			int count=pstmt.executeUpdate();
			 PrintWriter pw=resp.getWriter();
		     pw.print("<h1>"+count+" record deleted"+"</h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
