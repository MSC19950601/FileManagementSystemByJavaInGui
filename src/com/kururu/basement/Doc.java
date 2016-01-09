package com.kururu.basement;
import java.sql.Timestamp;
public class Doc{
	private String ID;
	private String creator;
	private Timestamp timestamp;
	private String description;
	private String filename;
	private String location;
	
	public Doc(String ID, String creator, Timestamp timestamp, String description, String filename, String location) {
		super();
		this.ID = ID;
		this.creator = creator;
		this.timestamp = timestamp;
		this.description = description;
		this.filename = filename;
		this.location = location;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location){
		this.location = location;
	}
}