package software.ulpgc.moneycalculator.io.exchangerate;


import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.time.LocalDate;
import java.util.Map;


public class FixerExchangeRateAdapter implements ExchangeRateAdapter{
    private final static String BASE_CURRENCY = "EUR";

    @Override
    public ExchangeRate adapt(Object object, Currency from, Currency to) {
        return parse((ExchangeRateResponse) object, from, to);
    }

    private ExchangeRate parse(ExchangeRateResponse exchangeRateResponse, Currency from, Currency to) {
        Map<String, Double> rates = exchangeRateResponse.rates();

        if (from.code().equals(BASE_CURRENCY)){
            return new ExchangeRate(from, to, rates.get(to.code()), LocalDate.now());
        } else if(to.code().equals(BASE_CURRENCY)){
            return new ExchangeRate(from, to, 1/rates.get(from.code()), LocalDate.now());
        } else{
            double fromToTargetRate = getFromToTargetRate(from, to, rates);
            return new ExchangeRate(from, to, fromToTargetRate, LocalDate.now());
        }
    }

    private static double getFromToTargetRate(Currency from, Currency to, Map<String, Double> rates) {
        double fromToBaseRate = 1 / rates.get(from.code());
        double baseToTargetRate = rates.get(to.code());
        return fromToBaseRate * baseToTargetRate;
    }
}