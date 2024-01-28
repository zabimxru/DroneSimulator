package Katze.DroneSimulation.logic;

import Katze.DroneSimulation.data.TestData;
import Katze.DroneSimulation.data.api.Drone;

public class APIDataHandler {
	public static Drone getDroneBySerialNr(String serialNr) {
		Drone[] drones = TestData.DRONE_DATA; //wird als einziges für den APICall verändert
		for(int i = 0; i < drones.length; i++) {
			if (drones[i].getSerialnumber().equals(serialNr)) {
				return drones[i];
			}
		}
		return null;
	}
}
