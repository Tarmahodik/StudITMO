package itmo.course2.pip.lab5.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 15000;

    public static void main(String[] args) {
        ServerSocket serverSocket;

        Dialog dial = new Dialog();

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException exception) {

            dial.wrongPort(PORT);

            return;
        }
        dial.serverRun();


        while (true) {
            try {
                Socket client = serverSocket.accept();
                new RequestHandler(client);
            } catch (IOException exception) {
                dial.errortoclinet();
            }
        }


    }
}
