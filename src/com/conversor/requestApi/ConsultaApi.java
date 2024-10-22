package com.conversor.requestApi;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


public class ConsultaApi {

    // API Key para acceder al servicio de Exchange Rate API
    private static final String API_KEY = "44478fb1b01a700a7db45caa";

    // URL base de la API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Cliente HTTP reutilizable para hacer las solicitudes
    private static final HttpClient client = HttpClient.newHttpClient();

    // Instancia de Gson para procesar el JSON de la respuesta
    private static final Gson gson = new Gson();

    /**
     * Obtiene la tasa de conversión entre dos monedas usando la API de Exchange Rate.
     *
     * @param from La moneda de origen (ej. USD)
     * @param to La moneda de destino (ej. ARS)
     * @return La tasa de conversión entre las monedas
     */ 
    public static double getConversionRate(String from, String to) {
        // Construye la URL completa para hacer la solicitud a la API
        String url = BASE_URL + API_KEY + "/latest/" + from;
        URI uri = URI.create(url);

        // Crea la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            // Envía la solicitud y obtiene la respuesta en formato de String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear la respuesta JSON a un mapa
            Map<String, Object> responseMap = gson.fromJson(response.body(), Map.class);

            // Verifica si la respuesta contiene las tasas de conversión
            if (!responseMap.containsKey("conversion_rates")) {
                throw new RuntimeException("La respuesta de la API no contiene tasas de conversión.");
            }

            // Obtiene las tasas de conversión
            Map<String, Double> conversionRates = (Map<String, Double>) responseMap.get("conversion_rates");

            // Verifica si la moneda de destino está presente en las tasas de conversión
            if (!conversionRates.containsKey(to)) {
                throw new RuntimeException("No se encontró la tasa de conversión para la moneda: " + to);
            }

            // Retorna la tasa de conversión correspondiente
            return conversionRates.get(to);

        } catch (Exception e) {
            // Manejo de errores si algo falla en la solicitud o el procesamiento
            throw new RuntimeException("Ocurrió un error al obtener la tasa de conversión: " + e.getMessage(), e);
        }
    }
}
