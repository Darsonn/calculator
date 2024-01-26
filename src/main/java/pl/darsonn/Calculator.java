package pl.darsonn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private JButton percent;
    private JButton button3;
    private JButton clearButton;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton xButton;
    private JButton a4Button;
    private JButton a1Button;
    private JButton button6;
    private JButton a5Button;
    private JButton a6Button;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton a3Button;
    private JButton a2Button;
    private JButton a0Button;
    private JButton button15;
    private boolean result;
    private boolean bracket;
    private boolean negative;

    public Calculator() {
        result = false;
        bracket = false;
        negative = false;

        ActionListener numbersListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(result) {
                    textField1.setText("");
                    result = false;
                }
                changeFunctionalButtons(true);
                textField1.setText(textField1.getText() + e.getActionCommand());
            }
        };
        a7Button.addActionListener(numbersListener);
        a8Button.addActionListener(numbersListener);
        a9Button.addActionListener(numbersListener);
        a4Button.addActionListener(numbersListener);
        a1Button.addActionListener(numbersListener);
        a5Button.addActionListener(numbersListener);
        a6Button.addActionListener(numbersListener);
        a3Button.addActionListener(numbersListener);
        a2Button.addActionListener(numbersListener);
        a0Button.addActionListener(numbersListener);
        button15.addActionListener(numbersListener);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");

                changeFunctionalButtons(false);
            }
        });
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = true;
                textField1.setText(
                        String.valueOf(
                                MathExpressionEvaluator.evaluateMathExpression(
                                        textField1.getText()
                                )
                        )
                );

                changeFunctionalButtons(false);
            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(result) {
                    textField1.setText("");
                    result = false;
                }
                if(e.getActionCommand() == "()") {
                    if(bracket) {
                        bracket = false;
                        textField1.setText(textField1.getText() + ")");
                    } else {
                        bracket = true;
                        textField1.setText(textField1.getText() + " (");
                    }
                    return;
                }
                textField1.setText(textField1.getText() + " " + e.getActionCommand() + " ");
            }
        };
        button3.addActionListener(listener);
        percent.addActionListener(listener);
        button1.addActionListener(listener);
        xButton.addActionListener(listener);
        button9.addActionListener(listener);
        button10.addActionListener(listener);
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(negative) {
                    negative = false;
                    textField1.setText(textField1.getText() + ")");
                } else {
                    negative = true;
                    textField1.setText(textField1.getText() + " (-");
                }
            }
        });
    }

    private void changeFunctionalButtons(boolean enabled) {
        button3.setEnabled(enabled);
        percent.setEnabled(enabled);
        button1.setEnabled(enabled);
        xButton.setEnabled(enabled);
        button9.setEnabled(enabled);
        button10.setEnabled(enabled);
        button15.setEnabled(enabled);
    }
}
