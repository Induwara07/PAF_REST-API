package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Beans.DoctorBean;
import DbConnect.DBConnection;

public class Doctor {

	public String insertDoctor(DoctorBean doc) {

		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// prepared statement
			String query = "insert into doctor"
					+ "(`DocId`,`Dname`,`RegNo`,`Specialization`,`ContactNo`,`Address`,`Email`,`Hospital`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, doc.getDname());
			preparedStmt.setString(3, doc.getRegNo());
			preparedStmt.setString(4, doc.getSpecialization());
			preparedStmt.setInt(5, doc.getContactNo());
			preparedStmt.setString(6, doc.getAddress());
			preparedStmt.setString(7, doc.getEmail());
			preparedStmt.setString(8, doc.getHospital());

			// execute
			preparedStmt.execute();
			con.close();
			output = "Inserted Doctor successfully";
		} catch (Exception e) {
			output = "Error while inserting doctors";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readDoctors() {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// html table

			output = "<table border=\"1\">" + "<tr>" + "<th>Doctor</th>" + "<th>Reg.No</th>" + "<th>Specialization</th>"
					+ "<th>Contact No</th>" + "<th>Address</th>" + "<th>Email</th>" + "<th>Hospital</th>";

			String query = "select * from doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {

				String DocId = Integer.toString(rs.getInt("DocId"));
				String dname = rs.getString("Dname");
				String dRegNo = rs.getString("RegNo");
				String specialization = rs.getString("Specialization");
				String contactNo = Integer.toString(rs.getInt("ContactNo"));
				String address = rs.getString("Address");
				String email = rs.getString("Email");
				String Hospital = rs.getString("Hospital");

				// Add into the html table
				output += "<tr>" + "<td>" + dname + "</td>";
				output += "<td>" + dRegNo + "</td>";
				output += "<td>" + specialization + "</td>";
				output += "<td>" + contactNo + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + Hospital + "</td></tr>";
			}

			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the doctors.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateDoctor(DoctorBean doc) {

		String output = "";

		try {
			Connection con = DBConnection.connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE doctor SET Dname = ?, RegNo = ?,"
					+ "Specialization = ?,ContactNo = ?, Address = ?,Email = ?,Hospital = ? WHERE DocId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, doc.getDname());
			preparedStmt.setString(2, doc.getRegNo());
			preparedStmt.setString(3, doc.getSpecialization());
			preparedStmt.setInt(4, doc.getContactNo());
			preparedStmt.setString(5, doc.getAddress());
			preparedStmt.setString(6, doc.getEmail());
			preparedStmt.setString(7, doc.getHospital());
			preparedStmt.setInt(8, doc.getDocId());

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String removeDoctor(String DocId) {

		String output = "";
		try {
			Connection con = DBConnection.connect();

			if (con == null) {

				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from doctor where DocId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(DocId));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted Doctor successfully";

		} catch (Exception e) {

			output = "Error while deleting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
