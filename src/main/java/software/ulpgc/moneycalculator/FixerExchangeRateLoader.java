package software.ulpgc.moneycalculator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

public class FixerExchangeRateLoader implements ExchangeRateLoader{
    @Override
    public ExchangeRate load(Currency from, Currency to) throws IOException {
        URL url = new URL("https://api.exchangeratesapi.io/v1/latest?access_key=28ebf6583decc179ffbeb4e3c20c8289");
        try (InputStream is = url.openStream()) {
            String json = new String(is.readAllBytes());
            return extractExchangeRate(from, to, json);
        }
    }

    private ExchangeRate extractExchangeRate(Currency from, Currency to, String json) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        double rate = jsonObject.getAsJsonObject("rates").get(to.code()).getAsDouble();
        return new ExchangeRate(from, to, rate, LocalDate.now());
    }
}
