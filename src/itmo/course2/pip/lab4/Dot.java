package itmo.course2.pip.lab4;

import javax.swing.*;
import java.awt.*;

public class Dot extends JComponent {
    private Color color = Color.PINK;
    private int D;
    private double x;
    private double y;
    private JTextArea label;

    public Dot(double x, double y, int D, JTextArea area) {
        this.D = D;
        this.x = x;
        this.y = y;
        this.label = area;
    }


    public Color getColor() {
        return color;
    }

    public double getD() {
        return D;
    }

    public JTextArea getLabel() {
        return label;
    }

    public void decDiameter() {
        D--;
    }

    public void paintRed() {
        color = Color.RED;
    }

    public void paintPink() {
        color = Color.pink;
    }

    public void paintGreen() {
        color = Color.GREEN;
    }

    public boolean inFigure(double r) {
        return (y < r / 2) && (y > 0) && (x > -1 * r) && (x <= 0)
                || (y < r / 2) && (y >= 0) && (y > -1 * x + r / 4)
                || (y > -1 * r) && (x > 0) && (x * x + y * y < r * r);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillOval(this.getWidth() / 2 - D / 2, this.getHeight() / 2 - D / 2,
                D, D);
    }
}