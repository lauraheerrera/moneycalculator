package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final JTextField amountField;
    private final CurrencyDialog currencyDialog;
    private final static Color grey = new Color(51, 51, 51);


    public SwingMoneyDialog(JTextField textField, CurrencyDialog currencyDialog) {
        this.setLayout(new FlowLayout());
        this.setForeground(Color.WHITE);
        this.setBackground(grey);
        this.amountField = textField;
        this.currencyDialog = currencyDialog;
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add((Component) currencyDialog.define(currencies));
        return this;
    }

    @Override
    public Money get() {
        return new Money(toDouble(amountField.getText()), currencyDialog.get());
    }

    private double toDouble(String text) {
        return Double.parseDouble(text);
    }
}
