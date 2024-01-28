package Katze.DroneSimulation.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.data.api.DroneType;

public class TestData {
	
	private static Date createDateFromString(String string) {
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			return format.parse(string);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static final Drone[] DRONE_DATA = {
			//int id, String dronetype, Date created, String serialnumber, int carriageWeight, String carriageType
			new Drone(71, "Katze", createDateFromString("28.01.2024"), "MIAU-1234", 45, "MEKMEK"),
			new Drone(72, "Hund", createDateFromString("29.01.2024"), "WUFF-1234", 50, "EKEK")
	};
	
	public static final DroneDynamic[] DRONEDYNAMIC_DATA = {
			
	};
	
	public static final DroneType[] DRONETYPE_DATA = {
//			int id, String manufacturer, String typename, double weight, double maxSpeed, double minSpeed,
//			double batteryCap, double controlRange, double maxCarriage
			new DroneType(11, "ABC", "abc", 10.00, 10000, 0, 500, 2.9, 59)
	};
	

//    public static final DroneDynamicsResultListData[] DRONEDYNAMICS_DATA = {
//            new DroneDynamicsResultListData(new Date(), 30, 0.5, 0.75, 12.345, 67.890, 90, "ON"),
//            new DroneDynamicsResultListData(new Date(), 29, 0.4, 6.54, 11.111, 54.231, 74, "OF")
//        };
	
	public static final String SERIAL_NUMBER = "MIAU-1234";
	public static final String DRONE_TYPE = "Katze";
	
}
