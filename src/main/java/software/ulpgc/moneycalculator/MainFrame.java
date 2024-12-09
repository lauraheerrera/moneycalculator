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
    private CurrencyDialog toCurrencyDialog;
    private final static Font FONT = new Font(Font.SERIF, Font.BOLD, 15);
    private final static Font SWAPFONT = new Font(Font.SERIF, Font.BOLD, 40);
    private final static Color orange = new Color(255, 159, 10);
    private final static Color grey = new Color(51, 51, 51);

    public MainFrame() throws HeadlessException {
        this.setTitle("Money Calculator");
        this.setSize(390, 650);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.amountField = new JTextField();
        this.add(createMainPanel(), BorderLayout.CENTER);
        this.add(new JLabel(""), BorderLayout.SOUTH);
    }

    private Component createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(grey);

        panel.add(createNorthPanel());
        panel.add(Box.createVerticalStrut(5));
        panel.add(createCenterPanel());
        panel.add(Box.createVerticalStrut(5));
        panel.add(createSouthPanel());
        panel.add(Box.createVerticalStrut(5));
        panel.add(createResultPanel());

        return panel;
    }

    private Component createNorthPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("AMOUNT:");
        label.setForeground(Color.WHITE);
        label.setFont(FONT);
        panel.add(label);
        panel.setForeground(Color.WHITE);
        panel.setBackground(grey);
        amountField.setColumns(12);
        amountField.setFont(FONT);
        amountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(amountField);
        return panel;
    }

    private Component createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(grey);

        panel.add(createFromDialogPanel());
        panel.add(Box.createVerticalStrut(2));
        panel.add(createSwapButton());
        panel.add(Box.createVerticalStrut(2));
        panel.add(createToDialogPanel());
        panel.add(Box.createVerticalStrut(2));
        panel.add(createNumericPanel());
        return panel;
    }


    private Component createSwapButton(){
        JPanel panel = new JPanel();
        panel.setBackground(grey);
        panel.add(createButton("\u2191 \u2193", "Swap currencies", SWAPFONT));
        return panel;
    }

    private Component createFromDialogPanel() {
        JPanel panel = new JPanel();
        panel.setForeground(Color.WHITE);
        panel.setBackground(grey);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        SwingCurrencyDialog fromDialog = new SwingCurrencyDialog("FROM: ");
        fromDialog.setBackground(grey);
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(amountField, fromDialog);
        moneyDialog.setBackground(grey);
        this.moneyDialog = moneyDialog;
        panel.add(moneyDialog);

        return panel;
    }

    private Component createToDialogPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(grey);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        SwingCurrencyDialog toDialog = new SwingCurrencyDialog("TO: ");
        toDialog.setBackground(grey);
        this.toCurrencyDialog = toDialog;
        panel.add(toDialog);

        return panel;
    }

    private Component createSouthPanel() {
        JPanel panel = new JPanel();
        panel.setForeground(Color.WHITE);
        panel.setBackground(grey);

        JButton convertButton = createButton("CONVERT", "Calculate money", FONT);
        panel.add(convertButton);
        return panel;
    }

    private JButton createButton(String label, String nameCommand, Font font) {
        JButton button = new JButton(label);
        button.setFont(font);
        button.setBackground(orange);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 40));
        button.addActionListener(_ -> {
            try {
                commands.get(nameCommand).execute();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return button;
    }

    private Component createResultPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(grey);
        JLabel resultLabel = new JLabel("RESULT: ");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(FONT);
        panel.add(resultLabel);

        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        display.setForeground(Color.WHITE);
        panel.add(display);

        return panel;
    }

    private Component createNumericPanel() {
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());
        wrapperPanel.setBackground(grey);

        SwingNumericPanel numericPanel = new SwingNumericPanel(amountField);
        numericPanel.setBackground(grey);
        numericPanel.setPreferredSize(new Dimension(210, 280));

        wrapperPanel.add(numericPanel, BorderLayout.CENTER);
        return wrapperPanel;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

    public MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    public CurrencyDialog currencyDialog() {
        return toCurrencyDialog;
    }

    public MoneyDialog moneyDialog() {
        return moneyDialog;
    }
}
