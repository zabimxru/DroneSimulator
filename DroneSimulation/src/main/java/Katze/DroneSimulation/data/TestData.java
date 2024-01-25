package Katze.DroneSimulation.data;

import java.util.Date;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.data.api.DroneType;
import Katze.DroneSimulation.data.ui.DroneDynamicsResultListData;
import Katze.DroneSimulation.data.ui.HomepageResultlistData;

public class TestData {
	public static final Drone[] DRONE_DATA = {
			
	};
	
	public static final DroneDynamic[] DRONEDYNAMIC_DATA = {
			
	};
	
	public static final DroneType[] DRONETYPE_DATA = {
			
	};
	
	public static final HomepageResultlistData[] HOMEPAGERESULTLISTDATA_DATA = {
			new HomepageResultlistData("Katze", "MIAU-1234"),
			new HomepageResultlistData("Hund", "WUFF-1223"),
			new HomepageResultlistData("Katze", "MIAU-1234"),
			new HomepageResultlistData("Hund", "WUFF-1223"),
			new HomepageResultlistData("Katze", "MIAU-1234"),
			new HomepageResultlistData("Hund", "WUFF-1223")
	};
	
    public static final DroneDynamicsResultListData[] DRONEDYNAMICS_DATA = {
            new DroneDynamicsResultListData(new Date(), 30, 0.5, 0.75, 12.345, 67.890, 90, "ON"),
            new DroneDynamicsResultListData(new Date(), 29, 0.4, 6.54, 11.111, 54.231, 74, "OF")
        };
	
	public static final String SERIAL_NUMBER = "MIAU-1234";
	public static final String DRONE_TYPE = "Katze";
	
}
