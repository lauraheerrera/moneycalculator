package software.ulpgc.moneycalculator.io;

import com.google.gson.Gson;

public class FixerCurrencyDeserializer implements CurrencyDeserialzer {
    @Override
    public Object deserialize(String json) {
        return new Gson().fromJson(json, CurrencyResponse.class);
    }
}
