package Katze.DroneSimulation.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.data.api.DroneType;

public class InformationRetrieval {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public List<DroneType> fetchDroneTypes(String input) {
        try {
            List<DroneType> droneTypesList = new ArrayList<>();

            // Add data to the tableModel by using JSON interfaces
            JSONObject wholeFile = new JSONObject(input);
                //Anstatt so eine Klasse erstellen mit Drohnen und ein Array fängt die Informationen ab und speichert sie in der Klasse ein
            JSONArray jsonFile = wholeFile.getJSONArray("results");

            for (int i = 0; i < jsonFile.length(); i++) {
                JSONObject o = jsonFile.getJSONObject(i);
                if (o.has("id") && o.has("manufacturer")) {
                    //Extract data from JSON and create a DroneTypes instance
                    int id = o.getInt("id");
                    String manufacturer = o.getString("manufacturer");
                    String typename = o.getString("typename");
                    double weight = o.getDouble("weight");
                    double max_speed = o.getDouble("max_speed");
                    double battery_capacity = o.getDouble("battery_capacity");
                    double control_range = o.getDouble("control_range");
                    double max_carriage = o.getDouble("max_carriage");

                    //Create a new DroneTypes object using the constructor
                    DroneType droneType = new DroneType(id, manufacturer, typename, weight, max_speed,
                            battery_capacity, control_range, max_carriage);

                    //Add the object to the list
                    droneTypesList.add(droneType);

                }
            }
            return droneTypesList;
        }
        catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Drone> fetchDrones(String input) {
        try {
            List<Drone> dronesList = new ArrayList<>();

            JSONObject wholeFile = new JSONObject(input);
            JSONArray jsonFile = wholeFile.getJSONArray("results");

            for (int i = 0; i < jsonFile.length(); i++) {
                JSONObject o = jsonFile.getJSONObject(i);
                if (o.has("id") && o.has("dronetype")) {
                    int id = o.getInt("id");
                    String droneTypeUrl = o.getString("dronetype");
                    String created = o.getString("created");
                    String serialnumber = o.getString("serialnumber");
                    int carriageWeight = o.getInt("carriage_weight");
                    String carriageType = o.getString("carriage_type");
                    
                    //Get the ID from the URL
                    //int DTid = extractDroneIDFromURL(droneTypeUrl);
                    //Use the APIAuthentication-class to get JSON data of the Drone Type
                    APIAuthentication test = new APIAuthentication();
                    String test2 = test.fetchData(droneTypeUrl);
                    DroneType test3 = DroneTypeURL(test2);
                    String test4 = test3.getManufacturer() + ": " + test3.getTypename();
                    //Create a new Drones object using the constructor
                    Drone drones = new Drone(id, test3, stringToDate(created), serialnumber, carriageWeight, carriageType);
                    //Add the object to the list
                    dronesList.add(drones);
                }
            }
            return dronesList;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }


    public List<DroneDynamic> fetchDroneDynamics(String input) {
        try {
            List<DroneDynamic> droneDynamicsList = new ArrayList<>();

            JSONObject wholeFile = new JSONObject(input);
            JSONArray jsonFile = wholeFile.getJSONArray("results");

            for (int i = 0; i < jsonFile.length(); i++) {
                JSONObject o = jsonFile.getJSONObject(i);
                if (o.has("drone") && o.has("timestamp")) {
                    
                	
                    String timestamp = o.getString("timestamp");
                    String drone_url = o.getString("drone");
                    int speed = o.getInt("speed");
                    double alignRoll = o.getDouble("align_roll");
                    double alignPitch = o.getDouble("align_pitch");
                    double alignYaw = o.getDouble("align_yaw");
                    double longitude = o.getDouble("longitude");
                    double latitude = o.getDouble("latitude");
                    double batteryStatus = o.getDouble("battery_status");
                    String lastSeen = o.getString("last_seen");
                    String status = o.getString("status");

                    //Create a new Drones object using the constructor
                    DroneDynamic dd = new DroneDynamic(stringToDate(timestamp),	drone_url,
                    		speed,
                    		alignRoll,
                    		alignPitch,
                    		alignYaw,
                    		longitude,
                    		latitude,
                    		batteryStatus,
                    		stringToDate(lastSeen),
                    		status);
                    //Add the object to the list
                    droneDynamicsList.add(dd);
                }
            }
            return droneDynamicsList;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public Drone DroneURL(String parameter) {
        //APIAuthentication template = new APIAuthentication();

        try {
            //Use the ObjectMapper to read the JSON string and convert it into a Drones object
            Drone droneObject = objectMapper.readValue(parameter, Drone.class);
            return droneObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DroneType DroneTypeURL(String parameter) {
        //APIAuthentication template = new APIAuthentication();
        
        try {
            //Use the ObjectMapper to read the JSON string and convert it into a Drones object
            DroneType droneTypeObject = objectMapper.readValue(parameter, DroneType.class);
            return droneTypeObject;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while deserializing DroneType: " + e.getMessage());
            return null;
        }
    }
    
    public static Date stringToDate(String dateString) {
    	dateString = dateString.split("\\+")[0];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static Integer extractDroneIDFromURL(String url) {
    	try {
    		//the format is always the same "http://dronesim.facets-labs.com/api/drones/{droneID}/"
    		String[] parts = url.split("/");
    		return Integer.parseInt(parts[parts.length-2]);
    	} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
    		return null;
    	}
    }
}