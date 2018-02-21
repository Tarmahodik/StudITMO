package itmo.course2.pip.lab5.client.src;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class MainWindow extends JFrame {


    public MainWindow() {

        Locale.setDefault(new Locale("en", "US"));
        this.setBounds(350, 50, 850, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 300));
        this.setLayout(new BorderLayout());
        GraphicPanel graphicPanel = new GraphicPanel();
        this.add(graphicPanel);
        setLocale(new Locale("en", "US"));
        InputPanel inputPanel = new InputPanel(graphicPanel);
        this.add(inputPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
