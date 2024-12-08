package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    public static final Font FONT = new Font(Font.SERIF, Font.PLAIN, 15);
    private JComboBox<Currency> currencySelector;
    private final String label;

    public SwingCurrencyDialog(String label) {
        this.setLayout(new FlowLayout());
        this.label = label;
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        JLabel text = new JLabel(label);
        text.setForeground(Color.WHITE);
        text.setFont(FONT);
        add(text);
        add(createCurrencySelector(currencies));
        return this;
    }

    private Component createCurrencySelector(List<Currency> currencies) {
        JComboBox<Currency> selector = new JComboBox<>();
        selector.setFont(FONT);
        for (Currency currency : currencies) {
            selector.addItem(currency);
        }

        Currency defaultCurrency = null;
        if (Objects.equals(label, "FROM: ")) {
            defaultCurrency = new Currency("EUR", "Euro");
        } else if (Objects.equals(label, "TO: ")) {
            defaultCurrency = new Currency("USD", "United States Dollar");
        }

        if (defaultCurrency != null) {
            selector.setSelectedItem(defaultCurrency);
        }

        this.currencySelector = selector;
        return selector;
    }

    @Override
    public Currency get() {
        return currencySelector.getItemAt(currencySelector.getSelectedIndex());
    }
}
