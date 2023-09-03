package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            // API endpoint URL
            String apiUrl = "https://api.nationalize.io/?name=nathaniel";

            // Create a URL object
            URL url = new URL(apiUrl);

            // Create an HTTP connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Get the response code
            int responseCode = conn.getResponseCode();

            if (responseCode == 200)
            {
                // Read the response data
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response

                JSONObject jsonResponse = new JSONObject(response.toString());

                // Access the data in JSON format
                JSONArray countries = jsonResponse.getJSONArray("country");

                for (int i = 0; i < countries.length(); i++) {
                    JSONObject countryData = countries.getJSONObject(i);
                    String countryName = countryData.getString("country_id");
                    double probability = countryData.getDouble("probability");
                    System.out.println("Country: " + countryName + ", Probability: " + probability);
                }
            } else {
                System.out.println("Error Url doesnot exist" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
