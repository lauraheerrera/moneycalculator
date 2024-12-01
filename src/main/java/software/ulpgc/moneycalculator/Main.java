package software.ulpgc.moneycalculator;
import software.ulpgc.moneycalculator.control.CalculateCommand;
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
import software.ulpgc.moneycalculator.view.SwingMoneyDisplay;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        CurrencyLoader loader = new CurrencyLoader(
                new FixerCurrencyAdapter(),
                new FixerCurrencyDeserializer(),
                new FixerAPIReader(FixerAPI.FIXER_API_CURRENCIES_URL, FixerAPI.FIXER_API_KEY)
        );
        List<Currency> currencies = loader.get();

        ExchangeRateLoader exchangeRateLoader = new ExchangeRateLoader(
                new FixerExchangeRateAdapter(),
                new FixerExchangeRateDeserializer(),
                new FixerAPIReader(FixerAPI.FIXER_API_EXCHANGE_RATE_URL, FixerAPI.FIXER_API_KEY)
        );

        JFrame frame = new JFrame("Money Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField amountField = new JTextField(10);
        SwingCurrencyDialog fromDialog = new SwingCurrencyDialog("From:");
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(amountField, fromDialog);
        moneyDialog.define(currencies);
        SwingCurrencyDialog toDialog = new SwingCurrencyDialog("To:");
        toDialog.define(currencies);

        SwingMoneyDisplay moneyDisplay = new SwingMoneyDisplay();

        CalculateCommand calculateCommand = new CalculateCommand(
                moneyDialog, toDialog, exchangeRateLoader, moneyDisplay);

        JButton showButton = getButton(calculateCommand);

        frame.add(new JLabel("Enter Amount and Currency:"));
        frame.add(amountField);
        frame.add(moneyDialog);
        frame.add(toDialog);
        frame.add(showButton);
        frame.add(moneyDisplay);
        frame.setSize(600, 300);
        frame.setVisible(true);
    }

    private static JButton getButton(CalculateCommand calculateCommand) {
        JButton showButton = new JButton("Calculate");
        showButton.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        showButton.addActionListener(e -> {
            try {
                calculateCommand.execute();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        return showButton;
    }

}