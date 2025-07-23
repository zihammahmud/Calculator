import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CompleteCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton eqButton, clrButton, decButton, bkspButton;
    private JButton sqrtButton, sqrButton, plusMinusButton;

    private double firstNumber = 0;
    private String operation = "";
    private boolean newInput = true;

    public CompleteCalculator() {

        setTitle("Complete Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(240, 240, 240));
        add(display, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));


        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(new Color(220, 220, 255));
        }


        addButton = createOperationButton("+", new Color(255, 200, 200));
        subButton = createOperationButton("-", new Color(200, 255, 200));
        mulButton = createOperationButton("×", new Color(200, 200, 255));
        divButton = createOperationButton("÷", new Color(255, 255, 200));


        eqButton = createFunctionButton("=", new Color(200, 255, 255));
        clrButton = createFunctionButton("C", new Color(255, 200, 255));
        decButton = createFunctionButton(".", new Color(220, 255, 220));
        bkspButton = createFunctionButton("⌫", new Color(255, 220, 220));
        sqrtButton = createFunctionButton("√", new Color(220, 220, 255));
        sqrButton = createFunctionButton("x²", new Color(255, 220, 255));
        plusMinusButton = createFunctionButton("±", new Color(220, 255, 255));


        buttonPanel.add(clrButton);
        buttonPanel.add(bkspButton);
        buttonPanel.add(sqrtButton);
        buttonPanel.add(divButton);

        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(mulButton);

        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(subButton);

        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(addButton);

        buttonPanel.add(plusMinusButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(decButton);
        buttonPanel.add(eqButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createOperationButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.addActionListener(this);
        button.setBackground(color);
        return button;
    }

    private JButton createFunctionButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.addActionListener(this);
        button.setBackground(color);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentText = display.getText();


        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (newInput) {
                    display.setText(String.valueOf(i));
                    newInput = false;
                } else {
                    display.setText(currentText + i);
                }
                return;
            }
        }


        if (e.getSource() == clrButton) {
            display.setText("");
            firstNumber = 0;
            operation = "";
            newInput = true;
            return;
        }


        if (e.getSource() == bkspButton) {
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
            return;
        }


        if (e.getSource() == decButton) {
            if (newInput) {
                display.setText("0.");
                newInput = false;
            } else if (!currentText.contains(".")) {
                display.setText(currentText + ".");
            }
            return;
        }


        if (e.getSource() == sqrtButton) {
            if (!currentText.isEmpty()) {
                double num = Double.parseDouble(currentText);
                if (num >= 0) {
                    display.setText(String.valueOf(Math.sqrt(num)));
                } else {
                    display.setText("Error");
                }
                newInput = true;
            }
            return;
        }


        if (e.getSource() == sqrButton) {
            if (!currentText.isEmpty()) {
                double num = Double.parseDouble(currentText);
                display.setText(String.valueOf(num * num));
                newInput = true;
            }
            return;
        }


        if (e.getSource() == plusMinusButton) {
            if (!currentText.isEmpty()) {
                double num = Double.parseDouble(currentText);
                display.setText(String.valueOf(-num));
            }
            return;
        }


        if (e.getSource() == addButton || e.getSource() == subButton ||
                e.getSource() == mulButton || e.getSource() == divButton) {

            if (!currentText.isEmpty()) {
                firstNumber = Double.parseDouble(currentText);
            }

            if (e.getSource() == addButton) operation = "+";
            else if (e.getSource() == subButton) operation = "-";
            else if (e.getSource() == mulButton) operation = "*";
            else if (e.getSource() == divButton) operation = "/";

            newInput = true;
            return;
        }


        if (e.getSource() == eqButton) {
            if (!currentText.isEmpty() && !operation.isEmpty()) {
                double secondNumber = Double.parseDouble(currentText);
                double result = 0;

                switch (operation) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            display.setText("Error");
                            operation = "";
                            newInput = true;
                            return;
                        }
                        break;
                }

                display.setText(String.valueOf(result));
                operation = "";
                newInput = true;
            }
        }
    }

    public static void main(String[] args) {
        new CompleteCalculator();
    }
}