package com;


import model.Refund;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Refund")
public class RefundService {
	Refund refundObj = new Refund();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readRefund() {
		return refundObj.readRefund();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertRefund(@FormParam("paymentID") String paymentID, @FormParam("amount") String amount,
			@FormParam("date") String date, @FormParam("time") String time, @FormParam("method") String method, @FormParam("adminID") String adminID) {
		String output = refundObj.insertRefund(paymentID, amount, date, time, method, adminID );
		return output;
	}
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRefund(String refundData) {
		// Convert the input string to a JSON object
		JsonObject refundObject = new JsonParser().parse(refundData).getAsJsonObject();
		// Read the values from the JSON object
		String refundID = refundObject.get("refundID").getAsString();
		String paymentID = refundObject.get("paymentID").getAsString();
		String amount = refundObject.get("amount").getAsString();
		String date = refundObject.get("date").getAsString();
		String time = refundObject.get("time").getAsString();
		String method = refundObject.get("method").getAsString();
		String adminID = refundObject.get("adminID").getAsString();
		String output = refundObj.updateRefund ( refundID, paymentID, amount, date, time, method, adminID);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRefund(String refundData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(refundData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String refundID = doc.select("refundID").text();
		String output = refundObj.deleteRefund(refundID);
		return output;
	}

}
