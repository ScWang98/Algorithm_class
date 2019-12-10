package experiment.hxl.Dijkstra;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
public class UI extends JFrame {
    static final long serialVersionUID = 1L;
    static UI ui;
    static Image image;
    static Graphics2D screen;
    static double x = 0.0, y = 0.0;
    static Insets insets;
    static int width, height;
    static Color background = Color.white;
    static Color foreground = Color.green;
    public static void createUI(int width, int height) {

        ui = new UI();
        UI.width = width;
        UI.height = height;
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setTitle("USA MAP");
        ui.setResizable(false);
        ui.setVisible(true);
        insets = ui.getInsets();
        ui.setSize(new Dimension(width  + insets.left + insets.right,height + insets.top  + insets.bottom));
        image = ui.createImage(width, height);
        screen = (Graphics2D) image.getGraphics();
        clear(Color.white);

    }
    public static void clear() { clear(background); }
    public static void clear(Color background) {
        UI.background = background;
        screen.setColor(background);
        screen.fillRect(0, 0, width, height);
        screen.setColor(foreground);
    }
    public static void setColor(Color color) {
        foreground = color;
        screen.setColor(foreground);
    }
    public static void fly(double x, double y) {
        UI.x = x;
        UI.y = y;
    }
    public static void go(double x, double y) {
        screen.draw(new Line2D.Double(UI.x, height - UI.y, x, height - y));
        UI.x = x;
        UI.y = y;
    }
    public static void spot(double d) {
        if (d <= 1) screen.drawRect((int) x, (int) y, 1, 1);
        screen.fill(new Ellipse2D.Double(x - d/2, height - y - d/2, d, d));
    }
    public static void render() { ui.repaint(); }
    public void paint(Graphics g) {
        if (g != null && image != null)
            g.drawImage(image, insets.left, insets.top, null);
    }
}
