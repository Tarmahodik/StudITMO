package itmo.course3.compiler.lab1;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Pass filename as an argument");
            return;
        }

        Reader reader = new Reader();
        String programText = reader.getProgramText(args[0]);

        if (programText.length() == 0) {
            System.out.println("Program is empty!");
            return;
        }

        Analyser analyser = new Analyser();
        analyser.readByChar(programText);

    }
}
