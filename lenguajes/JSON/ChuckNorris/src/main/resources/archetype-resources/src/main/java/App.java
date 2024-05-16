package src.main.java;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class ChuckNorrisJoke {
    private String icon_url;
    private String id;
    private String url;
    private String value;
}

public class App {
    public static void main(String[] args) {
        String chuckNorrisAPI = "https://api.chucknorris.io/jokes/random";

        try {
            // Crear la URL y abrir la conexión
            URL url = new URL(chuckNorrisAPI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Utilizar Gson para parsear el JSON y guardar en la clase ChuckNorrisJoke
            Gson gson = new Gson();
            ChuckNorrisJoke joke = gson.fromJson(response.toString(), ChuckNorrisJoke.class);

            // Imprimir el chiste obtenido
            System.out.println("Chiste de Chuck Norris:");
            System.out.println(joke);

            // Cerrar la conexión
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}