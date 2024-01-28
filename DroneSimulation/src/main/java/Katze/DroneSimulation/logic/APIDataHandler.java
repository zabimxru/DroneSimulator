package Katze.DroneSimulation.logic;

import java.util.ArrayList;
import java.util.List;

import Katze.DroneSimulation.data.TestData;
import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.data.api.DroneType;
import Katze.DroneSimulation.data.ui.HomepageResultlistData;

public class APIDataHandler {
	// Suchmethode für Homepage
	public static List<HomepageResultlistData> getResultlistData(String searchInput) {
		searchInput = searchInput.toLowerCase(); // searchInput macht sich selbst klein

		// Leere Liste erstellen, die später die Suchergebnisse beinhaltet, die dem
		// Suchinput entsprechen
		List<HomepageResultlistData> resultList = new ArrayList<>();

		Drone[] drones = TestData.DRONE_DATA; // Hier wird später der APICall gemacht, alle Drohnen, die es gibt
		// Alle Drohnendaten in dem Array, das Zeile oben angegeben wird, werden
		// einzelnd durchgegangen und geprüft ob die daten jw. droneType oder serialNr.
		// entsprechen
		for (Drone drone : drones) {
			if (drone.getDronetype().toLowerCase().contains(searchInput)
					|| drone.getSerialnumber().toLowerCase().contains(searchInput)) {
				HomepageResultlistData data = new HomepageResultlistData(drone.getDronetype(), drone.getSerialnumber());
				resultList.add(data);
			}
		}

		return resultList;
	}

	// Suchmethode für DroneDynamicPage
	public static List<DroneDynamic> getDroneDynamicData(String searchInput) {
		searchInput = searchInput.toLowerCase(); // searchInput macht sich selbst klein

		// Leere Liste erstellen, die später die Suchergebnisse beinhaltet, die dem
		// Suchinput entsprechen
		List<DroneDynamic> resultList = new ArrayList<>();

		DroneDynamic[] droneDynamics = TestData.DRONEDYNAMIC_DATA; // Hier wird später der APICall gemacht, alle
																	// Drohnen, die es gibt
		// Alle Drohnendaten in dem Array, das Zeile oben angegeben wird, werden
		// einzelnd durchgegangen und geprüft ob die daten jw. droneType oder serialNr.
		// entsprechen
		for (DroneDynamic droneDynamic : droneDynamics) {
			if (droneDynamic.getTimestamp().toString().toLowerCase().contains(searchInput)) {
				resultList.add(droneDynamic);
			}
		}

		return resultList;
	}

	public static Drone getDroneBySerialNr(String serialNr) {
		Drone[] drones = TestData.DRONE_DATA; // wird als einziges für den APICall verändert
		for (int i = 0; i < drones.length; i++) {
			if (drones[i].getSerialnumber().equals(serialNr)) {
				return drones[i];
			}
		}
		return null;
	}

	
	//1. drone.getDroneType() aufrufen --> zurück kommt ein String was die URL ist blabla/api/dronetype/72
	//2. API aufruf mit dieser URL 
	//3. über den JSON Mapper den Datenstring umwandeln in ein DroneType Object
	//4. return das was rausgekommen ist an Daten
	public static DroneType getTypeFromDrone(Drone drone) {
		return TestData.DRONETYPE_DATA[0]; //anstatt dem hier
	}

}
