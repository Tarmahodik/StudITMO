package itmo.course3.compiler.lab1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public String getProgramText(String filepath) {
        String programText = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            programText = sb.toString();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file!");
            return "";
        } catch (IOException e) {
            return "";
        }
        return programText;
    }
}
