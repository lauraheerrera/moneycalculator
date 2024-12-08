package software.ulpgc.moneycalculator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingNumericPanel extends JPanel implements ActionListener {
    private final JTextField textField;
    private final String[] keys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "RESET"};
    private final static Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
    private final static Color lightgrey = new Color(78, 78, 78);
    private final static Color orange = new Color(255, 159, 10);

    public SwingNumericPanel(JTextField textField) {
        this.setLayout(new GridLayout(4, 3, 10, 10));
        this.setForeground(Color.white);
        this.setBackground(Color.BLACK);
        this.textField = textField;
        textField.setFont(font);

        createButtons();
    }

    private void createButtons() {
        for (String key : keys) {
            JButton b = new JButton(key) {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getModel().isPressed()) g.setColor(orange);
                    else g.setColor(getBackground());
                    g.fillOval(0, 0, getWidth(), getHeight());

                    super.paintComponent(g);
                }

                @Override
                public void updateUI() {
                    super.updateUI();
                    setBorder(BorderFactory.createEmptyBorder());
                    setOpaque(false);
                }
            };

            int size = 30;
            b.setFont(font);
            b.setPreferredSize(new Dimension(size, size));
            b.setBackground(lightgrey);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            if (key.equals("RESET")) b.setBackground(orange);
            b.addActionListener(this);

            this.add(b);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (b.getText().equals("RESET")) textField.setText("");
        else textField.setText(textField.getText() + b.getText());
    }
}
