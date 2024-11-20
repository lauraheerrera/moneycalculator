package software.ulpgc.moneycalculator.io.exchangerate;

import java.util.Map;

public record ExchangeRateResponse(boolean success, Map<String, Double> rates){
}
