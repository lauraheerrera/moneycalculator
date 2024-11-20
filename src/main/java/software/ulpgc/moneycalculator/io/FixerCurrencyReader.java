package software.ulpgc.moneycalculator.io;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

import static org.jsoup.Connection.Method.GET;

public class FixerCurrencyReader implements CurrencyReader {

    @Override
    public String read() {
        try {
            return read(FixerAPI.FIXER_API_CURRENCIES_URL + FixerAPI.FIXER_API_KEY);
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
