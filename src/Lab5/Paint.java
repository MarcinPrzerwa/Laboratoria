package Lab5;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Paint extends JFrame implements ActionListener {

    // Panels
    private JPanel buttonPanel;
    private JPanel drawPanel;

    // Buttons
    private JButton clearBtn;
    private JButton pencilBtn;
    private JButton lineBtn;
    private JButton rectangleBtn;
    private JButton ellipseBtn;
    private JButton foregroundColorBtn;
    private JButton backgroundColorBtn;
    private JButton eraseBtn;

    // Menu elements
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveItem;
    private JMenuItem importItem;
    private JMenu widthMenu;
    private JMenuItem widthMenuItem1;
    private JMenuItem widthMenuItem2;
    private JMenuItem widthMenuItem3;

    // shapes types
    private enum ShapeType {
        PENCIL,
        LINE,
        RECTANGLE,
        ELLIPSE
    }

    // Shapes options
    private Color foreground = Color.black;
    private int lineWidth = 1;

    // Lists of shapes
    private ArrayList<ColoredRectangle> coloredRectangles = new ArrayList<>();
    private ArrayList<ColoredLine> coloredLines = new ArrayList<>();
    private ArrayList<ColoredEllipse> coloredEllipses = new ArrayList<>();
    private ArrayList<ColoredEllipse> coloredPencils = new ArrayList<>();

    // Shapes
    private ShapeType shape = ShapeType.PENCIL;
    private Rectangle2D rectangle;
    private Line2D line;
    private Ellipse2D ellipse;
    private Line2D pencil;

    public static void main(String[] args) {
        Paint frame = new Paint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Paint() throws HeadlessException {

        this.setSize(900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        // Menu bar setup
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");

        saveItem = new JMenuItem("Save file");
        saveItem.setActionCommand("save");
        saveItem.addActionListener(this);

        importItem = new JMenuItem("Open file");
        importItem.setActionCommand("open");
        importItem.addActionListener(this);

        fileMenu.add(saveItem);
        fileMenu.add(importItem);

        widthMenu = new JMenu("Line width");

        widthMenuItem1 = new JMenuItem("1 pxl");
        widthMenuItem1.setActionCommand("width1");
        widthMenuItem1.addActionListener(this);

        widthMenuItem2 = new JMenuItem("5 pxl");
        widthMenuItem2.setActionCommand("width5");
        widthMenuItem2.addActionListener(this);

        widthMenuItem3 = new JMenuItem("10 pxl");
        widthMenuItem3.setActionCommand("width10");
        widthMenuItem3.addActionListener(this);

        widthMenu.add(widthMenuItem1);
        widthMenu.add(widthMenuItem2);
        widthMenu.add(widthMenuItem3);
        menuBar.add(fileMenu);
        menuBar.add(widthMenu);
        this.setJMenuBar(menuBar);


        // Button panel setup
        buttonPanel = new JPanel();

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coloredRectangles.clear();
                coloredLines.clear();
                coloredEllipses.clear();
                drawPanel.setBackground(Color.white);
                repaint();
            }
        });

        pencilBtn = new JButton("Pencil");
        pencilBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = ShapeType.PENCIL;
            }
        });

        lineBtn = new JButton("Line");
        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = ShapeType.LINE;
            }
        });

        rectangleBtn = new JButton("Rectangle");
        rectangleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = ShapeType.RECTANGLE;
            }
        });

        ellipseBtn = new JButton("Ellipse");
        ellipseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = ShapeType.ELLIPSE;
            }
        });

        foregroundColorBtn = new JButton("Shape Color");
        foregroundColorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorChooser = JColorChooser.showDialog(foregroundColorBtn, "Choose color", Color.black);
                foreground = colorChooser;
            }
        });

        backgroundColorBtn = new JButton("Background Color");
        backgroundColorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorChooser = JColorChooser.showDialog(foregroundColorBtn, "Choose color", Color.white);
                drawPanel.setBackground(colorChooser);
            }
        });

        buttonPanel.add(pencilBtn);
        buttonPanel.add(lineBtn);
        buttonPanel.add(rectangleBtn);
        buttonPanel.add(ellipseBtn);
        buttonPanel.add(foregroundColorBtn);
        buttonPanel.add(backgroundColorBtn);
        buttonPanel.add(clearBtn);
        this.add(buttonPanel, BorderLayout.PAGE_START);


        // Draw panel setup
        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Drawing shapes from lists
