package Katze.DroneSimulation.data.api;

import java.util.Date;

import Katze.DroneSimulation.data.TableViewable;

public class DroneDynamic implements TableViewable {
	
	public static final String[] TABLE_HEADERS = {
			"ID", "Timestamp", "Drone", "Speed", "Align Roll", "Control Range", "Align Yaw", "Longitude", "Latitude", "Battery Status", "Last Seen", "Status"
	};
	
	private int id;
	private Date timestamp;
	private String drone;
	private double speed;
	private double alignRoll;
	private double controlRange;
	private double alignYaw;
	private double longitude;
	private double latitude;
	private double batteryStat;
	private Date lastSeen;
	private String stat;
	

	public DroneDynamic(int id, Date timestamp, String drone, double speed, double alignRoll, double controlRange,
			double alignYaw, double longitude, double latitude, double batteryStat, Date lastSeen, String stat) {
		this.id = id;
		this.timestamp = timestamp;
		this.drone = drone;
		this.speed = speed;
		this.alignRoll = alignRoll;
		this.controlRange = controlRange;
		this.alignYaw = alignYaw;
		this.longitude = longitude;
		this.latitude = latitude;
		this.batteryStat = batteryStat;
		this.lastSeen = lastSeen;
		this.stat = stat;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDrone() {
		return drone;
	}
	public void setDrone(String drone) {
		this.drone = drone;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getAlignRoll() {
		return alignRoll;
	}
	public void setAlignRoll(double alignRoll) {
		this.alignRoll = alignRoll;
	}
	public double getControlRange() {
		return controlRange;
	}
	public void setControlRange(double controlRange) {
		this.controlRange = controlRange;
	}
	public double getAlignYaw() {
		return alignYaw;
	}
	public void setAlignYaw(double alignYaw) {
		this.alignYaw = alignYaw;
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
	public double getBatteryStat() {
		return batteryStat;
	}
	public void setBatteryStat(double batteryStat) {
		this.batteryStat = batteryStat;
	}
	public Date getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	

	@Override
	public Object[] getRowData() {
		
		return new Object[] {
				id, timestamp, drone, speed, alignRoll, controlRange, alignYaw, longitude, latitude, batteryStat, lastSeen, stat
		};
	}

}
