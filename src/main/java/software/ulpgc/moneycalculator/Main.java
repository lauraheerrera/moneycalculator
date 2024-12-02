package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.control.CalculateCommand;
import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.io.FixerAPI;
import software.ulpgc.moneycalculator.io.FixerAPIReader;
import software.ulpgc.moneycalculator.io.currency.CurrencyLoader;
import software.ulpgc.moneycalculator.io.currency.FixerCurrencyAdapter;
import software.ulpgc.moneycalculator.io.currency.FixerCurrencyDeserializer;
import software.ulpgc.moneycalculator.io.exchangerate.ExchangeRateLoader;
import software.ulpgc.moneycalculator.io.exchangerate.FixerExchangeRateAdapter;
import software.ulpgc.moneycalculator.io.exchangerate.FixerExchangeRateDeserializer;
import software.ulpgc.moneycalculator.model.Currency;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        MainFrame mainFrame = new MainFrame();
        List<Currency> currencies = new CurrencyLoader(
                new FixerCurrencyAdapter(),
                new FixerCurrencyDeserializer(),
                new FixerAPIReader(FixerAPI.FIXER_API_CURRENCIES_URL, FixerAPI.FIXER_API_KEY)).get();
        Command command = new CalculateCommand(
                mainFrame.moneyDialog().define(currencies),
                mainFrame.currencyDialog().define(currencies),
                new ExchangeRateLoader(
                        new FixerExchangeRateAdapter(),
                        new FixerExchangeRateDeserializer(),
                        new FixerAPIReader(FixerAPI.FIXER_API_EXCHANGE_RATE_URL, FixerAPI.FIXER_API_KEY)
                ),
                mainFrame.moneyDisplay());
        mainFrame.add("Calculate money", command);
        mainFrame.setVisible(true);
    }
}
