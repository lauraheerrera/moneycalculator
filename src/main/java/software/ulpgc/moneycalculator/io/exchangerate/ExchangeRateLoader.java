package software.ulpgc.moneycalculator.io.exchangerate;

import software.ulpgc.moneycalculator.io.FixerAPIReader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;

public class ExchangeRateLoader {
    private final FixerExchangeRateAdapter adapter;
    private final FixerExchangeRateDeserializer deserializer;
    private final FixerAPIReader reader;

    public ExchangeRateLoader(FixerExchangeRateAdapter adapter, FixerExchangeRateDeserializer deserializer, FixerAPIReader reader) {
        this.adapter = adapter;
        this.deserializer = deserializer;
        this.reader = reader;
    }


    public ExchangeRate load(Currency from, Currency to) throws IOException {
        return adapter.adapt(deserializer.deserialize(reader.read()), from, to);
    }
}
