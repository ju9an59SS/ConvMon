import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpCliente {
    private String apiKey;
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    public HttpCliente(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obtenerTasaDeCambio(String monedaBase, String monedaObjetivo) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + apiKey + "/latest/" + monedaBase))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            HttpRespuesta.manejarRespuesta(response);

            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
            JsonObject tasas = jsonResponse.getAsJsonObject("conversion_rates");

            if (tasas == null || !tasas.has(monedaObjetivo)) {
                System.out.println("Tasa de cambio no disponible.");
                return -1;
            }

            return tasas.get(monedaObjetivo).getAsDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public JsonObject obtenerMonedasDisponibles() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + apiKey + "/currencies"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            HttpRespuesta.manejarRespuesta(response);

            Gson gson = new Gson();
            return gson.fromJson(response.body(), JsonObject.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
