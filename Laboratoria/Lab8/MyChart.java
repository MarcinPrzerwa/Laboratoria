package Lab8;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyChart extends JFrame implements ActionListener {
    public XYSeries series1;
    public XYSeriesCollection dataset1;
    public JButton button1, button2,button3,button4,button5;
    public JFreeChart lineGraph;
    public Random rand;
    public ChartPanel chartPanel;



    public MyChart(){
        this.setSize(900,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        series1 = new XYSeries("series", true, true);
        dataset1 = new XYSeriesCollection(series1);
        rand=new Random();

        lineGraph = ChartFactory.createXYLineChart
                ("Title",  // Title
                        "X-Avis",           // X-Axis label
                        "Y-Avis",           // Y-Axis label
                        dataset1,          // Dataset
                        PlotOrientation.VERTICAL,        //Plot orientation
                        true,                //show legend
                        true,                // Show tooltips
                        false               //url show
                );
        chartPanel = new ChartPanel(lineGraph);
//        for (int i = 0; i < random.nextInt(128) + 128 / 2; i++) {
//            series1.add(i, Math.sin(i));
//        }
        button1 = new JButton("Sinus ");
        button1.setActionCommand("sinus");
        button1.addActionListener(this);
        button2 = new JButton("Cosinus ");
        button2.setActionCommand("cosinus");
        button2.addActionListener(this);
        button3 = new JButton("Log");
        button3.setActionCommand("log");
        button3.addActionListener(this);
        button4 = new JButton("Sqrt");
        button4.setActionCommand("sqrt");
        button4.addActionListener(this);
        button5 = new JButton("Linear");
        button5.setActionCommand("linear");
        button5.addActionListener(this);


        JPanel content = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        content.add(chartPanel);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        this.add(content, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("sinus")) {
            series1.clear();
            lineGraph.setTitle("SInus");
            for (int i = 0; i < 100; i++) {
                double x = (i)/10.0 ;
                double y = Math.sin(x);
                this.series1.addOrUpdate(x, y);
            }
        }else if (e.getActionCommand().equals("cosinus")) {
            series1.clear();
            lineGraph.setTitle("Cosinus");
            for (int i = 0; i < 100; i++) {
                double x = (i)/10.0 ;
                double y = Math.cos(x);
                this.series1.addOrUpdate(x, y);
            }
        }else if(e.getActionCommand().equals("log")){

            series1.clear();
            lineGraph.setTitle("Logarytm");
            for (int i = 0; i < 100; i++) {
                if(i==0) continue;
                double x = i;
                double y = Math.log(x);
                this.series1.addOrUpdate(x, y);
            }
        }else if(e.getActionCommand().equals("sqrt")){

            series1.clear();
            lineGraph.setTitle("Square root function");
            for (int i = 0; i < 100; i++) {
                double x = i;
                double y = Math.sqrt(x);
                this.series1.addOrUpdate(x, y);
            }
        }else if(e.getActionCommand().equals("linear")){

            series1.clear();
            lineGraph.setTitle("Linear function");
            for (int i = 0; i < 100; i++) {
                for(int a=0;a<rand.nextInt(2);a++){
                    for (int b=0;b<rand.nextInt(5);b++){
                        double x = i;
                        double y = a*x+b;
                        this.series1.addOrUpdate(x, y);
                    }
                }

            }
        }
    }

    public static void  main(String[] args){
        MyChart frame = new MyChart();
        frame.setVisible(true);
    }
}
