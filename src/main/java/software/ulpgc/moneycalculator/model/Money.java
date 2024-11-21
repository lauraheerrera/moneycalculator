package software.ulpgc.moneycalculator.model;

public record Money(double amount, Currency currency) {
    public String getCurrencyCode(){ return currency.code();}
}
