package Lab9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Zadanie1 extends JFrame implements ActionListener {

    private JTextField textField;
    private JButton button;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public Zadanie1() throws SQLException{

        this.setSize(900,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        textField = new JTextField("Write");
        button = new JButton("Click");
        textArea = new JTextArea("Text");
        scrollPane = new JScrollPane(textArea);

        this.setLayout(new GridLayout(3,1));
        this.add(textField);
        this.add(button);
        this.add(scrollPane);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(
                            "jdbc:h2:./data/nazwabazy", "sa",
                            "");

                    Statement statement = conn.createStatement();

                    statement.execute(textField.getText());

                    ResultSet rs = statement.getResultSet();

                    ResultSetMetaData md  = rs.getMetaData();

                    for (int ii = 1; ii <= md.getColumnCount(); ii++){
                        //System.out.print(md.getColumnName(ii)+ " | ");
                        textArea.append(md.getColumnName(ii)+ " | ");

                    }
                    textArea.append("\n");

                    while (rs.next()) {
                        for (int ii = 1; ii <= md.getColumnCount(); ii++){
                            //System.out.print( rs.getObject(ii) + " | ");
                            textArea.append( rs.getObject(ii) + " | ");
                        }
                        textArea.append("\n");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    if (conn!= null){
                        try {
                            conn.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

    }




    public static void main(String[] args) throws SQLException {
        Zadanie1 f = new Zadanie1();
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
