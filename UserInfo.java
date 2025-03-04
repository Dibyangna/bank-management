

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingManagementSystem", "root", "dibyangna21@");

		PreparedStatement selectStmt = con.prepareStatement("SELECT username, account, balance FROM bank_users WHERE username=?");
        selectStmt.setString(1, username);
        ResultSet rs = selectStmt.executeQuery();
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
        pw.println(" <form action=\"CreditDebit\" method=\"post\">");
        pw.println("<h1>User's Information</h1>");
        pw.println("<br>");
        pw.println("<table  align='center'>");
        pw.println("<tr>");
		pw.println("<th>User Name</th>");
		pw.println("<th>Account Number</th>");
		pw.println("<th>Current Balance</th>");
		pw.println("</tr>");
		while(rs.next()) {
		pw.println("<tr>");

		pw.println("<td>"+rs.getString(1)+"</td>");
		pw.println("<td>"+rs.getString(2)+"</td>");
		pw.println("<td>"+rs.getString(3)+"</td>");

		
		pw.println("</tr>");
		}
		pw.println("</table>");
		pw.println("<br>");
		pw.println("Want to do transaction?");
		pw.println("<br>");
		pw.println("<a href='balance.html'>Click here</a>");
		pw.println("<br>");
		pw.println("<a href=\"logout\">Logout</a>");
        pw.println("</form>");
        pw.println("</div>");
        pw.println("</body>");
        pw.println("</html>");
        
		
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		pw.println("<h1>"+se.getMessage()+"</h2>");
		}
		catch(Exception e) {

		e.printStackTrace();
		pw.println("<h1>"+e.getMessage()+"</h2>");
		}
		
		}
	


	}

	
	

