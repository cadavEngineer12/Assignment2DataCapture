
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class we added to our final project game first then copied and set here for this assignment
 * The code below is partially taken from <a href="https://www.delftstack.com/howto/java/call-rest-api-in-java/">...</a>
 * @author Angel Ortiz, Charles Davis
 */
public class API {
    /**
     * THe apiData method is for grabbing the core data from the website listed below
     * @param urlString is the URL that will be used
     * @return the url received as a string
     * @throws IOException in case it can not load the IO
     * @throws URISyntaxException in case there is a problem with the syntax
     */
    public String apiData(String urlString) throws IOException, URISyntaxException{
        URI uri = new URI(urlString);  // https://api.weather.gov/
        URL url = uri.toURL();

        // Creating an HTTP connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to "GET"
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "WeatherApp/1.0");
        connection.setRequestProperty("Accept", "application/ld+json");

        // Collect the response code
        int responseCode = connection.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == connection.HTTP_OK) {
            // Create a reader with the input stream reader.
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            // Create a string buffer
            StringBuffer response = new StringBuffer();

            // Write each of the input line
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // displays output
            return response.toString();
        }
        else {
            throw new IOException("Failed to grab code");
        }
    }

    /**
     * Extracts a value from a JSON string between two tags making it easier to use within my other methods
     * @param json the full JSON response as a String
     * @param startTag the starting tag to search for (e.g., "\"forecast\": \"")
     * @param endTag the ending tag that marks the end of the value (e.g., "\"")
     * @return the substring between startTag and endTag
     */
    private String extractValue(String json, String startTag, String endTag) {
        int start = json.indexOf(startTag) + startTag.length();
        int end = json.indexOf(endTag, start);
        return json.substring(start, end);
    }

    /**
     * This method is for grabbing the forecast of Radford Virginia.
     * @return API data from the forecast Url
     * @throws IOException to handle error cases
     * @throws URISyntaxException handles syntax exceptions
     */
    public String getRadfordForecast() throws IOException, URISyntaxException {
        String pointsUrl = "https://api.weather.gov/points/37.2296,-80.4137";
        String pointsJson = apiData(pointsUrl);

        // Looks for "forecast": "https://api.weather.gov/gridpoints/RNK/71,98/forecast" as a keyword
        String forecastUrl = extractValue(pointsJson, "\"forecast\": \"", "\"");

        // this is to return the actual forecast
        return apiData(forecastUrl);
    }

    /*
    Methods for the type of data will be handled below
     */
    public String getRain() throws IOException, URISyntaxException {
        String forecastJson = getRadfordForecast();

        int index = forecastJson.indexOf("\"probabilityOfPrecipitation\"");
        int valueStart = forecastJson.indexOf("\"value\": ", index) + 9;
        int valueEnd = forecastJson.indexOf("}", valueStart);
        String value = forecastJson.substring(valueStart, valueEnd).trim();

        if (value.equals("null")) value = "0";
        return "Rain chance: " + value + "%";

    }

}