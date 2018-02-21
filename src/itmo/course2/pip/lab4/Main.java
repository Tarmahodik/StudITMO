package itmo.course2.pip.lab4;

import javax.swing.*;
import java.awt.*;

public class Main implements Runnable {
    public static final Color BACKGROUND_COLOR = new Color(55, 156, 24);
    public static final Color FOREGROUND_COLOR = Color.BLACK;
    public static final Color FIGURE_COLOR = new Color(144, 110, 0);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }

    @Override
    public void run() {
        new MainWindow();
    }

}