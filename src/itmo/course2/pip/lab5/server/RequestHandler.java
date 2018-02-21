package itmo.course2.pip.lab5.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class RequestHandler extends Thread {
    private Socket client;

    RequestHandler(Socket client) {
        this.client = client;
        this.start();
    }

    @Override
    public void run() {
        String message;
        Dialog dial = new Dialog();

        try (
                InputStreamReader reader = new InputStreamReader(client.getInputStream());
                BufferedReader bufReader = new BufferedReader(reader);
                PrintStream printStream = new PrintStream(client.getOutputStream());
        ) {
            message = bufReader.readLine();
            String[] receivedParams = message.split(";");

            if (receivedParams.length != 3) {
                dial.wrongformat();

                return;
            }

            float[] params = new float[3];

            for (int i = 0; i < params.length; i++) {
                try {
                    params[i] = Float.parseFloat(receivedParams[i]);
                } catch (NumberFormatException exception) {
                    dial.wrongformat();
                    return;
                }
            }


            printStream.print(new Forme(params[2]).isEntered(new Vertex(params[0], params[1])));
            printStream.flush();
            printStream.close();

        } catch (IOException exception) {
            return;
        }
    }

}
