import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cityName;

        do {
            System.out.println("Enter city name (or type 'exit' to quit):");
            cityName = scanner.nextLine();

            if (!cityName.equalsIgnoreCase("exit")) {
                try {
                    String apiKey = "39936d58a67474ea92b745e5441d504b";
                    String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey;

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    String responseBody = response.body().string();

                    JSONObject jsonObject = new JSONObject(responseBody);
                    String weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    String cityNameOutput = jsonObject.getString("name");
                    double temperatureInKelvin = jsonObject.getJSONObject("main").getDouble("temp");
                    double temperatureInCelsius = temperatureInKelvin - 273.15;

                    System.out.println("Weather in " + cityNameOutput + ": " + weatherDescription);
                    System.out.printf("Temperature: %.2f°C\n", temperatureInCelsius);

                } catch (IOException e) {
                    System.out.println("An error occurred: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("City not found, please try again.");
                }
            }

        } while (!cityName.equalsIgnoreCase("exit"));
    }
}
