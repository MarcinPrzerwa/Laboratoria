package Lab6;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Zadanie1 extends JButton implements Runnable {
    String[] tekst = {"!", "Program", "wyłączy", "się", "za:"};
    int pauza = 2000;
    boolean czynny = true;

    Zadanie1() {
        super();
    }

    Zadanie1(String[] arg1, int arg2) {
        tekst = arg1;
        pauza = arg2;
    }

    @Override
    public void run() {
        int i = 0;
        while (czynny) {
            if (i < tekst.length - 1)
                i++;
            else
                i = 0;
            setText(tekst[i]);
            try {
                Thread.sleep(pauza);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setLayout(new BorderLayout());
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel toppanel = new JPanel();
                toppanel.setLayout(new GridLayout(2, 2));
                frame.add(toppanel, BorderLayout.PAGE_START);
                Zadanie1 b1 = new Zadanie1();
                toppanel.add(b1);

                String[] innyTekst = {"Uwaga", "Attention", "Danger"};

                Zadanie1 b2 = new Zadanie1(innyTekst, 500); //Korzystanie z drugiego konstruktora
                toppanel.add(b2);

                Background back = new Background();
                frame.add(back, BorderLayout.CENTER);

                Etykieta etyk = new Etykieta();
                toppanel.add(etyk);

                ExecutorService exec = Executors.newFixedThreadPool(4);
                // Executors.newSingleThreadExecutor();

                exec.execute(b1);
                exec.execute(b2);
                exec.execute(back);
                exec.execute(etyk);

                exec.shutdown();

                frame.setVisible(true);
            }
        });
    }

    public static class Background extends JPanel implements Runnable {
        boolean b = true;
        //int pauza = 1000;

        public Background() {
        }

        @Override
        public void run() {
            while (b) {
                this.setBackground(new Color(
                        (float) Math.random(), (float) Math.random(), (float) Math.random()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Etykieta extends JLabel implements Runnable {
        String[] tekst = {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"};
        boolean e = true;
        //int pauza = 1000;

        public Etykieta() {
        }

        @Override
        public void run() {

            int i = 0;
            while (e) {
                if (i < tekst.length - 1)
                    i++;
                else
                    i = 0;
                setText(tekst[i]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

