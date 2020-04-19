package Beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@XmlRootElement
public class DoctorBean {

	// Variables
	int DocId;
	private String Dname;
	private String RegNo;
	private String Specialization;
	private int ContactNo;
	private String Address;
	private String Email;
	private String Hospital;

	public DoctorBean() {

	}

	public DoctorBean(String doc) {

		JsonObject doctorObject = new JsonParser().parse(doc).getAsJsonObject();

		if (doctorObject.get("DocId") != null) {
			this.DocId = doctorObject.get("DocId").getAsInt();
		}

		this.Dname = doctorObject.get("Dname").getAsString();
		this.RegNo = doctorObject.get("RegNo").getAsString();
		this.Specialization = doctorObject.get("Specialization").getAsString();
		this.ContactNo = doctorObject.get("ContactNo").getAsInt();
		this.Address = doctorObject.get("Address").getAsString();
		this.Email = doctorObject.get("Email").getAsString();
		this.Hospital = doctorObject.get("Hospital").getAsString();

	}

	public DoctorBean(int doctorid, String doctorName, String Dregno, String specialization, int contactNo,
			String address, String email, String hospitalname) {

		this.DocId = doctorid;
		this.Dname = doctorName;
		this.RegNo = Dregno;
		this.Specialization = specialization;
		this.ContactNo = contactNo;
		this.Address = address;
		this.Email = email;
		this.Hospital = hospitalname;

	}

	public DoctorBean(String doctorName, String Dregno, String specialization, int contactNo, String address,
			String email, String hospitalname) {

		this.Dname = doctorName;
		this.RegNo = Dregno;
		this.Specialization = specialization;
		this.ContactNo = contactNo;
		this.Address = address;
		this.Email = email;
		this.Hospital = hospitalname;

	}

	public int getDocId() {
		return DocId;
	}

	public void setDocId(int doctorId) {
		DocId = doctorId;
	}

	public String getDname() {
		return Dname;
	}

	public void setDname(String dname) {
		Dname = dname;
	}

	public String getRegNo() {
		return RegNo;
	}

	public void setRegNo(String dRegNo) {
		RegNo = dRegNo;
	}

	public String getSpecialization() {
		return Specialization;
	}

	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}

	public int getContactNo() {
		return ContactNo;
	}

	public void setContactNo(int contactNo) {
		ContactNo = contactNo;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getHospital() {
		return Hospital;
	}

	public void setHospital(String Hospital) {
		Hospital = Hospital;
	}

}
