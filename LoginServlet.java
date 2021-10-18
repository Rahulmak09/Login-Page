package com.cybage.controllers;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cybage.bean.RegisterBean;
import com.cybage.dao.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 System.out.println("Email ");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Email = request.getParameter("userEmail");
		String Password = request.getParameter("userPassword");
		//String userRole =request.getParameter("userRole");
        System.out.println("Email :"+Email);
        System.out.println("Password :"+Password);
		RegisterBean registerBean = new RegisterBean();

		registerBean.setUserEmail(Email);
		registerBean.setUserPassword(Password);

		LoginDao loginDao = new LoginDao();

		try {
			String userValidate = loginDao.authenticateUser(registerBean);

//         if(userValidate.equals("Admin_Role"))
//          {
//              System.out.println("Admin's Home");
//   
//              HttpSession session = request.getSession(); //Creating a session
//              session.setAttribute("Admin", Email); //setting session attribute
//              request.setAttribute("userName", Password);
//   
//              request.getRequestDispatcher("/JSP/Admin.jsp").forward(request, response);
//          }
//          else if(userValidate.equals("Editor_Role"))
//          {
//              System.out.println("Editor's Home");
//   
//              HttpSession session = request.getSession();
//              session.setAttribute("Editor", userName);
//              request.setAttribute("userName", userName);
//   
//              request.getRequestDispatcher("/JSP/Editor.jsp").forward(request, response);
//          }
			if (userValidate.equals("userEmail")) {
				System.out.println("User's Home");
				
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10 * 60);
				session.setAttribute("Email", Email);
				session.setAttribute("Password", Password);

				request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);
			} 
			else {
				System.out.println("Error message = " + userValidate);
				request.setAttribute("errMessage", userValidate);

				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
