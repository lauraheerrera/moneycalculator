package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final JTextField amountField;
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;
    private final static Font FONT = new Font(Font.SERIF, Font.PLAIN, 20);

    public MainFrame() throws HeadlessException {
        this.setTitle("Money Calculator");
        this.setSize(1000, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.amountField = new JTextField();
        this.add(createMainPanel(), BorderLayout.CENTER);
        this.add(new JLabel(""), BorderLayout.SOUTH);
    }

    private Component createMainPanel() {
        JPanel panel = new JPanel();
        panel.add(createNorthPanel(), BorderLayout.NORTH);
        panel.add(createCenterPanel(), BorderLayout.CENTER);
        panel.add(createSouthPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private Component createNorthPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Amount:");
        panel.add(label);
        amountField.setColumns(12);
        amountField.setFont(FONT);
        amountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(amountField);
        return panel;
    }

    private Component createCenterPanel() {
        JPanel panel = new JPanel();
        panel.add(createDialogs(), BorderLayout.NORTH);
        return panel;
    }

    private Component createSouthPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Convert");
        button.setFont(FONT);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        button.setPreferredSize(new Dimension(150, 40));
        button.addActionListener(e -> {
            try {
                commands.get("Calculate money").execute();
                amountField.setText("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(button);

        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        panel.add(display);
        return panel;
    }

    private Component createDialogs() {
        JPanel panel = new JPanel();
        SwingCurrencyDialog fromDialog = new SwingCurrencyDialog("From: ");
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(amountField, fromDialog);
        this.moneyDialog = moneyDialog;
        panel.add(moneyDialog);

        SwingCurrencyDialog toDialog = new SwingCurrencyDialog("To:");
        this.currencyDialog = toDialog;
        panel.add(toDialog);

        return panel;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

    public MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    public CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    public MoneyDialog moneyDialog() {
        return moneyDialog;
    }
}
