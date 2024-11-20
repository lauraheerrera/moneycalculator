package software.ulpgc.moneycalculator.io.currency;

import com.google.gson.Gson;
import software.ulpgc.moneycalculator.io.Deserializer;

public class FixerCurrencyDeserializer implements Deserializer {
    @Override
    public Object deserialize(String json) {
        return new Gson().fromJson(json, CurrencyResponse.class);
    }
}
