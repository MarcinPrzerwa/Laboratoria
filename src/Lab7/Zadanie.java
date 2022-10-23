package Lab7;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;



public class Zadanie extends JFrame implements ActionListener {

    private JEditorPane editorPane;
    private JMenuBar menu;
    private JMenuItem opening;
    private JMenuItem saving;
    private JMenuItem changefont;
    private JMenu menuuu;
    private Font myfont;
    private boolean selected;



    public Zadanie(){

        editorPane = new JEditorPane();
        selected=false;
        myfont = new Font("Arial", Font.PLAIN, 14);
        editorPane.setFont(myfont);
        menu = new JMenuBar();
        menuuu = new JMenu("Menu");
        opening = new JMenuItem("Otwieranie pliku");
        saving = new JMenuItem("Zapisywanie pliku");
        //changefont = new JMenuItem("Zmiana czcionki");
        menuuu.add(opening);
        menuuu.add(saving);
        //menuuu.add(changefont);
        menu.add(menuuu);
        this.setJMenuBar(menu);
        this.setLayout(new BorderLayout());
        JRadioButton fontchange=new JRadioButton("Zmiana czcionki");
        JPanel bottomPanel=new JPanel();
        bottomPanel.setSize(480,500);
        this.add(editorPane, BorderLayout.CENTER);
        this.add(fontchange,BorderLayout.PAGE_START);


        opening.setActionCommand("open");
        opening.addActionListener(this);
        saving.setActionCommand("save");
        saving.addActionListener(this);
        fontchange.setActionCommand("chfont");
        fontchange.addActionListener(this);
        fontchange.setSelected(false);


    }


    @Override
    public void actionPerformed(ActionEvent arg) {
        if(arg.getActionCommand()=="open"){
            File dirPath = new File(System.getProperty("user.dir"));
            JFileChooser jchooser = new JFileChooser(dirPath);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text files only", "txt");
            int returnVal = jchooser.showSaveDialog(null);

            if (returnVal==JFileChooser.APPROVE_OPTION)
            {
                try{

                    File inputFile= jchooser.getSelectedFile();
                    InputStreamReader isr = new InputStreamReader(
                            new FileInputStream(inputFile),
                            Charset.forName("UTF-8").newDecoder()
                    );

                    Scanner s = new Scanner(isr).useDelimiter("\\A");
                    String result = s.hasNext() ? s.next() : "";

                    result= result.replaceAll("ó","?");
                    result= result.replaceAll("u","?");
                    result= result.replaceAll("ch","?");
                    result= result.replaceAll("h","?");
                    result= result.replaceAll("rz","?");
                    result= result.replaceAll("ż","?");
                    result= result.replaceAll("Ż","?");
                    result= result.replaceAll("ł","?");
                    result= result.replaceAll("ą","?");
                    result= result.replaceAll("ę","?");
                    result= result.replaceAll("ś","?");
                    result= result.replaceAll("ć","?");
                    result= result.replaceAll("ź","?");
                    result= result.replaceAll("dż","?");

                    editorPane.setText(result);
                    isr.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(arg.getActionCommand()=="save"){
            File dirPath = new File(System.getProperty("user.dir"));
            JFileChooser jchooser = new JFileChooser(dirPath);
            int returnVal = jchooser.showSaveDialog(null);
            if (returnVal==JFileChooser.APPROVE_OPTION) {
                try {
                    File outputFile = jchooser.getSelectedFile();
                    OutputStreamWriter osw = new OutputStreamWriter(
                            new FileOutputStream(outputFile),
                            Charset.forName("UTF-8").newEncoder()

                    );
                    osw.write(editorPane.getText());
                    osw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if(arg.getActionCommand()=="chfont"){
            if(!selected){
                Font newfont = new Font("Comic Sans MS", Font.BOLD, 18);
                editorPane.setFont(newfont);
                selected=true;
                editorPane.repaint();
            }else{
                editorPane.setFont(myfont);
                selected=false;
                editorPane.repaint();
            }

        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Zadanie f = new Zadanie();
                f.setTitle("Dyktando");
                f.setSize(480, 600);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

    }
}

