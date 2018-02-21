package itmo.course2.pip.lab4;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("1814");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(800, 600);

        Drawing drawing = new Drawing();
        getContentPane().add(new Components(drawing));

        setVisible(true);
    }
}