//                if (coloredPencils.size() > 0) {
//                    for (ColoredPencil cp : coloredPencils) {
//                        Graphics2D g2d = (Graphics2D)g;
//                        g2d.setColor(cp.getForeground());
//                        g2d.setStroke(new BasicStroke(cp.getLineWidth()));
//                        g2d.drawLine(cp.getStartX(), cp.getStartY(), cp.getEndX(), cp.getEndY());
//
//                    }
//                }

                if (coloredRectangles.size() > 0) {
                    for (ColoredRectangle cr : coloredRectangles)
                    {
                        Graphics2D g2d = (Graphics2D)g;
                        g2d.setColor(cr.getForeground());
                        g2d.setStroke(new BasicStroke(cr.getLineWidth()));
                        Rectangle r = cr.getRectangle();
                        g2d.drawRect(r.x, r.y, r.width, r.height);

                    }
                }

                if (coloredLines.size() > 0) {
                    for (ColoredLine cl : coloredLines) {
                        Graphics2D g2d = (Graphics2D)g;
                        g2d.setColor(cl.getForeground());
                        g2d.setStroke(new BasicStroke(cl.getLineWidth()));
                        g2d.drawLine(cl.getStartX(), cl.getStartY(), cl.getEndX(), cl.getEndY());

                    }
                }

                if (coloredEllipses.size() > 0) {
                    for (ColoredEllipse ce : coloredEllipses) {
                        Graphics2D g2d = (Graphics2D)g;
                        g2d.setColor(ce.getForeground());
                        g2d.setStroke(new BasicStroke(ce.getLineWidth()));
                        g2d.drawOval(ce.getX(), ce.getY(), ce.getWidth(), ce.getHeight());

                    }
                }

                // Drawing shapes in real time
                switch (shape) {
                    case PENCIL:
                        if (pencil!= null)
                        {
                            Graphics2D g2d = (Graphics2D)g;
                            g2d.setColor(foreground);
                            g2d.setStroke(new BasicStroke(lineWidth));
                            g2d.draw(pencil);
                        }
                        break;
                    case LINE:
                        if (line != null)
                        {
                            Graphics2D g2d = (Graphics2D)g;
                            g2d.setColor(foreground);
                            g2d.setStroke(new BasicStroke(lineWidth));
                            g2d.draw(line);
                        }
                        break;
                    case RECTANGLE:
                        if (rectangle != null)
                        {
                            Graphics2D g2d = (Graphics2D)g;
                            g2d.setColor(foreground);
                            g2d.setStroke(new BasicStroke(lineWidth));
                            g2d.draw(rectangle);
                        }
                        break;
                    case ELLIPSE:
                        if (ellipse != null)
                        {
                            Graphics2D g2d = (Graphics2D)g;
                            g2d.setColor(foreground);
                            g2d.setStroke(new BasicStroke(lineWidth));
                            g2d.draw(ellipse);
                        }
                        break;
                }
            }
        };

        MouseListener ml = new MouseListener();
        drawPanel.addMouseListener(ml);
        drawPanel.addMouseMotionListener(ml);
        drawPanel.setBackground(Color.white);
        this.add(drawPanel);
    }

    // Menu item listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "width1") {
            lineWidth = 1;
            getContentPane().repaint();
        } else if (e.getActionCommand() == "width5") {
            lineWidth = 5;
            getContentPane().repaint();
        } else if (e.getActionCommand() == "width10") {
            lineWidth = 10;
            getContentPane().repaint();
        } else if (e.getActionCommand() == "save") {
            BufferedImage image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            drawPanel.paintAll(g2d);
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PNG images", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File outputFile = new File(chooser.getSelectedFile().getAbsolutePath() + ".png");
                try {
                    ImageIO.write(image, "png", outputFile);
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } else if (e.getActionCommand() == "open") {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PNG images", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                BufferedImage image = null;
                File inputFile = new File(chooser.getSelectedFile().getAbsolutePath());
                try {
                    image = ImageIO.read(inputFile);
                    Graphics g = drawPanel.getGraphics();
                    g.drawImage(image, 0, 0, null);
                } catch(IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    // Methods for adding shapes to lists
//    public void addPencil(Line2D pencil, Color color) {
//    	ColoredPencil cp = new ColoredPencil(color, pencil, lineWidth);
//    	coloredPencils.add(cp);
//    	repaint();
//    }

    public void addRectangle(Rectangle2D rectangle, Color color) {
        ColoredRectangle cr = new ColoredRectangle(color, rectangle, lineWidth);
        coloredRectangles.add(cr);
        repaint();
    }

    public void addLine(Line2D line, Color color) {
        ColoredLine cl = new ColoredLine(color, line, lineWidth);
        coloredLines.add(cl);
        repaint();
    }

    public void addEllipse(Ellipse2D ellipse, Color color) {
        ColoredEllipse ce = new ColoredEllipse(color, ellipse, lineWidth);
        coloredEllipses.add(ce);
        repaint();
    }

    // Mouse listener
    class MouseListener extends MouseInputAdapter {
        private Point startPoint;

        public void mousePressed(MouseEvent e)
        {
            startPoint = e.getPoint();
            switch (shape) {
                case PENCIL:
                    pencil = new Line2D.Double();
                    break;
                case LINE:
                    line = new Line2D.Double();
                    break;
                case RECTANGLE:
                    rectangle = new Rectangle2D.Double();
                    break;
                case ELLIPSE:
                    ellipse = new Ellipse2D.Double();
                    break;
            }
        }

        public void mouseDragged(MouseEvent e)
        {
            Point endPoint = e.getPoint();
            double x = Math.min(startPoint.x, e.getX());
            double y = Math.min(startPoint.y, e.getY());
            double width = Math.abs(startPoint.x - e.getX());
            double height = Math.abs(startPoint.y - e.getY());

            switch (shape) {
                case PENCIL:
                    pencil.setLine(startPoint, endPoint);
                    break;
                case LINE:
                    line.setLine(startPoint, endPoint);
                    break;
                case RECTANGLE:
                    rectangle.setRect(x, y, width, height);
                    break;
                case ELLIPSE:
                    ellipse.setFrame(x, y, width, height);
                    break;
            }
            repaint();
        }

        public void mouseReleased(MouseEvent e)
        {
            switch (shape) {
                case PENCIL:
                    if (pencil.getP1() != pencil.getP2()) {
                        addLine(pencil, foreground);
                    }
                    break;
                case LINE:
                    if (line.getP1() != line.getP2()) {
                        addLine(line, foreground);
                    }
                    line = null;
                    break;
                case RECTANGLE:
                    if (rectangle.getWidth() != 0 || rectangle.getHeight() != 0) {
                        addRectangle(rectangle, foreground);
                    }
                    rectangle = null;
                    break;
                case ELLIPSE:
                    if (ellipse.getWidth() != 0 || ellipse.getHeight() != 0) {
                        addEllipse(ellipse, foreground);
                    }
                    ellipse = null;
                    break;
            }
        }
    }
}

// Shapes classes

class ColoredPencil {
    private Color foreground;
    private Line2D pencil;
    private int pencilWidth;

    public ColoredPencil(Color foreground, Line2D pencil, int pencilWidth) {
        this.foreground = foreground;
        this.pencil = pencil;
        this.pencilWidth = pencilWidth;
    }

    public Color getForeground() {
        return foreground;
    }

    public int getLineWidth() {
        return pencilWidth;
    }

    public int getStartX() {
        return (int) pencil.getX1();
    }
    public int getStartY() {
        return (int) pencil.getY1();
    }
    public int getEndX() {
        return (int) pencil.getX2();
    }
    public int getEndY() {
        return (int) pencil.getY2();
    }
}

class ColoredRectangle {
    private Color foreground;
    private Rectangle2D rectangle;
    private int lineWidth;

    public ColoredRectangle(Color foreground, Rectangle2D rectangle, int lineWidth) {
        this.foreground = foreground;
        this.rectangle = rectangle;
        this.lineWidth = lineWidth;
    }

    public Color getForeground() {
        return foreground;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public Rectangle getRectangle()
    {
        return rectangle.getBounds();
    }
}

class ColoredLine {
    private Color foreground;
    private Line2D line;
    private int lineWidth;

    public ColoredLine(Color foreground, Line2D line, int lineWidth) {
        this.foreground = foreground;
        this.line = line;
        this.lineWidth = lineWidth;
    }

    public Color getForeground() {
        return foreground;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public int getStartX() {
        return (int) line.getX1();
    }
    public int getStartY() {
        return (int) line.getY1();
    }
    public int getEndX() {
        return (int) line.getX2();
    }
    public int getEndY() {
        return (int) line.getY2();
    }
}

class ColoredEllipse {
    private Color foreground;
    private Ellipse2D ellipse;
    private int lineWidth;

    public ColoredEllipse(Color foreground, Ellipse2D ellipse, int lineWidth) {
        this.foreground = foreground;
        this.ellipse = ellipse;
        this.lineWidth = lineWidth;
    }

    public Color getForeground() {
        return foreground;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public int getX() {
        return (int) ellipse.getX();
    }
    public int getY() {
        return (int) ellipse.getY();
    }
    public int getWidth() {
        return (int) ellipse.getWidth();
    }
    public int getHeight() {
        return (int) ellipse.getHeight();
    }
}