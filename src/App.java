import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {

    public static void main(String[] args) throws Exception {

        // Criar conexão HTTP e bustar os top 250 filmes

        String url = "https://api.mocki.io/v2/549a5d8b"; // URL da API com 250 filmes
        URI rota = URI.create(url);
        HttpClient client = HttpClient.newHttpClient(); // criando Client
        HttpRequest request = HttpRequest.newBuilder(rota).GET().build(); 
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String corpo = response.body();

        // pegar só os dados que interessam (Titulo, imagem e avaliação)

        var parser = new JsonParser(); 
        List<Map<String, String>> listaDeFilmes = parser.parse(corpo);

        // Manipular e exibir os dados

        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get( "title"));
            System.out.println(filme.get( "image"));
            System.out.println(filme.get( "imDbRating"));
            System.out.println();
        }
    }
}
