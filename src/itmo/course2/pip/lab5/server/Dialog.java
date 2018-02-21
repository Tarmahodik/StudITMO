package itmo.course2.pip.lab5.server;

public class Dialog {

    public void wrongformat() {
        System.err.println("Wrong format");
    }

    public void serverRun() {
        System.out.println("*******************");
        System.out.println("Server now running!");
        System.out.println("*******************");
    }

    public void errortoclinet() {
        System.err.println("Failed!");
    }

    public void wrongPort(int i) {
        System.err.println("Wrong port " + i);
    }


}