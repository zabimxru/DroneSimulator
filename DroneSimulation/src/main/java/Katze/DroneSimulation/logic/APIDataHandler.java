package Katze.DroneSimulation.logic;

import java.util.ArrayList;
import java.util.List;

import Katze.DroneSimulation.data.TestData;
import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.data.api.DroneType;
import Katze.DroneSimulation.data.ui.HomepageResultlistData;

public class APIDataHandler {
	private static List<DroneType> droneTypes;
	private static List<Drone> drones;
	private static List<DroneDynamic> droneDynamics;
	
	// Suchmethode für Homepage
	public static List<HomepageResultlistData> getResultlistData(String searchInput) {
		searchInput = searchInput.toLowerCase(); // searchInput macht sich selbst klein
		fetchData();
		// Leere Liste erstellen, die später die Suchergebnisse beinhaltet, die dem
		// Suchinput entsprechen
		List<HomepageResultlistData> resultList = new ArrayList<>();
		InformationRetrieval test = new InformationRetrieval();
		
            for (Drone drone : drones) {
    			if ((drone.getDronetype().getManufacturer() + ": " + drone.getDronetype().getTypename()).toLowerCase().contains(searchInput)
    					|| drone.getSerialnumber().toLowerCase().contains(searchInput)) {
    				
    				HomepageResultlistData data = new HomepageResultlistData(drone.getDronetype().getManufacturer() + ": " + drone.getDronetype().getTypename(), drone.getSerialnumber());
    				resultList.add(data);
    			}
    		}
            // Hier wird später der APICall gemacht, alle Drohnen, die es gibt
    		// Alle Drohnendaten in dem Array, das Zeile oben angegeben wird, werden
    		// einzelnd durchgegangen und geprüft ob die daten jw. droneType oder serialNr.
    		// entsprechen
            return resultList;
        }
	
	public static void fetchData() {
		
		if(droneTypes != null) return;
		
    	APIAuthentication api = new APIAuthentication();
        
        int offset = 0;
        int limit = 3000;

        // Fetch DroneTypes data
        String urlString = "http://dronesim.facets-labs.com/api/dronetypes/?format=json&limit=" + limit + "&offset=" + offset;
        String jsonString = api.fetchData(urlString);
        if (jsonString != null) {
            InformationRetrieval informationRetrieval = new InformationRetrieval();
            droneTypes = informationRetrieval.fetchDroneTypes(jsonString);
        }

        // Fetch Drones data
        String urlString2 = "http://dronesim.facets-labs.com/api/drones/?format=json&limit=" + limit + "&offset=" + offset;
        String jsonString2 = api.fetchData(urlString2);
        if (jsonString2 != null) {
            InformationRetrieval informationRetrieval2 = new InformationRetrieval();
            drones = informationRetrieval2.fetchDrones(jsonString2);
        }

        // Fetch DroneDynamics data
        String urlString3 = "http://dronesim.facets-labs.com/api/dronedynamics/?format=json&limit=" + limit + "&offset=" + offset;
        String jsonString3 = api.fetchData(urlString3);
        if (jsonString3 != null) {
            InformationRetrieval informationRetrieval3 = new InformationRetrieval();
            droneDynamics = informationRetrieval3.fetchDroneDynamics(jsonString3);
        }
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
		 
		for (int i = 0; i < drones.size(); i++) {
			if (drones.get(i).getSerialnumber().equals(serialNr)) {
				return drones.get(i);
			}
		}
		return null;
	}

	
	//1. drone.getDroneType() aufrufen --> zurück kommt ein String was die URL ist blabla/api/dronetype/72
	//2. API aufruf mit dieser URL 
	//3. über den JSON Mapper den Datenstring umwandeln in ein DroneType Object
	//4. return das was rausgekommen ist an Daten
	public static DroneType getTypeFromDrone(Drone drone) {
		return drone.getDronetype(); //anstatt dem hier
	}

}
