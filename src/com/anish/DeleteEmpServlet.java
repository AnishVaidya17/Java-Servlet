package com.anish;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteEmpServlet
 */
@WebServlet("/delete")
public class DeleteEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DeleteDB obj1 = new DeleteDB();
		
		
		
		try {
			if(obj1.connectToDatabase()) {
				System.out.println("Connected!!");
				
				PrintWriter out = response.getWriter();
				
				int empid = Integer.parseInt(request.getParameter("empid"));
				
				obj1.delete(obj1.con, empid);
				System.out.println("Record Deleted!");
				
				obj1.displayRecords(obj1.con, out);
				
				
				
				
			}else {
				   System.out.println("NOT CONNECTED!!");
		     }
			
			
		}catch(Exception e) {
			System.out.println("Error while Deletion!!");
			System.out.println(e);
		
		}

	}//dopost ends

}//servlet ends




class DeleteDB{
	
	Connection con;		//global
	boolean connectToDatabase() throws SQLException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaemployee", "root", "Anish@mysql17");
			System.out.println("Database connected!");
			return true;
			
			
		}catch(Exception e){
           System.out.println("Problem with connection: could not connect!!");
            System.out.println(e);
		}
		
		return false;
	
	
	}//connecttoDatabase ends here
	
	
	
	
	
	
	
	void delete( Connection con, int empid) {
	  	
	   	 try {
	   		 PreparedStatement st = con.prepareStatement( "delete from employee where empid = ? "  ) ;  // our main insert query
	   		 st.setInt( 1 , empid );
	   		 
	   		 st.execute();
	   		 System.out.println(" Record Data Deleted.");
	   		 
	   		 
	   	 } catch (Exception e ) {
	   		 System.out.println("Error while Deleted Data");
	   		 System.out.println(e);
	   	 }
	   		 
	   	
	   	 
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	void displayRecords(Connection con, PrintWriter out) throws SQLException {
		  String query = "select * from employee;";
	   	 	Statement st = con.createStatement();
	   	 	ResultSet rs =  st.executeQuery(query);
	   	 	
	   	 out.print("<html><table border = '1' > <tr> <th>Employee ID</th> <th> Name </th> <th> Contact </th> <th> Designation </th>  <th> Email </th> </tr>");
	   	 
	   	 try {
	   		 
	   		 while(rs.next()) {
	   			 
	   			out.print("<tr><td>");
	   			out.println(rs.getInt(1));
	        	 out.print("</td>");
	        	 out.print("<td>");
	        	 out.print(rs.getString(2));
	        	 out.print("</td>");
	        	 out.print("<td>");
	        	 out.print(rs.getString(3));
	        	 out.print("</td>");
	        	 out.print("<td>");
	        	 out.print(rs.getString(4));
	        	 out.print("</td>");
	        	 out.print("<td>");
	        	 out.print(rs.getString(5));
	        	 out.print("</td>");
	        	 out.print("</tr>");
	   		 }
	   		 
	   		 
	   	 }catch(Exception e) {
	   		 System.out.println("Error While Printing!!!");
	   		 System.out.println(e);
	   	 }
	   	 
	   	  out.print("</table></html>");
	  	  out.print("<h2> It's Done ! </h2>");
	  	  
	}//displayRecords ends
	
	
	
}//class DeleteDB ends
