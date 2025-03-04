import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreditDebit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
       public CreditDebit() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingManagementSystem", "root", "dibyangna21@");

            // Retrieve current balance
            PreparedStatement selectStmt = con.prepareStatement("SELECT balance FROM bank_users WHERE username=?");
            selectStmt.setString(1, username);
            ResultSet rs = selectStmt.executeQuery();

            BigDecimal currentBalance = new BigDecimal(0);
            if (rs.next()) {
                currentBalance = rs.getBigDecimal("balance");
            }

            // Update balance
            PreparedStatement updateStmt = con.prepareStatement("UPDATE bank_users SET balance=? WHERE username=?");
            if ("Credit".equals(request.getParameter("credit"))) {
                currentBalance = currentBalance.add(amount);
            } else {
                currentBalance = currentBalance.subtract(amount);
            }
            updateStmt.setBigDecimal(1, currentBalance);
            updateStmt.setString(2, username);
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                // Update successful
                request.setAttribute("balance", currentBalance);
                response.sendRedirect("final.html");
            } else {
                // Update failed
                response.sendRedirect("balance.html");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	}


