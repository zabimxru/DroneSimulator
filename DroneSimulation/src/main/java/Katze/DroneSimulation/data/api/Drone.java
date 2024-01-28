package Katze.DroneSimulation.data.api;

import java.util.Date;

public class Drone {
	private int id;
	private String dronetype;
	private Date created;
    private String serialnumber;
    private int carriageWeight;
    private String carriageType;
    
	public Drone(int id, String dronetype, Date created, String serialnumber, int carriageWeight, String carriageType) {
		this.id = id;
		this.dronetype = dronetype;
		this.created = created;
		this.serialnumber = serialnumber;
		this.carriageWeight = carriageWeight;
		this.carriageType = carriageType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDronetype() {
		return dronetype;
	}
	public void setDronetype(String dronetype) {
		this.dronetype = dronetype;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public int getCarriageWeight() {
		return carriageWeight;
	}
	public void setCarriageWeight(int carriageWeight) {
		this.carriageWeight = carriageWeight;
	}
	public String getCarriageType() {
		return carriageType;
	}
	public void setCarriageType(String carriageType) {
		this.carriageType = carriageType;
	}
 

	

}
