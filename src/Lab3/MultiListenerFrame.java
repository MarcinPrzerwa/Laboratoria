package Lab3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiListenerFrame extends JFrame implements ActionListener {

    private JPanel topPanel, bottomPanel, leftPanel, rightPanel, centerPanel;
    private JSlider slider;
    private JButton button;
    private JLabel label, label1;
    private JRadioButton radioButton1, radioButton2, radioButton3;
    private JTextField textField;

    static final int SLIDER_MIN = 0;
    static final int SLIDER_MAX = 100;
    static final int SLIDER_INIT = 0;

    private String selectedPanel = "center";

    public MultiListenerFrame(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(900,600);
        this.setLayout(new BorderLayout());

        //Top Panel
        topPanel = new JPanel();
        slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
        slider.setPreferredSize(new Dimension(400,50));
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new SliderChangeListener());

        topPanel.add(slider);
        this.add(topPanel, BorderLayout.PAGE_START);
        //*******//

        //Left Panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(4,1));
        label = new JLabel("Wybierz Panel");

        radioButton1 = new JRadioButton("Gorny");
        radioButton1.addActionListener(this);
        radioButton1.setActionCommand("top");

        radioButton2 = new JRadioButton("Srodkowy");
        radioButton2.addActionListener(this);
        radioButton2.setActionCommand("center");

        radioButton3 = new JRadioButton("Dolny");
        radioButton3.addActionListener(this);
        radioButton3.setActionCommand("bottom");

        leftPanel.add(label);
        leftPanel.add(radioButton1);
        leftPanel.add(radioButton2);
        leftPanel.add(radioButton3);
        this.add(leftPanel, BorderLayout.LINE_START);
        //********//

        //Right Panel
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(8,1));
        label1 = new JLabel("Wartosc");
        textField = new JTextField("0");
        textField.setSize(100, 25);

        rightPanel.add(label1);
        rightPanel.add(textField);
        this.add(rightPanel, BorderLayout.LINE_END);
        //********//


        //Center Panel
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);

        this.add(centerPanel, BorderLayout.CENTER);
        //*******//

        //Bottom Panel
        bottomPanel = new JPanel();
        button = new JButton("Wybierz kolor");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog
                        (null, "Wybierz kolor", Color.white);
                if (selectedPanel == "top")
                    topPanel.setBackground(newColor);
                if (selectedPanel == "center")
                    centerPanel.setBackground(newColor);
                if (selectedPanel == "bottom")
                    bottomPanel.setBackground(newColor);
            }
        });

        bottomPanel.add(button);
        this.add(bottomPanel, BorderLayout.PAGE_END);
        //********//
    }

    public static void main(String[] args){
        MultiListenerFrame frame = new MultiListenerFrame();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "top") {
            selectedPanel = "top";
        } else if (e.getActionCommand() == "center") {
            selectedPanel = "center";
        } else if (e.getActionCommand() == "bottom") {
            selectedPanel = "bottom";
        }
    }

    public class SliderChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            String value = String.format("%d", slider.getValue());
            textField.setText(value);
        }

    }
}
