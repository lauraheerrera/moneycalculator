package software.ulpgc.moneycalculator.io;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

import static org.jsoup.Connection.Method.GET;

public class FixerAPIReader implements Reader {

    private final String apiURL;

    private final String apiKey;

    public FixerAPIReader(String apiURL, String apiKey) {
        this.apiURL = apiURL;
        this.apiKey = apiKey;
    }
    @Override
    public String read() {
        try {
            return read(apiURL+apiKey);
        } catch (IOException e) {
            throw new RuntimeException("Error al obtener datos de la API", e);
        }
    }

    private String read(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url)
                .ignoreContentType(true)
                .method(GET)
                .execute();

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener datos de la API: Código " + response.statusCode());
        }

        return response.body();
    }
}
