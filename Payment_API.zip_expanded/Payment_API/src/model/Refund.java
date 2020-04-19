package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Refund {

	// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/healthcarems?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
						"root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}
		
		public String insertRefund(String paymentID, String amount, String date, String time, String method, String adminID) {
			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into refund (`refundID`,`paymentID`,`amount`,`date`,`time`,`method`,`adminID`)"
						+ " values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, paymentID);
				preparedStmt.setDouble(3, Double.parseDouble(amount));
				preparedStmt.setString(4, date);
				preparedStmt.setString(5, time);
				preparedStmt.setString(6, method);
				preparedStmt.setString(7, adminID);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted Refund successfully";
			} catch (Exception e) {
				output = "Error while inserting the Refund.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String readRefund() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th> Payment ID </th><th>Amount</th><th>Date</th><th>Time</th><th>Method</th><th>Admin ID</th></tr>";
				String query = "select * from refund";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String refundID = Integer.toString(rs.getInt("refundID"));
					String paymentID = Integer.toString(rs.getInt("paymentID"));
					String amount = Double.toString(rs.getDouble("amount"));
					String date = rs.getString("date");
					String time = rs.getString("time");
					String method = rs.getString("method");
					String adminID = Integer.toString(rs.getInt("adminID"));
					
					// Add into the html table
					output += "<tr><td>" + paymentID + "</td>";
					output += "<td>" + amount + "</td>";
					output += "<td>" + date + "</td>";
					output += "<td>" + time + "</td>";
					output += "<td>" + method + "</td>";
					output += "<td>" + adminID + "</td>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the Refunds Table.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String updateRefund( String refundID, String paymentID, String amount, String date, String time, String method,String adminID) {
			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE refund SET  paymentID=?, amount=? , date=?, time=?,method=?, adminID=? WHERE refundID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(paymentID));
				preparedStmt.setDouble(2, Double.parseDouble(amount));
				preparedStmt.setString(3, date);
				preparedStmt.setString(4, time);
				preparedStmt.setString(5, method);
				preparedStmt.setInt(6, Integer.parseInt(adminID));
				preparedStmt.setInt(7, Integer.parseInt(refundID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Refund Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the Refund Amount.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String deleteRefund(String refundID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from refund where refundID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(refundID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Refund Data Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the Refund Data.";
				System.err.println(e.getMessage());
			}
			return output;
		}

}
