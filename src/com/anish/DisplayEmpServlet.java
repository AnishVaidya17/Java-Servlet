package com.anish;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayEmpServlet
 */
@WebServlet("/display")
public class DisplayEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayEmpServlet() {
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
		//doGet(request, response);
		
		DispDB obj2 = new DispDB();
		
		
		try {
			
			if(obj2.connectToDatabase()) {
				System.out.println("Connected!!");
				
				PrintWriter out = response.getWriter();
				
				
				obj2.displayRecords(obj2.con, out);
				System.out.println("Employees displayed!!");
				
				
			}else {
				System.out.println("NOT CONNECTED!!");
			}
			
		}catch(Exception e) {
			System.out.println("Problem while displaying values!");
			System.out.println(e);
		 }
		
		
		
	}//dopost ends

}//servlet ends













class DispDB{
	
	Connection con;   //global
	
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
	  	  //out.print("<h2> It's Done ! </h2>");
	  	  
	}//display ends

	
	
	
}//class ends
