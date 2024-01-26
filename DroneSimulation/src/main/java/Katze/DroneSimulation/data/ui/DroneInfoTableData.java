package Katze.DroneSimulation.data.ui;

import java.util.Date;

public class DroneInfoTableData {
	private Date created;
	private float carriageWeight;
	private String carriageType;
	
	public DroneInfoTableData(Date created, float carriageWeight, String carriageType) {
		this.created = created;
		this.carriageWeight = carriageWeight;
		this.carriageType = carriageType;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public float getCarriageWeight() {
		return carriageWeight;
	}
	public void setCarriageWeight(float carriageWeight) {
		this.carriageWeight = carriageWeight;
	}
	public String getCarriageType() {
		return carriageType;
	}
	public void setCarriageType(String carriageType) {
		this.carriageType = carriageType;
	}
}
