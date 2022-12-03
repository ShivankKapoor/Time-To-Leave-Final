import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Route {
    private static final String API_KEY = "AIzaSyA2lWhHibKy6VhUgDVUhPERV4LiJQA0Uac";
    public static float[][] distances;
    public static float[][] times;
    public static String[] cities = {"", "ECSS,+Engineering+and+Computer+Science+Building,+Franklyn+Jenifer+Drive,+Richardson,+TX", "Callier+Center+Richardson,+Richardson,+TX"};
    public static int n= cities.length;


    //downloading the data
    public static String getData(String source, String destination) throws Exception {
        var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + source + "&destinations=" + destination + "&mode=walking" + "&key=" + API_KEY;
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        //System.out.println(response);
        return response;

    }

    public static long parse(String response,int i,int j){
        long distance = -1L;
        long time = -1L;
        String timeText = "";
        //parsing json data and updating data
        {
            try {
                JSONParser jp = new JSONParser();
                JSONObject jo = (JSONObject) jp.parse(response);
                JSONArray ja = (JSONArray) jo.get("rows");
                jo = (JSONObject) ja.get(0);
                ja = (JSONArray) jo.get("elements");
                jo = (JSONObject) ja.get(0);
                JSONObject je = (JSONObject) jo.get("distance");
                JSONObject jf = (JSONObject) jo.get("duration");
                distance = (long) je.get("value");
                time = (long) jf.get("value"); // this is the value that we want and will be in the unit of seconds
                timeText = (String) jf.get("text");
               // System.out.println("It will take " + timeText + " to get to the Callier Center from ECSW!");
                distances[i][j] = distance;
                times[i][j] = time;
                return time;


            } catch (Exception e) {
                //System.out.println(e + " for " + cities[j]);
            }
        }
        return 0;
    }

    public static long getTime(String origin, String destination) throws Exception {
        cities = new String[2];
        cities[0] = origin;
        cities[1] = destination;
        n=2;
        return run();
    }

    public static String modString(String what){
        what = what.replaceAll(" ","+");
        return what;
    }

    private static long run() throws Exception {
        distances = new float[n][n];
        times = new float[n][n];
        int count=0;
        for (int i = 0; i < n; i++)
            for (int j = 1; j < n; j++) {
                //System.out.print(++count+"/100 ");
                if (i != j) {
                    String response = getData(cities[i], cities[j]);
                    return parse(response,i,j);
                }
                else {
                    times[i][j] = 0;
                    distances[i][j] = 0;
                }
            }
        return 0;
    }

}