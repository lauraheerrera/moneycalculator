package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.io.FixerAPI;
import software.ulpgc.moneycalculator.io.FixerAPIReader;
import software.ulpgc.moneycalculator.io.currency.CurrencyLoader;
import software.ulpgc.moneycalculator.io.currency.FixerCurrencyAdapter;
import software.ulpgc.moneycalculator.io.currency.FixerCurrencyDeserializer;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.view.SwingCurrencyDialog;
import software.ulpgc.moneycalculator.view.SwingMoneyDialog;
import software.ulpgc.moneycalculator.view.SwingMoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CurrencyLoader loader = new CurrencyLoader(
                new FixerCurrencyAdapter(),
                new FixerCurrencyDeserializer(),
                new FixerAPIReader(FixerAPI.FIXER_API_CURRENCIES_URL, FixerAPI.FIXER_API_KEY)
        );

        List<Currency> currencies = loader.get();

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

        JButton showButton = getButton(moneyDialog, toDialog, moneyDisplay);

        frame.add(new JLabel("Enter Amount and Currency:"));
        frame.add(amountField);
        frame.add(moneyDialog);
        frame.add(toDialog);
        frame.add(showButton);
        frame.add(moneyDisplay);

        frame.setSize(600, 300);
        frame.setVisible(true);
    }

    private static JButton getButton(SwingMoneyDialog moneyDialog, SwingCurrencyDialog toDialog, SwingMoneyDisplay moneyDisplay) {
        JButton showButton = new JButton("Show Data");
        showButton.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        showButton.addActionListener(e -> {
            Money money = moneyDialog.get();
            Currency toCurrency = toDialog.get();
            System.out.println("Entered Amount: " + money.amount());
            System.out.println("From Currency: " + money.getCurrencyCode());
            System.out.println("To Currency: " + toCurrency.code());

            moneyDisplay.show(money);
        });
        return showButton;
    }
}
