import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String apiKey = "39936d58a67474ea92b745e5441d504b\n";
        String city = "Kyiv";

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            JSONObject json = new JSONObject(response.body().string());
            double temperature = json.getJSONObject("main").getDouble("temp");
            System.out.println("Temperature in " + city + " is " + temperature + " °C");
        }
    }
}
