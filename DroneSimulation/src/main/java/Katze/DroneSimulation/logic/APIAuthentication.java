package Katze.DroneSimulation.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class APIAuthentication {
    //private static final String API_URL = "https://dronesim.facets-labs.com/api/";
    private static final String TOKEN = "25d3818e0d0fb9288a1be8158fa58ecd4efc8ef9";

    public static String fetchData(String endpoint) {
        try {
            //Setup URL for ApI request
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Getter methode
            connection.setRequestMethod("GET");

            //Auth. header with token
            connection.setRequestProperty("Authorization", "Token " + TOKEN);
            //Respnse code
            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK) {
                //Read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                //Process teh JSON response as needed
                System.out.println(response.toString());
                return response.toString();
            } else {
                //Error handling
                System.out.println("Error: " + responseCode);
            }
            //Cut Connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}