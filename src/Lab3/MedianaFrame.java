package Lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class MedianaFrame extends JFrame {
    private List<Double> liczby = new ArrayList<Double>();
    private JTextField pole;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JButton button2;

    public MedianaFrame(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(400,200);
        this.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,4));

        pole = new JTextField();

        button1 = new JButton("Dodaj");
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double number;
                String text = pole.getText();
                try{
                    number = Double.parseDouble(text);
                    liczby.add(number);
                    label2.setText(label2.getText() + number + ", ");
                }
                catch(NumberFormatException exception){
                    System.out.println("Wrong number format.");
                }

            }

        });

        button2 = new JButton("Mediana");
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(liczby);
                label2.setText("Zbiór liczb: ");
                for(Double x : liczby) {
                    label2.setText(label2.getText() + x + ", ");
                }
                double mediana=0;
                int srodek = liczby.size()/2;

                if(srodek%2==0) {
                    mediana=(liczby.get(srodek-1)+liczby.get(srodek))/2;
                } else {
                    mediana=liczby.get(srodek);
                }

                label1.setText(label1.getText() + mediana);
            }

        });

        label1 = new JLabel("= ");

        topPanel.add(pole);
        topPanel.add(button1);
        topPanel.add(button2);
        topPanel.add(label1);
        this.add(topPanel, BorderLayout.PAGE_START);

        leftPanel = new JPanel();
        label2 = new JLabel("Zbior liczb: ");
        leftPanel.add(label2);
        this.add(leftPanel, BorderLayout.LINE_START);
    }

    public static void main(String[] args) {
        MedianaFrame ramka = new MedianaFrame();
        ramka.setVisible(true);
    }
}
