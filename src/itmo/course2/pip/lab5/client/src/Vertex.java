package itmo.course2.pip.lab5.client.src;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Vertex extends JComponent implements Comparable {

    public boolean isNotSended;
    int radius;
    Thread animation;
    private float x;
    private float y;
    private int realX;
    private int realY;
    private boolean isEntered;
    private Color color;
    private JTextField textField;

    public Vertex(float newX, float newY, int newRealX, int newRealY, JTextField newTextField) {
        this.x = newX;
        this.y = newY;
        this.realX = newRealX;
        this.realY = newRealY;
        this.textField = newTextField;
        this.textField.setText(this.toString());
        this.radius = 4;
    }

    public void setIsEntered(boolean entered) {
        this.isEntered = entered;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int r) {
        this.radius = r;
    }

    @Override
    public boolean equals(Object obj) {
        Vertex dot = null;

        try {
            dot = (Vertex) obj;
        } catch (ClassCastException e) {
            return false;
        }

        if (dot == null)
            return false;

        return (this.x == dot.x && this.y == dot.y);

    }

    public int compareTo(Object obj) {
        Vertex dot = (Vertex) obj;
        return (int) ((this.x * this.x + this.y * this.y) - (dot.x * dot.x + dot.y * dot.y));
    }

    public float getXCoord() {
        return this.x;
    }

    public float getYCoord() {
        return this.y;
    }

    @Override
    public String toString() {
        NumberFormat nf = new DecimalFormat("#0.00");
        return "{" + nf.format(this.x) + "; " + nf.format(this.y) + "}";
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.setColor(this.color);
        this.setBounds(realX - this.radius / 2, realY - this.radius / 2, this.radius, this.radius);
        this.textField.setBounds(realX + this.radius, realY + this.radius, 70, 20);
        graphics.fillOval(this.getWidth() / 2 - radius / 2, this.getHeight() / 2 - radius / 2, radius, radius);
    }

    public void setColor(int color) {
        if (color == 1) {
            this.color = Color.GREEN;
            this.isNotSended = false;
        } else if (color == 2) {
            this.color = Color.LIGHT_GRAY;
            this.isNotSended = true;
        } else {
            this.color = Color.RED;
            this.isNotSended = false;
        }
    }

    public void recalculate(double newR, double newScale, int newXCenter, int newYCenter) {
        int rX = newXCenter + (int) ((this.x * newScale * 30) / newR);
        int rY = newYCenter - (int) ((this.y * newScale * 30) / newR);
        if (rX < 0 || rY < 0 || rX > newXCenter * 2 || rY > newYCenter * 2) {
            this.setVisible(false);
            this.textField.setVisible(false);
            return;
        }
        this.realX = rX;
        this.realY = rY;
        this.setVisible(true);
        this.textField.setVisible(true);

    }
}
