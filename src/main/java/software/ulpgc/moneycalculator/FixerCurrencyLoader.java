package software.ulpgc.moneycalculator;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FixerCurrencyLoader implements CurrencyLoader{

    @Override
    public List<Currency> load() {
        try{
            return buildCurrencyList(loadJson());
        } catch (IOException e){
            return Collections.emptyList();
        }
    }

    private List<Currency> buildCurrencyList(String json) {
        ArrayList<Currency> currencies = new ArrayList<>();
        Map<String, JsonElement> symbols = new Gson().fromJson(json, JsonObject.class).get("symbols").getAsJsonObject().asMap();
        for (String symbol : symbols.keySet()) {
            currencies.add(new Currency(symbol, symbols.get(symbol).getAsString()));
        }
        return currencies;
    }

    private String loadJson() throws IOException {
        URL url = new URL("http://data.fixer.io/api/symbols?access_key=28ebf6583decc179ffbeb4e3c20c8289");
        try (InputStream is = url.openStream()){
            return new String(is.readAllBytes());
        }
    }
}
