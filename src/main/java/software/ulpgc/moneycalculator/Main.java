package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.control.CalculateCommand;
import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.control.SwapCommand;
import software.ulpgc.moneycalculator.io.FixerAPI;
import software.ulpgc.moneycalculator.io.FixerAPIReader;
import software.ulpgc.moneycalculator.io.currency.CurrencyLoader;
import software.ulpgc.moneycalculator.io.currency.FixerCurrencyAdapter;
import software.ulpgc.moneycalculator.io.currency.FixerCurrencyDeserializer;
import software.ulpgc.moneycalculator.io.exchangerate.ExchangeRateLoader;
import software.ulpgc.moneycalculator.io.exchangerate.FixerExchangeRateAdapter;
import software.ulpgc.moneycalculator.io.exchangerate.FixerExchangeRateDeserializer;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.SwingCurrencyDialog;
import software.ulpgc.moneycalculator.view.SwingMoneyDialog;

import java.util.List;

public class Main {
    public static void main(String[] args)  {
        MainFrame mainFrame = new MainFrame();
        List<Currency> currencies = new CurrencyLoader(
                new FixerCurrencyAdapter(),
                new FixerCurrencyDeserializer(),
                new FixerAPIReader(FixerAPI.FIXER_API_CURRENCIES_URL, FixerAPI.FIXER_API_KEY)).get();
        Command calculateCommand = new CalculateCommand(
                mainFrame.moneyDialog().define(currencies),
                mainFrame.currencyDialog().define(currencies),
                new ExchangeRateLoader(
                        new FixerExchangeRateAdapter(),
                        new FixerExchangeRateDeserializer(),
                        new FixerAPIReader(FixerAPI.FIXER_API_EXCHANGE_RATE_URL, FixerAPI.FIXER_API_KEY)
                ),
                mainFrame.moneyDisplay());
        mainFrame.add("Calculate money", calculateCommand);
        Command swapCommand = new SwapCommand(
                ((SwingMoneyDialog) mainFrame.moneyDialog()).getCurrencyDialog(),
                (SwingCurrencyDialog) mainFrame.currencyDialog()
        );
        mainFrame.add("Swap currencies", swapCommand);

        mainFrame.setVisible(true);
    }
}
