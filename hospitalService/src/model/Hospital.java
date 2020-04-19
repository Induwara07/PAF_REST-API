package model;

import java.sql.*;

public class Hospital {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcarems", "root", " ");
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return con;
	}

	public String insertHospital(String hcode, String hname, String htp, String haddress, String hdoc, String hdesc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement
			String query = " insert into healthcarems(`hpID`,`hpCode`,`hpName`,`hpTp`,`hpAddress`,`hpDoctors`,`hpDesc`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hcode);
			preparedStmt.setString(3, hname);
			preparedStmt.setString(4, htp);
			preparedStmt.setString(5, haddress);
			preparedStmt.setString(6, hdoc);
			preparedStmt.setString(7, hdesc);

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readHospital() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Hospital Code</th><th>Hospital Name</th><th>Telephone</th><th>Address</th><th>Doctors</th><th>Description</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from healthcarems";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String hpID = Integer.toString(rs.getInt("hpID"));
				String hpCode = rs.getString("hpCode");
				String hpName = rs.getString("hpName");
				String hpTp = rs.getString("hpTp");
				String hpAddress = rs.getString("hpAddress");
				String hpDoc = rs.getString("hpDoc");
				String hpDesc = rs.getString("hpDesc");
// Add into the html table
				output += "<tr><td>" + hpID + "</td>";
				output += "<td>" + hpName + "</td>";
				output += "<td>" + hpTp + "</td>";
				output += "<td>" + hpAddress + "</td>";
				output += "<td>" + hpDoc + "</td>";
				output += "<td>" + hpDesc + "</td>";
				
// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"hpID\" type=\"hidden\" value=\"" + hpID + "\">" + "</form></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateHospital(String ID,String hcode, String hname, String htp, String haddress, String hdoc, String hdesc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE hospital SET hpID=?,hpName=?,hpTp=?,hpAddress=?,hpDoc=?,hpDesc=?WHERE hpID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, hcode);
			preparedStmt.setString(2, hname);
			preparedStmt.setString(3, htp);
			preparedStmt.setString(4, haddress);
			preparedStmt.setString(5, hdoc);
			preparedStmt.setString(6, hdesc);
			preparedStmt.setInt(7, Integer.parseInt(ID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospital(String hpID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from items where hpID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(hpID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}