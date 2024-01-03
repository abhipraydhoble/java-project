package com.project.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registrationservlet
 */
@WebServlet("/register")
public class registrationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrationservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("Fname");
		String userDateOfBirth = request.getParameter("DOB");
		String userAddress = request.getParameter("address");
		String userGender = request.getParameter("gender");
		String userCity = request.getParameter("city");
		String userState = request.getParameter("state");
		String userId = request.getParameter("user");
		String userPwd = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user1","root","pranay");
			PreparedStatement pst = con.prepareStatement("insert into persons(userName,userDateOfBirth,userGender,userAddress,userCity,userState,userId,userPassword) values(?,?,?,?,?,?,?,?,?)");
			pst.setString(1, userName);
			pst.setString(2, userDateOfBirth);
			pst.setString(3, userAddress);
			pst.setString(4, userGender);
			pst.setString(5, userCity);
			pst.setString(6, userState);
			pst.setString(7, userId);
			pst.setString(8, userPwd);
				
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if(rowCount > 0) {
				request.setAttribute("stutes", "success");
			}else {
				request.setAttribute("stutes", "failed");
			}
			dispatcher.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

}