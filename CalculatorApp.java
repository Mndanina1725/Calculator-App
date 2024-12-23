import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp {
    private JFrame frame;
    private JTextField textField;
    private JPanel buttonPanel;
    private String operator;
    private double num1, num2, result;

    public CalculatorApp() {
        // Initialize the frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Initialize the text field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);
        frame.add(textField, BorderLayout.NORTH);

        // Initialize the button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Add buttons
        addButtons();

        // Set the frame visibility
        frame.setVisible(true);
    }

    private void addButtons() {
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }
    }

    // Inner class for handling button clicks
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "C": // Clear
                    textField.setText("");
                    num1 = num2 = result = 0;
                    operator = null;
                    break;
                case "=": // Calculate result
                    if (!textField.getText().isEmpty()) {
                        num2 = Double.parseDouble(textField.getText());
                        calculateResult();
                    }
                    break;
                case "+": // Store operator and first number
                case "-":
                case "*":
                case "/":
                    if (!textField.getText().isEmpty()) {
                        num1 = Double.parseDouble(textField.getText());
                        operator = command;
                        textField.setText("");
                    }
                    break;
                default: // Append number or decimal point
                    textField.setText(textField.getText() + command);
            }
        }

        private void calculateResult() {
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num2 != 0 ? num1 / num2 : 0; // Avoid division by zero
                    break;
            }

            // Check if the result is an integer
            if (result == (int) result) {
                textField.setText(String.valueOf((int) result)); // Display as an integer
            } else {
                textField.setText(String.valueOf(result)); // Display as a float
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}
