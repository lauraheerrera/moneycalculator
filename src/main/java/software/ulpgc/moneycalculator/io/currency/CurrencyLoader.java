package software.ulpgc.moneycalculator.io.currency;

import software.ulpgc.moneycalculator.io.FixerAPIReader;
import software.ulpgc.moneycalculator.model.Currency;

import java.util.List;

public class CurrencyLoader {
    private final FixerCurrencyAdapter adapter;
    private final FixerCurrencyDeserializer deserializer;
    private final FixerAPIReader reader;

    public CurrencyLoader(FixerCurrencyAdapter adapter, FixerCurrencyDeserializer deserializer, FixerAPIReader reader) {
        this.adapter = adapter;
        this.deserializer = deserializer;
        this.reader = reader;
    }

    public List<Currency> get() {
        return adapter.adapt(deserializer.deserialize(reader.read()));
    }
}
