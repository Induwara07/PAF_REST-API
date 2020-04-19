package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService {
	Payment paymentObj = new Payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return paymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("type") String type, @FormParam("dateAndTime") String dateAndTime,
			@FormParam("amount") String amount, @FormParam("paymentStatus") String paymentStatus) {
		String output = paymentObj.insertPayment(type, dateAndTime, amount, paymentStatus);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		// Read the values from the JSON object
		String paymentID = paymentObject.get("paymentID").getAsString();
		String type = paymentObject.get("type").getAsString();
		String dateAndTime = paymentObject.get("dateAndTime").getAsString();
		String amount = paymentObject.get("amount").getAsString();
		String paymentStatus = paymentObject.get("paymentStatus").getAsString();
		String output = paymentObj.updatePayment(paymentID, type, dateAndTime, amount, paymentStatus);
		return output;
	}
	
}
