package Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Beans.DoctorBean;
import Model.Doctor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctors")
public class DoctorService {

	Doctor doctor = new Doctor();

	// Read Doctor List
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctors() {
		return doctor.readDoctors();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(String doctorData) {

		DoctorBean doctorb = new DoctorBean(doctorData);

		String output = doctor.insertDoctor(doctorb);
		return output;

	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String Doctor) {

		DoctorBean doc = new DoctorBean(Doctor);

		String output = doctor.updateDoctor(doc);
		return output;

	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removeDoctor(String doctorData) {

		JsonObject DoctorObject = new JsonParser().parse(doctorData).getAsJsonObject();

		String doctorID = DoctorObject.get("DocId").getAsString();
		String output = doctor.removeDoctor(doctorID);

		return output;

	}

}
