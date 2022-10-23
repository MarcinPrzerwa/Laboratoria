package Lab10;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 * based on example from  www.codejava.net
 */

public class Zadanie1 extends JFrame implements LineListener {

    String audioFilePath = "";
    Clip audioClip = null;
    File audioFile = null;
    AudioInputStream audioStream = null;
    private JButton playbutton,pausebutton, choosebutton;


    boolean playCompleted = false;

    Zadanie1(){
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.setLayout(new BorderLayout());
        playbutton = new JButton("Play");
        choosebutton = new JButton("Choose music file");
        pausebutton = new JButton("Pause");

        this.add(playbutton, BorderLayout.WEST);
        playbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioClip.start();
            }
        });

        this.add(choosebutton, BorderLayout.CENTER);
        choosebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Choose file");
                int result = chooser.showDialog(null,"Choose");
                String path = chooser.getSelectedFile().getAbsolutePath();
                System.out.println(path);
                play(path);

            }
        });

        this.add(pausebutton, BorderLayout.EAST);
        pausebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioClip.stop();
            }
        });

        setVisible(true);
    }


    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    void play(String audioFilePath) {

        try {
            audioFile = new File(audioFilePath);
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);

            /**
             *  Play the audio clip in a new thread not to block the GUI.
             *  It helps in this case, but is not really necessary.
             */
            Thread thread = new Thread(new Runnable() {

                public void run() {
                    //audioClip.start();
                    while(!playCompleted){

                    }
                    //audioClip.close();

                    try {
                        audioStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            });
            thread.start();



        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error playing the audio file.");
            e1.printStackTrace();
        }

    }

    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }

    }

    public static void main(String[] args) {

        final String inFileName = "res//1-welcome.wav";

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                Zadanie1 p = new Zadanie1();
                p.play(inFileName);

            }
        });
    }

}
