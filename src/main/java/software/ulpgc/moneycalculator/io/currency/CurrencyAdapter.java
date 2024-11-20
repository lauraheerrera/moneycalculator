package software.ulpgc.moneycalculator.io.currency;

import software.ulpgc.moneycalculator.model.Currency;

import java.util.List;

public interface CurrencyAdapter {
    List<Currency> adapt(Object o);
}
