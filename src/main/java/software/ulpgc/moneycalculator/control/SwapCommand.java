package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.view.SwingCurrencyDialog;

import java.io.IOException;

public class SwapCommand implements Command {
    private final SwingCurrencyDialog fromDialog;
    private final SwingCurrencyDialog toDialog;

    public SwapCommand(SwingCurrencyDialog fromDialog, SwingCurrencyDialog toDialog) {
        this.fromDialog = fromDialog;
        this.toDialog = toDialog;
    }

    @Override
    public void execute() throws IOException {
        var fromCurrency = fromDialog.get();
        var toCurrency = toDialog.get();

        fromDialog.getCurrencySelector().setSelectedItem(toCurrency);
        toDialog.getCurrencySelector().setSelectedItem(fromCurrency);
    }
}
