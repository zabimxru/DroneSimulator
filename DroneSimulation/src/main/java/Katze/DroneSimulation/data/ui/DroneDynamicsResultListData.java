package Katze.DroneSimulation.data.ui;

import java.util.Date;

public class DroneDynamicsResultListData implements Comparable<DroneDynamicsResultListData> {
	public DroneDynamicsResultListData(Date timestamp, int speed, double alignmentRoll, double controlRage,
			double longitude, double latitude, int batteryStatus, String status) {
		super();
		this.timestamp = timestamp;
		this.speed = speed;
		this.alignmentRoll = alignmentRoll;
		this.controlRage = controlRage;
		this.longitude = longitude;
		this.latitude = latitude;
		this.batteryStatus = batteryStatus;
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getAlignmentRoll() {
		return alignmentRoll;
	}

	public void setAlignmentRoll(double alignmentRoll) {
		this.alignmentRoll = alignmentRoll;
	}

	public double getControlRage() {
		return controlRage;
	}

	public void setControlRage(double controlRage) {
		this.controlRage = controlRage;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getBatteryStatus() {
		return batteryStatus;
	}

	public void setBatteryStatus(int batteryStatus) {
		this.batteryStatus = batteryStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int compareTo(DroneDynamicsResultListData other) {
		return this.getTimestamp().compareTo(other.getTimestamp());
	}

	private Date timestamp;
	private int speed;
	private double alignmentRoll;
	private double controlRage;
	private double longitude;
	private double latitude;
	private int batteryStatus;
	private String status;

}
