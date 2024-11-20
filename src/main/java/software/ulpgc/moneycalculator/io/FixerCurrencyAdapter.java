package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FixerCurrencyAdapter implements CurrencyAdapter {
    @Override
    public List<Currency> adapt(Object object) {
        return adapt((CurrencyResponse) object);
    }

    private List<Currency> adapt(CurrencyResponse response){
        List<Currency> currencies = new ArrayList<>();
        if (response.success()) {
            for (Map.Entry<String, String> entry : response.symbols().entrySet()) {
                currencies.add(new Currency(entry.getKey(), entry.getValue()));
            }
        }
        return currencies;
    }
}
