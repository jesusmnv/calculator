import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

  JFrame frame;
  JTextField textField;
  JButton[] numberButtons = new JButton[10];
  JButton[] functionButtons = new JButton[4];
  JButton addButton, subButton, mulButton, divButton;
  JButton equButton, clrButton;
  JPanel panel;

  boolean isResultDisplayed = false;
  double num1 = 0, num2 = 0, result = 0;
  char operator = 0;

  Calculator() {

    frame = new JFrame("Calculator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 500);
    frame.setLayout(null);

    textField = new JTextField();
    textField.setBounds(20, 20, 350, 50);
    textField.setEditable(false);

    addButton = new JButton("+");
    subButton = new JButton("-");
    mulButton = new JButton("*");
    divButton = new JButton("/");
    equButton = new JButton("=");
    clrButton = new JButton("C");

    functionButtons[0] = addButton;
    functionButtons[1] = subButton;
    functionButtons[2] = mulButton;
    functionButtons[3] = divButton;

    for (int i = 0; i < 4; i++) {
      functionButtons[i].addActionListener(this);
      functionButtons[i].setFocusable(false);
      functionButtons[i].setPreferredSize(new Dimension(80, 80));
    }

    for (int i = 0; i < 10; i++) {
      numberButtons[i] = new JButton(String.valueOf(i));
      numberButtons[i].addActionListener(this);
      numberButtons[i].setFocusable(false);
      numberButtons[i].setPreferredSize(new Dimension(80, 80));
    }

    equButton.addActionListener(this);
    equButton.setFocusable(false);

    clrButton.addActionListener(this);
    clrButton.setFocusable(false);

    panel = new JPanel();
    panel.setBounds(20, 90, 350, 350);
    panel.setLayout(new GridLayout(4, 4, 10, 10));

    panel.add(numberButtons[1]);
    panel.add(numberButtons[2]);
    panel.add(numberButtons[3]);
    panel.add(addButton);
    panel.add(numberButtons[4]);
    panel.add(numberButtons[5]);
    panel.add(numberButtons[6]);
    panel.add(subButton);
    panel.add(numberButtons[7]);
    panel.add(numberButtons[8]);
    panel.add(numberButtons[9]);
    panel.add(mulButton);
    panel.add(clrButton);
    panel.add(numberButtons[0]);
    panel.add(equButton);
    panel.add(divButton);

    frame.add(panel);
    frame.add(textField);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    for (int i = 0; i < 10; i++) {
      if (e.getSource() == numberButtons[i]) {
        if (isResultDisplayed || hasOperator(textField.getText())) {
          textField.setText("");
          isResultDisplayed = false;
        }
        textField.setText(textField.getText().concat(String.valueOf(i)));
      }
    }
    if (e.getSource() == addButton) {
      if (textField.getText().isEmpty()) {
        num2 = 0;
      }
      num1 = Double.parseDouble(textField.getText());
      operator = '+';
      textField.setText("+");
    }
    if (e.getSource() == subButton) {
      if (textField.getText().isEmpty()) {
        num2 = 0;
      }
      num1 = Double.parseDouble(textField.getText());
      operator = '-';
      textField.setText("-");
    }
    if (e.getSource() == mulButton) {
      if (textField.getText().isEmpty()) {
        num2 = 0;
      }
      num1 = Double.parseDouble(textField.getText());
      operator = '*';
      textField.setText("*");
    }
    if (e.getSource() == divButton) {
      if (textField.getText().isEmpty()) {
        num2 = 0;
      }
      num1 = Double.parseDouble(textField.getText());
      operator = '/';
      textField.setText("/");
    }

    if (e.getSource() == equButton) {
      System.out.println(textField.getText());
      System.out.println(hasOperator(textField.getText()));
      System.out.println(num1);
      System.out.println(num2);
      if (textField.getText().isEmpty() || hasOperator(textField.getText())) {
        num2 = 0;
      }
      calculate();
      textField.setText(String.valueOf(result));
      num1 = 0;
      operator = 0;
      isResultDisplayed = true;
    }

    if (e.getSource() == clrButton) {
      textField.setText("");
    }
  }

  private boolean hasOperator(String text) {
    return text.contains("+") || text.contains("-") ||
        text.contains("*") || text.contains("/");
  }

  private void calculate() {
    if (operator != 0) {
      try {
        String text = textField.getText().replaceAll("[^0-9]", "");
        if (text.isEmpty()) {
          num2 = 0;
        } else {
          num2 = Double.parseDouble(text);
        }
        switch (operator) {
          case '+':
            result = num1 + num2;
            break;
          case '-':
            result = num1 - num2;
            break;
          case '*':
            result = num1 * num2;
            break;
          case '/':
            result = num1 / num2;
            break;
        }
        num1 = result;
      } catch (NumberFormatException ex) {
        textField.setText("Error");
      }
    } else {
      num1 = Double.parseDouble(textField.getText());
    }
  }

  public static void main(String[] args) {

    Calculator calc = new Calculator();
  }

}