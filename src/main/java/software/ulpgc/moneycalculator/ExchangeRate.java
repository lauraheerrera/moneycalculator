package software.ulpgc.moneycalculator;

import java.time.LocalDate;

public record ExchangeRate(Currency from, Currency to, double rate, LocalDate date) {
}
