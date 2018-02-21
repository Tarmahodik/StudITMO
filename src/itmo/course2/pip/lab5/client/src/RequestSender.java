package itmo.course2.pip.lab5.client.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class RequestSender implements Runnable {

    public static final int PORT = 15000;
    private float r;
    private float x;
    private float y;
    private Vertex vertex;

    RequestSender(Vertex vertex, double r) {
        this.vertex = vertex;
        this.r = (float) r;
    }

    @Override
    public void run() {
        this.x = (float) vertex.getXCoord();
        this.y = (float) vertex.getYCoord();

        String message;

        try (Socket socket = new Socket("127.0.0.1", RequestSender.PORT);
             PrintStream printStream = new PrintStream(socket.getOutputStream());
             BufferedReader bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            printStream.println(x + ";" + y + ";" + r + ";");
            message = bufReader.readLine();
        } catch (UnknownHostException hostException) {
            vertex.setColor(2);
            return;
        } catch (IOException ioException) {
            return;
        }

        switch (message) {
            case "0":
                vertex.setColor(0);
                vertex.setIsEntered(false);
                break;
            case "1":
                vertex.setColor(1);
                vertex.setIsEntered(true);
                break;
            default:
                vertex.setColor(2);
                break;
        }
    }

}
