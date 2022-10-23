package Lab2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class ThreeShapesPanel extends JPanel {
    Random rand = new Random();
    Color randomColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    Color randomColor2 = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    Color randomColor3 = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(randomColor);
        g.fillRect(70, 50, 150, 100);

        g.setColor(randomColor2);
        g.fillOval(70, 300, 150, 150);

        g.setColor(randomColor3);
        int x[] = {70, 300, 185};
        int y[] = {500, 500, 700};
        int n = 3;
        g.drawPolygon(x, y, n);
        g.fillPolygon(x, y, n);
    }

    public static void main(String[] args) {
        CloseableFrame frame = new CloseableFrame();
        frame.setSize(1000,1000);
        frame.setLayout(new GridLayout(1,2));

        JPanel firstPanel = new JPanel();

        firstPanel.setLayout(new GridLayout(4,1));

        JLabel label = new JLabel("Tekst pisany");
        firstPanel.add(label);

        JButton button1 = new JButton("Przycisk");
        firstPanel.add(button1);

        JRadioButton button3 = new JRadioButton("yes");
        firstPanel.add(button3);

        JCheckBox box = new JCheckBox("Kliknij");
        firstPanel.add(box);

        ThreeShapesPanel secondPanel = new ThreeShapesPanel();
        secondPanel.setBackground(Color.WHITE);

        frame.add(firstPanel);
        frame.add(secondPanel);

        frame.setVisible(true);
    }
}
