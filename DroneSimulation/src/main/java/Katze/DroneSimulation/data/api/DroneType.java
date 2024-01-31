package Katze.DroneSimulation.data.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DroneType {
	private int id;
	private String manufacturer;
	private String typename;
	private double weight;
	@JsonProperty("max_speed")
	private double max_speed;
	@JsonProperty("battery_capacity")
	private double battery_capacity;
	@JsonProperty("control_range")
	private double control_range;
	@JsonProperty("max_carriage")
	private double max_carriage;
	
	//Default constructor (no-args constructor)
	public DroneType() {}
	
	public DroneType(
			@JsonProperty("id") int id,
			@JsonProperty("manufacturer") String manufacturer,
			@JsonProperty("typename") String typename, 
			@JsonProperty("weight") double weight, 
			@JsonProperty("maxSpeed") double maxSpeed,
			@JsonProperty("batteryCap") double batteryCap, 
			@JsonProperty("controlRange") double controlRange, 
			@JsonProperty("maxCarriage") double maxCarriage) {
		this.id = id;
		this.manufacturer = manufacturer;
		this.typename = typename;
		this.weight = weight;
		this.max_speed = maxSpeed;
		this.battery_capacity = batteryCap;
		this.control_range = controlRange;
		this.max_carriage = maxCarriage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getMaxSpeed() {
		return max_speed;
	}
	public void setMaxSpeed(double maxSpeed) {
		this.max_speed = maxSpeed;
	}

	public double getBatteryCap() {
		return battery_capacity;
	}
	public void setBatteryCap(double batteryCap) {
		this.battery_capacity = batteryCap;
	}
	public double getControlRange() {
		return control_range;
	}
	public void setControlRange(double controlRange) {
		this.control_range = controlRange;
	}
	public double getMaxCarriage() {
		return max_carriage;
	}
	public void setMaxCarriage(double maxCarriage) {
		this.max_carriage = maxCarriage;
	}
	
	
}