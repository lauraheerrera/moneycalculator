package software.ulpgc.moneycalculator.io.exchangerate;


import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.time.LocalDate;


public class FixerExchangeRateAdapter implements ExchangeRateAdapter{
    @Override
    public ExchangeRate adapt(Object object, Currency from, Currency to) {
        return parse((ExchangeRateResponse) object, from, to);
    }

    private ExchangeRate parse(ExchangeRateResponse exchangeRateResponse, Currency from, Currency to) {
        Double rate = exchangeRateResponse.rates().get(to.code());
        if (rate == null) {
            throw new IllegalArgumentException("No exchange rate available for " + to.code());
        }
        return new ExchangeRate(from, to, rate, LocalDate.now());
    }
}
