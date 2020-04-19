package model;

import java.sql.*;

public class Payment {

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

	public String insertPayment(String type, String dateAndTime, String amount, String paymentStatus) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment (`paymentID`,`type`,`dateAndTime`,`amount`,`paymentStatus`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, dateAndTime);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setString(5, paymentStatus);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted Payment successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th> Payment Type </th><th>Date and Time</th><th>Amount</th><th>Payment Status</th></tr>";
			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String type = rs.getString("type");
				String dateAndTime = rs.getString("dateAndTime");
				String amount = Double.toString(rs.getDouble("amount"));
				String paymentStatus = rs.getString("paymentStatus");
				// Add into the html table
				output += "<tr><td>" + type + "</td>";
				output += "<td>" + dateAndTime + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + paymentStatus + "</td>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String paymentID, String type, String dateAndTime, String amount,
			String paymentStatus) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payment SET type=?,dateAndTime=?,amount=?,paymentStatus=? WHERE paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, type);
			preparedStmt.setString(2, dateAndTime);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setString(4, paymentStatus);
			preparedStmt.setInt(5, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Payment Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
