package ui;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import core.Drink;
import core.MixMavenModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RemoteDataAccess implements DataAccess {
    private final URI endpointBaseUri;

    private static final String APPLICATION_JSON = "application/json";

    private static final String ACCEPT_HEADER = "Accept";

    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    private MixMavenModel mixMavenModel;

    private Gson gson;

    public RemoteDataAccess(URI endpointBaseUri) {
        this.endpointBaseUri = endpointBaseUri;
        this.gson = new Gson();
    }

    private String uriParam(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

    private List<Drink> fromJson(String responsebody) {
        Type listType = new TypeToken<List<Drink>>() {}.getType();
        return gson.fromJson(responsebody, listType);
    }

    private String toJson(Drink drink) {
        return gson.toJson(drink);
    }

    private URI drinkUri(String id) {
        return endpointBaseUri.resolve(uriParam(id));
      }

    public final MixMavenModel getModel() {
        HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
        .header(ACCEPT_HEADER, APPLICATION_JSON)
        .GET()
        .build();
        try {
            final HttpResponse<String> response = HttpClient
            .newBuilder()
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString());
            this.mixMavenModel = new MixMavenModel(fromJson(response.body()));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mixMavenModel;
    }

    public List<Drink> getDrinks() {
        return getModel().getDrinks();
    }

    //TODO: return boolean
    public final void deleteDrink(String id) {
        System.out.println(drinkUri(id));
        HttpRequest request = HttpRequest.newBuilder(drinkUri(id))
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .DELETE()
          .build();
        try {
            final HttpResponse<String> response = HttpClient
            .newBuilder()
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO: return boolean
    public final void addDrink(Drink drink) {
        String json = toJson(drink);
        HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
          .POST(BodyPublishers.ofString(json))
          .build();

        try {
            HttpResponse<String> response = HttpClient
            .newBuilder()
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO: return boolean
    public final void editDrink(String id, Drink newDrink) {
        String json = toJson(newDrink);
        HttpRequest request = HttpRequest.newBuilder(drinkUri(id))
          .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
          .PUT(BodyPublishers.ofString(json))
          .build();
          
        try {
            HttpResponse response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFilePath(String string) {
        ;
    }

}
