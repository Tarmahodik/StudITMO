package itmo.course2.pip.lab5.server;


public class Forme {
    private double r;

    public Forme(double newR) {
        this.r = newR;
    }

    public double getR() {
        return this.r;
    }

    public void setR(double newR) {
        this.r = newR;
    }

    public int isEntered(Vertex dot) {
        float x = dot.getXCoord();
        float y = dot.getYCoord();


        if (x <= 0 && x > -(r) && y >= 0 && y < (r / 2))
            return 1;


        if (x >= 0 && y >= 0 && y <= r / 2 - x)
            return 1;


        if (x >= 0 && y <= 0 && x * x + y * y < r * r)
            return 1;


        return 0;
    }
}

