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
			new Drone(71, "Katze", createDateFromString("28.01.2024"), "MIAU-1234", 45, "MEKMEK"),
			new Drone(72, "Hund", createDateFromString("29.01.2024"), "WUFF-1234", 50, "EKEK")
	};
	
	public static final DroneDynamic[] DRONEDYNAMIC_DATA = {
			new DroneDynamic(21,createDateFromString("28.01.2024") ,"Katze",150.00, 1.5, 1.5,
					2.5, 2.5, 2.5, 2.5,createDateFromString("28.01.2024"), "ON"),
			new DroneDynamic(21,createDateFromString("28.01.2024") ,"Katze",150.00, 1.5, 1.5,
					2.5, 2.5, 2.5, 2.5,createDateFromString("28.01.2024"), "ON"),
			new DroneDynamic(21,createDateFromString("28.01.2024") ,"Katze",150.00, 1.5, 1.5,
					2.5, 2.5, 2.5, 2.5,createDateFromString("28.01.2024"), "ON")
			
	};
	
	public static final DroneType[] DRONETYPE_DATA = {
			new DroneType(11, "ABC", "abc", 10.00, 10000, 500, 2.9, 59)
	};
}
