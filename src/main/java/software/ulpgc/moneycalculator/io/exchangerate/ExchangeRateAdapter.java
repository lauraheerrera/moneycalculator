package software.ulpgc.moneycalculator.io.exchangerate;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

public interface ExchangeRateAdapter {
    ExchangeRate adapt(Object object, Currency from, Currency to);
}
