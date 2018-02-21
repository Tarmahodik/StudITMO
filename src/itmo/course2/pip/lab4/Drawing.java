package itmo.course2.pip.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashSet;


public class Drawing extends JPanel {
    private final int POINT_RADIUS = 4;
    private double radius = 4;
    private int step;
    private int x;
    private int y;
    private int height;
    private int width;
    private HashSet<Dot> points = new HashSet<Dot>();

    public Drawing() {
        setBackground(Main.BACKGROUND_COLOR);
        setLayout(null);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addPoint(e.getX(), e.getY());
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        x = this.getX();
        y = this.getY();
        height = this.getHeight();
        width = this.getWidth();
        if (width + x < height + y) step = (x + width) / 20;
        else step = (y + height) / 20;

        super.paintComponent(g);
        drawFigure(g);
        drawCoords(g);
        drawPoints();
    }

    private void drawCoords(Graphics g) {
        g.setColor(Main.FOREGROUND_COLOR);

        g.drawLine((x + width) / 2, y, (x + width) / 2, y + height);
        g.drawLine(x, (y + height) / 2, x + width, (y + height) / 2);

        for (int i = (x + width) / 2; i <= (x + width); i += step) {
            g.drawLine(i, ((y + height) / 2) + 2, i, ((y + height) / 2) - 2);
        }
        for (int i = (x + width) / 2; i >= x; i -= step) {
            g.drawLine(i, ((y + height) / 2) + 2, i, ((y + height) / 2) - 2);
        }
        for (int i = (y + height) / 2; i >= y; i -= step) {
            g.drawLine(((x + width) / 2) + 2, i, ((x + width) / 2) - 2, i);
        }
        for (int i = (y + height) / 2; i <= (y + height); i += step) {
            g.drawLine(((x + width) / 2) + 2, i, ((x + width) / 2) - 2, i);
        }
    }

    public void drawFigure(Graphics g) {
        g.setColor(Main.FIGURE_COLOR);

        g.fillRect(
                (x + width) / 2 - (int) (step * radius),
                (y + height) / 2 - (int) (step * radius / 2),
                (int) (step * radius),
                (int) (step * radius / 2)
        );


        Polygon polygon = new Polygon();
        polygon.addPoint((x + width) / 2, (y + height) / 2);

        polygon.addPoint((x + width) / 2 + (int) (step * radius / 2), ((y + height) / 2));

        polygon.addPoint((x + width) / 2, (y + height) / 2 - (int) (step * radius / 2));

        g.fillPolygon(polygon);

        g.fillArc(

                (x + width) / 2 - (int) (step * radius),
                (y + height) / 2 - (int) (step * radius),
                (int) (radius * step * 2), (int) (radius * step * 2),
                270,
                90
        );


    }

    public void addPoint(double pX, double pY) {
        int x = (int) (pX * step) + (this.x + width) / 2;
        int y = (int) (pY * step * -1) + (this.y + height) / 2;
        addPoint(pX, pY, x, y);
    }

    private void addPoint(int x, int y) {
        double pX = (double) (x - (this.x + width) / 2) / (double) step;
        double pY = -1 * (double) (y - (this.y + height) / 2) / (double) step;
        addPoint(pX, pY, x, y);
    }


    private void addPoint(double pX, double pY, int x, int y) {
        JTextArea label = new JTextArea("{" + new BigDecimal(pX).round(new MathContext(2)).toString()
                + "," + new BigDecimal(pY).round(new MathContext(2)).toString() + "}");
        label.setBounds(x - 40, y - 23, 80, 20);
        Dot point = new Dot(pX, pY, POINT_RADIUS, label);
        point.setBounds(x, y, POINT_RADIUS, POINT_RADIUS);
        if (!points.contains(point)) {
            points.add(point);
            add(point);
            add(label);
            repaint();
        }
    }


    public void setRadius(double radius) {
        this.radius = radius;
        repaint();
    }

    private void drawPoints() {
        points.forEach(this::drawPoint);
    }

    private void drawPoint(Dot dot) {
        if (dot.inFigure(radius)) if (dot.getColor() == Color.RED) {
            dot.paintPink();
            new Thread(new AnimationPoint(dot, this)).start();
        } else {
            dot.paintGreen();
            dot.repaint();
        }
        else {
            dot.paintRed();
            dot.repaint();
        }
        dot.getLabel().repaint();
    }

    class AnimationPoint implements Runnable {
        Dot point;
        Drawing lp;

        public AnimationPoint(Dot point, Drawing lp) {
            this.point = point;
            this.lp = lp;
        }

        @Override
        public void run() {
            Rectangle bounds = point.getBounds();
            Dot newPoint = new Dot(point.getX(), point.getY(), (int) (radius * step * 2) / 8, point.getLabel());

            newPoint.setBounds((int) bounds.getX() - (int) (radius * step) / 8 + (int) bounds.getWidth() / 2,
                    (int) bounds.getY() - (int) (radius * step) / 8 + (int) bounds.getHeight() / 2,
                    (int) (radius * step * 2) / 8, (int) (radius * step * 2) / 8);
            lp.add(newPoint);

            while (newPoint.getD() >= POINT_RADIUS) {
                newPoint.repaint();
                newPoint.decDiameter();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    lp.remove(newPoint);
                    return;
                }
            }

            lp.remove(newPoint);
        }
    }

}
