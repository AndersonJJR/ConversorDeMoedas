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
import java.util.ArrayList;
import java.util.List;

public class CoinSearch {

    Gson gson = new Gson();
    ExchangeResponse exchangeResponse = new ExchangeResponse();
    DecimalFormat df = new DecimalFormat("0.00");
    double valor;
    List<String> moedaFornecida = new ArrayList<>();
    List<String> moedaConvertida = new ArrayList<>();
    List<Double> moedaQuantidade = new ArrayList<>();

    public void convertCoin(String moedaUser, double quantidade, String moedaConvert){

        String coinUser = moedaUser.trim().toUpperCase();
        String coinConvert = moedaConvert.trim().toUpperCase();

        String apiKey = "543ac8c9fbcb888a707419cc";
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + coinUser;

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

            if (exchangeResponse.conversion_rates == null) {
                System.out.println("Moeda possuida é invalida!");
                return;
            }

            if (!exchangeResponse.conversion_rates.containsKey(moedaConvert)){
                System.out.println("Moeda a ser convertida é invalida!");
                return;
            }

            valor = quantidade * exchangeResponse.conversion_rates.getOrDefault(coinConvert, 0.0);

            gson.newBuilder().setPrettyPrinting().create();

            System.out.println("\n------------- CONVERSÃO -------------");
            System.out.println("Moeda fornecida: " + moedaUser + "\nQuantidade: " + quantidade);

            System.out.println("\n------------- CONVERSÃO -------------");
            System.out.println("Moeda convertida: "+ moedaConvert + "\nQuantidade: " + df.format(valor));

            moedaFornecida.add(moedaUser);
            moedaConvertida.add(moedaConvert);
            moedaQuantidade.add(quantidade);

        } catch (IOException | InterruptedException e) {
            System.out.println("Não foi possivel verificar a cotação de preços");
        }
    }

    public void mostrarLista() {

        int indice = 1;
        if (!moedaFornecida.isEmpty()) {
            for (int i = 0; i < moedaFornecida.size(); i++) {
                System.out.println( (indice + i) + " Foi-se convertido a moeda " + moedaFornecida.get(i) +
                        " com o valor " + moedaQuantidade.get(i) +
                        " para a moeda " + moedaConvertida.get(i));

            }
        } else {
            System.out.println("Não existem converções feitas.");
        }
    }
}
