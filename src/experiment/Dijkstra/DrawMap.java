package experiment.Dijkstra;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

class Line {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}

public class DrawMap {
    private JFrame frame;
    private PaintLines map;
    private PaintLines relaxed;
    private PaintLines path;
    private int width;
    private int height;

    public DrawMap(int width, int height, String name) {
        this.width = width;
        this.height = height;

        frame = new JFrame(name);
        frame.setSize(width, height);
//        frame.setResizable(false);
        frame.setVisible(true);

        path = new PaintLines(width, height);
        path.setColor(Color.black);
        frame.add(path);

        relaxed = new PaintLines(width, height);
        relaxed.setColor(Color.red);
        frame.add(relaxed);

        map = new PaintLines(width, height);
        map.setColor(Color.green);
        frame.add(map);
    }

    public int transLocationX(int x) {
        return (int) (0.14 * x) + 20;
    }

    public int transLocationY(int y) {
        return 790 - (int) (0.18 * y);
    }

    public void addMapLine(int x1, int y1, int x2, int y2) {
        map.addLine(transLocationX(x1),
                transLocationY(y1),
                transLocationX(x2),
                transLocationY(y2));
//        map.addLine(x1, y1, x2, y2);
    }

    public void addPathLine(int x1, int y1, int x2, int y2) {
        path.addLine(transLocationX(x1),
                transLocationY(y1),
                transLocationX(x2),
                transLocationY(y2));
//        path.addLine(x1, y1, x2, y2);
    }

    public void addRelaxedLine(int x1, int y1, int x2, int y2) {
        relaxed.addLine(transLocationX(x1),
                transLocationY(y1),
                transLocationX(x2),
                transLocationY(y2));
    }

    public void clearRelaxed() {
        relaxed.clear();
    }

    public void clearPath() {
        path.clear();
    }

    public void repaintMap() {
        map.repaint();
    }

    public void repaintRelaxed() {
        relaxed.repaint();
    }

    public void repaintPath() {
        path.repaint();
    }

    public static void main(String[] args) {
        DrawMap map = new DrawMap(1000, 1000, "map");
        map.addMapLine(10, 10, 500, 500);
        map.addMapLine(300, 400, 1000, 800);

        map.addPathLine(70, 50, 180, 850);
        map.addPathLine(110, 10, 140, 820);
    }
}

class PaintLines extends JPanel {

    private ArrayList<Line> lines;
    Color color = Color.black;


    public PaintLines(int width, int height) {
        setSize(width, height);
        setBackground(null);
        setOpaque(false);
        lines = new ArrayList<Line>();
    }

    public void clear() {
        lines.clear();
        repaint();
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        lines.add(new Line(x1, y1, x2, y2));
//        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        for (int i = 0; i < lines.size(); ++i) {
            Line line = lines.get(i);
            g.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }


}

