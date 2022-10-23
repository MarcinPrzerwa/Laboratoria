package Lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreeButtonFrame extends JFrame {
    private JTextField textField;
    private JButton button;
    private JButton exitButton;
    public ThreeButtonFrame(){
        this.setSize(900,600);
        this.setTitle("Okno");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,3));

        textField = new JTextField("Welcome");
        button = new JButton("Click on me");
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setFont(new Font("",Font.ITALIC,30));
                exitButton.setBackground(Color.RED);
            }
        };
        button.addActionListener(listener);

        exitButton = new JButton("Exit");
        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        exitButton.addActionListener(listener1);

        this.add(textField);
        this.add(button);
        this.add(exitButton);
    }
    public static void main(String[] args) {
      ThreeButtonFrame buttonFrame = new ThreeButtonFrame();
      buttonFrame.setVisible(true);
    }
}
