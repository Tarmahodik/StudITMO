package itmo.course2.pip.lab5.client.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashSet;

import static java.lang.Thread.sleep;

public class GraphicPanel extends JPanel {

    private Forme forme;
    private LinkedHashSet<Vertex> vertexes = new LinkedHashSet<Vertex>();
    private int xCenter;
    private int yCenter;
    private int x;
    private int y;
    private int scale;
    private int width;
    private int height;

    GraphicPanel() {
        this.setBackground(Color.GRAY);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addPoint(e.getX(), e.getY());
            }
        });
        this.forme = new Forme(5);
        repaint();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    public void addPoint(int xMouse, int yMouse) {
        float xC = (float) (xMouse - xCenter) * (float) this.forme.getR() / this.scale / 30;
        float yC = (float) (yCenter - yMouse) * (float) this.forme.getR() / this.scale / 30;
        JTextField textField = new JTextField();
        textField.setBounds(xMouse - 2, yMouse - 2, 70, 20);
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setFont(new Font(
                textField.getFont().getFontName(),
                textField.getFont().getStyle(),
                textField.getFont().getSize() - 3)
        );
        textField.setEditable(false);
        Vertex dot = new Vertex(xC, yC, xMouse, yMouse, textField);
        dot.setBounds(xMouse - 2, yMouse - 2, 2, 2);
        dot.setColor(2);
        if (!this.vertexes.contains(dot)) {
            new Thread(new RequestSender(dot, this.forme.getR())).start();
            this.vertexes.add(dot);
            this.add(dot);
            this.add(textField);
        }
        dot.animation = new Thread(new Animation(dot, this.forme.getR(), this.scale));
        dot.animation.start();
        repaint();
    }

    public void addPoint(float x, float y) {
        int Xc = xCenter + (int) ((x * scale * 30) / this.forme.getR());
        int Yc = yCenter - (int) ((y * scale * 30) / this.forme.getR());
        JTextField textField = new JTextField();
        textField.setBounds(Xc - 2, Yc - 2, 70, 20);
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setFont(new Font(textField.getFont().getFontName(), textField.getFont().getStyle(), textField.getFont().getSize() - 3));
        textField.setEditable(false);
        Vertex point = new Vertex(x, y, Xc, Yc, textField);
        point.setBounds(Xc - 2, Yc - 2, 2, 2);
        point.setColor(2);
        if (!this.vertexes.contains(point)) {
            new Thread(new RequestSender(point, this.forme.getR())).start();
            this.vertexes.add(point);
            this.add(point);
            this.add(textField);
        }
        point.animation = new Thread(new Animation(point, this.forme.getR(), this.scale));
        point.animation.start();
        repaint();
    }

    public void setRadius(double newR) {
        this.forme.setR(newR);
        for (Vertex vertex : vertexes) {
            vertex.recalculate(this.forme.getR(), this.scale, this.xCenter, this.yCenter);
            vertex.setColor(2);
            new Thread(new RequestSender(vertex, this.forme.getR())).start();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        this.x = this.getX();
        this.y = this.getY();
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.scale = ((this.xCenter > this.yCenter) ? yCenter / 30 : xCenter / 30);
        this.xCenter = x + this.width / 2;
        this.yCenter = y + this.height / 2;
        super.paintComponent(graphics);

        //Фигура
        drawForme(graphics);

        //Точки
        drawPoints();

        //Линии
        drawAxies(graphics);
    }

    public void drawAxies(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawLine(x, yCenter, x + width, yCenter);
        graphics.drawLine(xCenter, y, xCenter, y + height);
    }

    public void drawPoints() {
        for (Vertex vertex : vertexes) {
            vertex.repaint();
        }
    }

    public void drawForme(Graphics graphics) {

        graphics.setColor(new Color(255, 0, 0));

        Polygon polygon = new Polygon();

        //вверх левая
        polygon.addPoint(xCenter - 30 * scale, yCenter - scale * 30 / 2);

        //вверх права
        polygon.addPoint(xCenter, yCenter - scale * 30 / 2);

        //низ права
        polygon.addPoint(xCenter + 30 * scale / 2, yCenter);

        //низ лево
        polygon.addPoint(xCenter - 30 * scale, yCenter);

        graphics.fillPolygon(polygon);

        graphics.fillArc(xCenter - 30 * scale, yCenter - 30 * scale, 30 * scale * 2, 30 * scale * 2, 270, 90);

    }

    class Animation implements Runnable {

        Vertex vertex;
        double r;
        int scale;

        public Animation(Vertex vertex, double radius, int scale) {
            this.vertex = vertex;
            this.r = radius;
            this.scale = scale;
        }

        @Override
        public void run() {
            int k = 8;
            while (vertex.getRadius() >= 4) {
                resize(k);

                try {
                    sleep(30);
                } catch (InterruptedException e) {
                    vertex.setRadius(4);
                    vertex.repaint();
                    return;
                }

                k += 1;
                vertex.repaint();
            }
            vertex.setRadius(4);
            vertex.repaint();

        }

        void resize(double div) {
            this.vertex.setRadius((int) (r / div * scale));
            vertex.repaint();
        }
    }

}
