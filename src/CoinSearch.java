import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;

public class CoinSearch {

    Gson gson = new Gson();
    ExchangeResponse exchangeResponse = new ExchangeResponse();
    DecimalFormat df = new DecimalFormat("0.00");

    public void convertCoin(String moedaUser, double quantidade, String moedaConvert){
        try {
            String apiKey = "543ac8c9fbcb888a707419cc";
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaUser.toUpperCase();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            try {
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());
                JsonElement element = JsonParser.parseString(response.body());
                JsonObject objectRoot = element.getAsJsonObject();
                exchangeResponse = gson.fromJson(objectRoot, ExchangeResponse.class);
                double valor = quantidade * exchangeResponse.conversion_rates.getOrDefault(moedaConvert , 0.0);
                gson.newBuilder().setPrettyPrinting().create();

                System.out.println("-------------  -------------");
                System.out.println("Moeda fornecida: " + moedaUser + "\nQuantidade: " + quantidade);
                System.out.println("-------------  -------------");
                System.out.println("Moeda convertida: "+ moedaConvert + "\nQuantidade: " + df.format(valor));

            } catch (IOException | InterruptedException e) {
                System.out.println("Não foi possivel verificar a cotação de preços");
                e.getMessage();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
