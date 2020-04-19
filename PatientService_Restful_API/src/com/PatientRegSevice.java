package com;

import model.PatientReg;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/PatientReg")

public class PatientRegSevice {
	
	PatientReg patient = new PatientReg();
	
	// view patients details
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatients() {
		
		return patient.viewRegPatients();
		
	}
  //insert profile details
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertpatient(
			@FormParam("name") String name,
			@FormParam("dob") String dob,
			@FormParam("gender") String gender,
			@FormParam("homeadd") String homeadd,
			@FormParam("homephone") String homephone,
			@FormParam("mobile") String mobile,
			@FormParam("email") String email, 
			@FormParam("password") String password )
	{
		String output = patient.insertPatientInfo(name, dob, gender, homeadd,homephone,mobile,email,password);
		return output;
	}
	
	// update profile details
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatepatient(String pdata) {

		JsonObject patientobj = new JsonParser().parse(pdata).getAsJsonObject();

		String patientID = patientobj.get("patientID").getAsString();
		String name = patientobj.get("name").getAsString();
		String dob = patientobj.get("dob").getAsString();
		String gender = patientobj.get("gender").getAsString();
		String homeadd = patientobj.get("homeadd").getAsString();
		String homephone = patientobj.get("homephone").getAsString();
		String mobile = patientobj.get("mobile").getAsString();
		String email = patientobj.get("email").getAsString();
		String password = patientobj.get("password").getAsString();

		
		
		
		String output = patient.updatepatientinfo(patientID, name, dob, gender, homeadd, homephone,mobile, email, password);

		return output;
	}
	
	//delete profile details
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletepatient(String pdata) {
		Document doc = Jsoup.parse(pdata, "", Parser.xmlParser());
		String patientID = doc.select("patientID").text();

		String output = patient.deletepatientinfo(patientID);

		return output;
	}
	
	// login
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String getlogin(String log) {
		
	
		
		Document doc = Jsoup.parse(log, "", Parser.xmlParser());
		String name = doc.select("name").text();
		String password = doc.select("password").text();
		
		
				//JsonObject jsonobj = new JsonParser().parse(log).getAsJsonObject();
				//String name= jsonobj.get("name").getAsString();
			//	String password = jsonobj.get("password").getAsString();
	
		
		
		String output = patient.login(name,password);
		
		return output;
		
		/*if(output.equals("sucess"))
		{
			return "succes";
		}
		else 
			return "fail";
		*/
			
	}
	


	// view profile details
	
	@GET
	@Path("/profile/{patientID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	//@Produces(MediaType.TEXT_HTML)
	public String readprofile(@PathParam("patientID") String patientID) {
	
		return patient.viewProfile(patientID);
		
		//return patient.viewProfile();
		
	}
	

	

}
