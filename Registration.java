

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		 String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String account = request.getParameter("account");
	        String balance= request.getParameter("balance");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingManagementSystem", "root", "dibyangna21@");
	         // Check if the username is unique
	            PreparedStatement checkUsernameStmt = con.prepareStatement("SELECT username FROM bank_users WHERE username = ?"); 
	            checkUsernameStmt.setString(1, username);
	            ResultSet resultSet = checkUsernameStmt.executeQuery();
	            if (resultSet.next()) 
	            {
	            	 pw.println("<!DOCTYPE html>");
	                 pw.println("<html>");
	                 pw.println("<head>");
	                 pw.println("<title>UserInfo</title>");
	                 pw.println("<style>");
	                 pw.println("body {\r\n"
	                 		+ "            margin: 0;\r\n"
	                 		+ "            padding: 0;\r\n"
	                 		+ "            font-family: 'Arial', sans-serif;\r\n"
	                 		+ "            background: url('http://surl.li/osjmz') no-repeat center center fixed;\r\n"
	                 		+ "            background-size: cover;\r\n"
	                 		+ "            display: flex;\r\n"
	                 		+ "            align-items: center;\r\n"
	                 		+ "            justify-content: center;\r\n"
	                 		+ "            height: 100vh;\r\n"
	                 		+ "            color: #fff;\r\n"
	                 		+ "        }");
	                 pw.println(" .container {\r\n"
	                 		+ "            background: rgba(0, 0, 0, 0.5);\r\n"
	                 		+ "            padding: 20px;\r\n"
	                 		+ "            border-radius: 10px;\r\n"
	                 		+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);\r\n"
	                 		+ "            text-align: center;\r\n"
	                 		+ "        }");
	                 pw.println("h1 {\r\n"
	                 		+ "            margin-bottom: 20px;\r\n"
	                 		+ "        }");
	                 pw.println(" form {\r\n"
	                 		+ "            display: flex;\r\n"
	                 		+ "            flex-direction: column;\r\n"
	                 		+ "            align-items: center;\r\n"
	                 		+ "        }\r\n"
	                 		+ "");
	                 pw.println("label {\r\n"
	                 		+ "            color: #fff;\r\n"
	                 		+ "            margin-bottom: 10px;\r\n"
	                 		+ "            font-weight: bold;\r\n"
	                 		+ "        }");
	                 pw.println(" input {\r\n"
	                 		+ "            width: 100%;\r\n"
	                 		+ "            padding: 10px;\r\n"
	                 		+ "            margin-bottom: 20px;\r\n"
	                 		+ "            border: 1px solid #fff;\r\n"
	                 		+ "            border-radius: 5px;\r\n"
	                 		+ "            box-sizing: border-box;\r\n"
	                 		+ "        }");
	                 pw.println(" button {\r\n"
	                 		+ "            background-color: #fff;\r\n"
	                 		+ "            color: #000;\r\n"
	                 		+ "            padding: 10px 20px;\r\n"
	                 		+ "            border: none;\r\n"
	                 		+ "            border-radius: 5px;\r\n"
	                 		+ "            font-weight: bold;\r\n"
	                 		+ "            cursor: pointer;\r\n"
	                 		+ "        }");
	                 pw.println("button:hover {\r\n"
	                 		+ "            background-color: #000;\r\n"
	                 		+ "            color: #fff;\r\n"
	                 		+ "        }");
	                 pw.println("a{\r\n"
	                 		+ "         color:white\r\n"
	                 		+ "}");
	                 pw.println("table {\r\n"
	                 		+ "            border-collapse: separate;\r\n"
	                 		+ "            border-spacing: 10px; /* Adjust the spacing value as needed */\r\n"
	                 		+ "        }\r\n"
	                 		+ "");
	                 pw.println("</style>");
	                 pw.println("</head>");
	                 pw.println("<body>");
	                 pw.println(" <div class=\"container\">");
	                 pw.println(" <form action=\"Registration\" method=\"post\">");
	                 // Username is not unique, display an error message
	                 pw.println("<h1>Error: This username is already taken. Please choose another or Login!!!!</h1>");
	                 pw.println("<br>");
	                 pw.println("<a href='register.html'>Go Back</a>");
	                 pw.println("</form>");
	                 pw.println("</div>");
	                 pw.println("</body>");
	                 pw.println("</html>");
	            } else {
	            PreparedStatement pst = con.prepareStatement("INSERT INTO bank_users (username, password, account, balance) VALUES (?, ?, ?,?)");
	            pst.setString(1, username);
	            pst.setString(2, password);
	            pst.setString(3, account);
	            pst.setString(4, balance);

	            int rowsAffected = pst.executeUpdate();

	            if (rowsAffected > 0) {
	            	pw.println("<!DOCTYPE html>");
	                 pw.println("<html>");
	                 pw.println("<head>");
	                 pw.println("<title>UserInfo</title>");
	                 pw.println("<style>");
	                 pw.println("body {\r\n"
	                 		+ "            margin: 0;\r\n"
	                 		+ "            padding: 0;\r\n"
	                 		+ "            font-family: 'Arial', sans-serif;\r\n"
	                 		+ "            background: url('http://surl.li/osjmz') no-repeat center center fixed;\r\n"
	                 		+ "            background-size: cover;\r\n"
	                 		+ "            display: flex;\r\n"
	                 		+ "            align-items: center;\r\n"
	                 		+ "            justify-content: center;\r\n"
	                 		+ "            height: 100vh;\r\n"
	                 		+ "            color: #fff;\r\n"
	                 		+ "        }");
	                 pw.println(" .container {\r\n"
	                 		+ "            background: rgba(0, 0, 0, 0.5);\r\n"
	                 		+ "            padding: 20px;\r\n"
	                 		+ "            border-radius: 10px;\r\n"
	                 		+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);\r\n"
	                 		+ "            text-align: center;\r\n"
	                 		+ "        }");
	                 pw.println("h1 {\r\n"
	                 		+ "            margin-bottom: 20px;\r\n"
	                 		+ "        }");
	                 pw.println(" form {\r\n"
	                 		+ "            display: flex;\r\n"
	                 		+ "            flex-direction: column;\r\n"
	                 		+ "            align-items: center;\r\n"
	                 		+ "        }\r\n"
	                 		+ "");
	                 pw.println("label {\r\n"
	                 		+ "            color: #fff;\r\n"
	                 		+ "            margin-bottom: 10px;\r\n"
	                 		+ "            font-weight: bold;\r\n"
	                 		+ "        }");
	                 pw.println(" input {\r\n"
	                 		+ "            width: 100%;\r\n"
	                 		+ "            padding: 10px;\r\n"
	                 		+ "            margin-bottom: 20px;\r\n"
	                 		+ "            border: 1px solid #fff;\r\n"
	                 		+ "            border-radius: 5px;\r\n"
	                 		+ "            box-sizing: border-box;\r\n"
	                 		+ "        }");
	                 pw.println(" button {\r\n"
	                 		+ "            background-color: #fff;\r\n"
	                 		+ "            color: #000;\r\n"
	                 		+ "            padding: 10px 20px;\r\n"
	                 		+ "            border: none;\r\n"
	                 		+ "            border-radius: 5px;\r\n"
	                 		+ "            font-weight: bold;\r\n"
	                 		+ "            cursor: pointer;\r\n"
	                 		+ "        }");
	                 pw.println("button:hover {\r\n"
	                 		+ "            background-color: #000;\r\n"
	                 		+ "            color: #fff;\r\n"
	                 		+ "        }");
	                 pw.println("a{\r\n"
	                 		+ "         color:white\r\n"
	                 		+ "}");
	                 pw.println("table {\r\n"
	                 		+ "            border-collapse: separate;\r\n"
	                 		+ "            border-spacing: 10px; /* Adjust the spacing value as needed */\r\n"
	                 		+ "        }\r\n"
	                 		+ "");
	                 pw.println("</style>");
	                 pw.println("</head>");
	                 pw.println("<body>");
	                 pw.println(" <div class=\"container\">");
	                 pw.println(" <form action=\"Registration\" method=\"post\">");
	                 // Registration confirmation
	                 pw.println("<h1>Registration Successfull.</h1>");
	                 pw.println("<br>");
	                 pw.println("<a href='login.html'>Login</a>");
	                 pw.println("</form>");
	                 pw.println("</div>");
	                 pw.println("</body>");
	                 pw.println("</html>");
	                 // response.sendRedirect("login.html");
	            } else {
	                response.sendRedirect("register.html");
	            }
	            }
	            con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


