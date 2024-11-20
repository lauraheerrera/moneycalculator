package software.ulpgc.moneycalculator.io;


import java.util.Map;

public record CurrencyResponse(boolean success, Map<String, String> symbols) {
}
