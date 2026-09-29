import java.net.*;
import java.io.*;
import net.sf.json.*;
import org.apache.commons.lang.exception.*;
import org.apache.commons.io.IOUtils;


public class Main {
    public static void main(String[] args) throws Exception {
        String JSonString = readURL("http://api.geonames.org/weatherIcaoJSON?ICAO=MHTG&formatted=true&username=crdavis4");
        JSONObject x = JSONObject.fromObject(JSonString);

        JSONObject weatherData =(JSONObject)(x.get("weatherObservation"));


        System.out.println("Weather data for " + weatherData.get("stationName"));
        System.out.println("Temperature is " + weatherData.get("temperature") + " degrees (Celsius) with " + weatherData.get("clouds"));

    }
    private static String readURL(String webservice) throws java.net.MalformedURLException, java.io.IOException {
        URL service = new URL(webservice);

        String result = IOUtils.toString(service, "UTF-8");
        return result;
    }
}