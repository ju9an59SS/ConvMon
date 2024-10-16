import java.net.http.HttpResponse;

public class HttpRespuesta {
    public static void manejarRespuesta(HttpResponse<String> response) {
        int codigoEstado = response.statusCode();
        System.out.println("Código de estado: " + codigoEstado);

        if (codigoEstado != 200) {
            System.out.println("Error en la solicitud: " + response.body());
        }
    }
}
