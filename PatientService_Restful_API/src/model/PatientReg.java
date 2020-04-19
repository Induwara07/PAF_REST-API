package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientReg {
	
	private Connection connect() 
	 {  
	 Connection con = null; 
	 
	  try   {     Class.forName("com.mysql.jdbc.Driver");         
	  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcarems", "root", ""); 
	  }   
	  catch (Exception e) 
	  {
		  e.printStackTrace();
	  } 
	 
	  return con;  
	  
	  } 
	
//insert information 	
	public String insertPatientInfo(String name, String dob ,String gender, String homeadd, String homephone, String mobile, String email, String password) 
	 {  
	 String output = ""; 
	 
	  try   {    Connection con = connect(); 
	 
	   if (con == null)   
		   {
			   return "Error while connecting to the database for inserting.";

	       } 
	 
	     String query = " INSERT INTO patient_reg (`patientID`, `name`, `dob`, `gender`, `homeadd`, `homephone`, `mobile`, `email`, `password`) " + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, name); 
			preparedStmt.setString(3, dob);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, homeadd); 
			preparedStmt.setString(6, homephone); 
			preparedStmt.setString(7, mobile);
			preparedStmt.setString(8, email);
			preparedStmt.setString(9, password);
			
			preparedStmt.execute(); 
		    con.close(); 
	 
	         output = "Inserted successfully";
	}   
	catch (Exception e)  
	 {   
	 output = "Error while inserting the user.";    
	 System.err.println(e.getMessage());  
	 } 
	 
	  return output;  
	  } 
	
	
// view information 
	
	public String viewRegPatients() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border=\"1\"><tr><th>Name</th><th>Date of Birth</th><th>Gender</th><th>Home Address</th><th>Home Phone</th><th>Mobile Number</th><th>Email</th><th>Password</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from patient_reg";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String patientID = Integer.toString(rs.getInt("patientID"));
				String name = rs.getString("name");
				String dob = rs.getString("dob");
				String gender = rs.getString("gender");
				String homeadd = rs.getString("homeadd");
				String homephone = rs.getString("homephone");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				
				
				output += "<tr><td>" + name + "</td>";
				output += "<td>" + dob + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + homeadd + "</td>";
				output += "<td>" + homephone + "</td>";
				output += "<td>" + mobile + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + password + "</td>";
				
				
				
				
				
				
				output += "<td><input name=\"btnUpdate\" type=\"button\"  value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"PatientReg.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"  class=\"btn btn-danger\">"
						+ "<input name=\"patientID\" type=\"hidden\" value=\"" + patientID + "\">" + "</form></td></tr>";
			}

			con.close();

			output += "</table>";
			
			
		} catch (Exception e) 
		{
			
			output = "Error while reading the client.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//update information
	
	 public String updatepatientinfo(String ID, String name, String dob, String gender, String homeadd, String homephone, String mobile, String email , String password)  {  
		 String output = ""; 
	 
	  try   {    
		  Connection con = connect(); 
	 
	   if (con == null)   
	   {
		   return "Error while connecting to the database for updating."; 
		   
	   } 
	 
	    String query = "UPDATE patient_reg SET name=?,dob=?,gender=?,homeadd=?,homephone=?,mobile=?,email=?,password=?     WHERE patientID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      preparedStmt.setString(1, name);   
	      preparedStmt.setString(2, dob);   
	      preparedStmt.setString(3, gender);  
	      preparedStmt.setString(4, homeadd); 
	      preparedStmt.setString(5, homephone); 
	      preparedStmt.setString(6, mobile); 
	      preparedStmt.setString(7, email); 
	      preparedStmt.setString(8, password);
	      preparedStmt.setInt(9, Integer.parseInt(ID)); 
	 
	      preparedStmt.execute();   
	      con.close(); 
	 
	      output = "Updated successfully"; 
	   
	  }  
	  catch (Exception e)   
	  {  
		   output = "Error while updating the client.";  
		   System.err.println(e.getMessage()); 
		   
	  } 
	 
	  return output;  } 
	
	 //delete patient info
	 
	 public String deletepatientinfo(String patientID)  {   
		 
		 String output = ""; 
	 
	  try   {   
		  
		  Connection con = connect(); 
	 
	   if (con == null)    
	   
	   {
		   return "Error while connecting to the database for deleting.";
		   
	   } 
	 
	     String query = "delete from patient_reg where patientID=?"; 
	 
	     PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   	preparedStmt.setInt(1, Integer.parseInt(patientID)); 
	 
	     preparedStmt.execute();   
	     con.close(); 
	 
	   output = "Deleted successfully";   } 
	  catch (Exception e)  
	  {    
		  output = "Error while deleting the Patient.";  
		  System.err.println(e.getMessage());  
		  } 
	 
	  return output; 
	  } 
	
	 
	 //login 
	 
	 public String login(String name, String password) {
		 
		 
		 
		 try {
			 
			  Connection con = connect(); 
			  
			  if (con == null)    
				   
			   {
				   return "Error while connecting to the database for login ......";
				   
			   } 
			  String query = "select name, password from patient_reg";
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  
			  preparedStmt.setString(1,name);
			  preparedStmt.setString(2, password);
			  
			  ResultSet rs = preparedStmt.executeQuery();
			  con.close();
			  
			  
			  if(rs.next()) {
				  
				  return "sucess";
			  }
			  else {
				  return "fail";
			  }
			  
			
		 
		 }
		 catch(Exception e) 
		 {
			 return "Error while connecting to the database for login.";
			 
		 }
		
		 
	 }
	 
	 //view profile
	 
	 public String viewProfile(String patientID) {
		 String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				output = "<table border=\"1\"><tr><th>Name</th><th>Date of Birth</th><th>Gender</th><th>Home Address</th><th>Home Phone</th><th>Mobile Number</th><th>Email</th><th>Password</th><th>Update</th><th>Remove</th></tr>";

				String query = "select *  from patient_reg where patientID=' " + patientID + "'" ;
				Statement stmt = con.createStatement();
			    
			     
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next()) {
					String patient = Integer.toString(rs.getInt("patientID"));
					String name = rs.getString("name");
					String dob = rs.getString("dob");
					String gender = rs.getString("gender");
					String homeadd = rs.getString("homeadd");
					String homephone = rs.getString("homephone");
					String mobile = rs.getString("mobile");
					String email = rs.getString("email");
					String password = rs.getString("password");
					
					
					
					
					output += "<tr><td>" + name + "</td>";
					output += "<td>" + dob + "</td>";
					output += "<td>" + gender + "</td>";
					output += "<td>" + homeadd + "</td>";
					output += "<td>" + homephone + "</td>";
					output += "<td>" + mobile + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + password + "</td>";
					
					
					
					
					
					
					output += "<td><input name=\"btnUpdate\" type=\"button\"  value=\"Update\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"PatientReg.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"  class=\"btn btn-danger\">"
							+ "<input name=\"patientID\" type=\"hidden\" value=\"" + patient + "\">" + "</form></td></tr>";
				}
				
				con.close();

				output += "</table>";
				
				
			} catch (Exception e) 
			{
				
				output = "Error while reading the client.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		 
		 
	 

}
