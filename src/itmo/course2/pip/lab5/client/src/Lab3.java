package itmo.course2.pip.lab5.client.src;

import javax.swing.*;

public class Lab3 extends Thread {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Lab3());
    }

    public void run() {
        new MainWindow();
    }
}