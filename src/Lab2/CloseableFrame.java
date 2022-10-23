package Lab2;

import javax.swing.*;
import java.awt.*;
public class CloseableFrame extends JFrame {
    public CloseableFrame() throws HeadlessException {
        this.setSize(640,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public CloseableFrame(GraphicsConfiguration gc) {
        super(gc);
        this.setSize(640,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public CloseableFrame(String title) throws HeadlessException {
        super(title);
        this.setSize(640,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public CloseableFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        this.setSize(640,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        CloseableFrame frame = new CloseableFrame();
        frame.setSize(640,480);
        frame.setVisible(true);
    }
}