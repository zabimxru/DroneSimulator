package Katze.DroneSimulation.data.api;

import java.util.Date;

public class Drone {
	private int id;
	private DroneType dronetype;
	private Date created;
    private String serialnumber;
    private int carriageWeight;
    private String carriageType;
    
	public Drone(int id, DroneType dronetype, Date date, String serialnumber, int carriageWeight, String carriageType) {
		this.id = id;
		this.dronetype = dronetype;
		this.created = date;
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
	public DroneType getDronetype() {
		return dronetype;
	}
	public void setDronetype(DroneType dronetype) {
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
