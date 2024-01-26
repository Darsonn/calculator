package pl.darsonn;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Image icon = Toolkit.getDefaultToolkit().createImage("src/main/resources/icon.png");

        JFrame jFrame = new JFrame("Calculator");
        jFrame.setContentPane(new Calculator().panel1);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setIconImage(icon);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}