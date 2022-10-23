package Lab4;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Wielokąty extends JFrame {

    private static final int SLIDER_MIN = 3;
    private static final int SLIDER_MAX = 33;
    private static final int SLIDER_INIT = 3;

    private static final int radius = 100;

    private String selectedPanel = "regular";
    private Color lineColor = Color.black;
    private int lineWidth = 1;

    private int xPoly[];
    private int yPoly[];

    //menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;

    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;

    //top
    private JSlider slider;
    private JButton buttonTop;
    private JLabel labelTop;

    //left
    private TitledBorder polygonTitle;
    private ButtonGroup group;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;

    //center
    private Polygon polygon;


    //right
    private GridLayout layout;
    private static int numberOfTextFieldsRows = SLIDER_INIT;
    private static final int numberOfTextFieldsColumns = 2;
    private JLabel labelRight1;
    private JLabel labelRight2;
    private JTextField[][] textFields;

    //bottom
    private JButton buttonBottom1;
    private JButton buttonBottom2;

    private String value;

    public Wielokąty() throws HeadlessException {

        this.setSize(900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        //Menu
        menuBar = new JMenuBar();
        menu = new JMenu("Line width");
        menuItem1 = new JMenuItem("1 pxl");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineWidth = 1;
                getContentPane().repaint();
            }
        });
        menuItem2 = new JMenuItem("5 pxl");
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineWidth = 5;
                getContentPane().repaint();
            }
        });
        menuItem3 = new JMenuItem("10 pxl");
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineWidth = 10;
                getContentPane().repaint();
            }
        });

        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        //*****//

        // Top Panel
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        labelTop = new JLabel("No. of vertices");
        slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
        slider.setPreferredSize(new Dimension(200, 50));
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new SliderChangeListener());

        buttonTop = new JButton("Draw");
        buttonTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawCustomShape();
            }
        });

        topPanel.add(labelTop);
        topPanel.add(slider);
        topPanel.add(buttonTop);
        this.add(topPanel, BorderLayout.PAGE_START);
        //*****//

        // Left Panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        polygonTitle = BorderFactory.createTitledBorder("Polygon");
        group = new ButtonGroup();
        radioButton1 = new JRadioButton("Regular");
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPanel = "regular";
                drawRegularShape(numberOfTextFieldsRows);
                fillTextFields(numberOfTextFieldsRows, numberOfTextFieldsColumns);
            }
        });
        radioButton1.setSelected(true);

        radioButton2 = new JRadioButton("Random");
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPanel = "random";
                drawRandomShape(numberOfTextFieldsRows);
                fillTextFields(numberOfTextFieldsRows, numberOfTextFieldsColumns);
            }
        });
        group.add(radioButton1);
        group.add(radioButton2);

        leftPanel.setBorder(polygonTitle);
        leftPanel.add(radioButton1);
        leftPanel.add(radioButton2);
        this.add(leftPanel, BorderLayout.LINE_START);
        //*****//

        // Center Panel
        drawRegularShape(3);
        centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BasicStroke stroke = new BasicStroke(lineWidth);
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(this.getSize().width / 2, this.getSize().height / 2);
                g2d.setStroke(stroke);
                g2d.setColor(lineColor);
                g2d.drawPolygon(polygon);
            }
        };
        centerPanel.setBackground(Color.white);
        this.add(centerPanel, BorderLayout.CENTER);
        //*****//

        //Right Panel
        setupRightPanel(4, 2);
        //*****//

        // Bottom Panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        buttonBottom1 = new JButton("BG color");
        buttonBottom1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JColorChooser colorChooser = new JColorChooser();
                JDialog dialog = JColorChooser.createDialog(Wielokąty.this, "Choose background color", true, colorChooser, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        centerPanel.setBackground(colorChooser.getColor());
                    }
                }, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Nic do zrobienia
                    }
                });
                dialog.setVisible(true);
            }
        });

        buttonBottom2 = new JButton("LN color");
        buttonBottom2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JColorChooser colorChooser = new JColorChooser();
                JDialog dialog = JColorChooser.createDialog(Wielokąty.this, "Select line color", true, colorChooser, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lineColor = colorChooser.getColor();
                        getContentPane().repaint();
                    }
                }, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                dialog.setVisible(true);
            }
        });

        bottomPanel.add(buttonBottom1);
        bottomPanel.add(buttonBottom2);
        this.add(bottomPanel, BorderLayout.PAGE_END);
        //*****//
    }

    private void drawRandomShape(int vertices) {
        Random generator = new Random();
        xPoly = new int[vertices];
        yPoly = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            xPoly[i] = generator.nextInt(701) - 350;
        }
        for (int i = 0; i < vertices; i++) {
            yPoly[i] = generator.nextInt(401) - 200;
        }

        polygon = new Polygon(xPoly, yPoly, vertices);
        this.getContentPane().repaint();
    }

    private void drawRegularShape(int vertices) {
        xPoly = new int[vertices];
        yPoly = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            xPoly[i] = (int) (radius * Math.cos((Math.PI / 2 + 2 * Math.PI * i) / vertices));
        }
        for (int i = 0; i < vertices; i++) {
            yPoly[i] = (int) (radius * Math.sin((Math.PI / 2 + 2 * Math.PI * i) / vertices));
        }

        polygon = new Polygon(xPoly, yPoly, vertices);
        this.getContentPane().repaint();
    }

    private void drawCustomShape() {
        xPoly = new int[numberOfTextFieldsRows];
        yPoly = new int[numberOfTextFieldsRows];

        for (int m = 0; m < numberOfTextFieldsRows; m++) {
            for (int n = 0; n < numberOfTextFieldsColumns; n++) {
                if (m % 2 == 0 && n % 2 == 0) {
                    xPoly[m] = Integer.parseInt(textFields[m][n].getText());
                } else if (m % 2 == 0 && n % 2 != 0) {
                    yPoly[m] = Integer.parseInt(textFields[m][n].getText());
                } else if (m % 2 != 0 && n % 2 == 0) {
                    xPoly[m] = Integer.parseInt(textFields[m][n].getText());
                } else if (m % 2 != 0 && n % 2 != 0) {
                    yPoly[m] = Integer.parseInt(textFields[m][n].getText());
                }
            }
        }

        for (int i = 0; i < numberOfTextFieldsRows; i++) {
            xPoly[i] = xPoly[i] - this.getSize().width / 2;
        }
        for (int i = 0; i < numberOfTextFieldsRows; i++) {
            yPoly[i] = yPoly[i] - this.getSize().height / 2;
        }

        polygon = new Polygon(xPoly, yPoly, numberOfTextFieldsRows);
        this.getContentPane().repaint();
    }

    private void fillTextFields(int rows, int cols) {
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {
                if (m % 2 == 0 && n % 2 == 0) {
                    xPoly[m] = xPoly[m] + this.getSize().width / 2;
                    textFields[m][n].setText(String.format("%d", xPoly[m]));
                } else if (m % 2 == 0 && n % 2 != 0) {
                    yPoly[m] = yPoly[m] + this.getSize().height / 2;
                    textFields[m][n].setText(String.format("%d", yPoly[m]));
                } else if (m % 2 != 0 && n % 2 == 0) {
                    xPoly[m] = xPoly[m] + this.getSize().width / 2;
                    textFields[m][n].setText(String.format("%d", xPoly[m]));
                } else if (m % 2 != 0 && n % 2 != 0) {
                    yPoly[m] = yPoly[m] + this.getSize().height / 2;
                    textFields[m][n].setText(String.format("%d", yPoly[m]));
                }
            }
        }
    }

    private void setupRightPanel(int rows, int cols) {
        if (rightPanel != null) {
            this.remove(rightPanel);
            this.revalidate();
        }

        rightPanel = new JPanel();
        layout = new GridLayout(rows, cols);
        rightPanel.setLayout(layout);
        labelRight1 = new JLabel("X pos.");
        labelRight2 = new JLabel("Y pos.");
        rightPanel.add(labelRight1);
        rightPanel.add(labelRight2);
        textFields = new JTextField[rows][cols];
        for (int m = 0; m < rows - 1; m++) {
            for (int n = 0; n < cols; n++) {
                textFields[m][n] = new JTextField();
                rightPanel.add(textFields[m][n]);
            }
        }
        this.add(rightPanel, BorderLayout.LINE_END);
        this.revalidate();
    }

    public static void main(String[] args) {
        Wielokąty frame = new Wielokąty();
        frame.setVisible(true);
        frame.fillTextFields(numberOfTextFieldsRows, numberOfTextFieldsColumns);
    }

    private class SliderChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent arg0) {
            JSlider source = (JSlider) arg0.getSource();
            numberOfTextFieldsRows = slider.getValue();
            setupRightPanel(numberOfTextFieldsRows + 1, numberOfTextFieldsColumns);
            if (selectedPanel == "regular") {
                drawRegularShape(source.getValue());
            } else if (selectedPanel == "random") {
                drawRandomShape(source.getValue());
            }
            fillTextFields(numberOfTextFieldsRows, numberOfTextFieldsColumns);
        }
    }
}
