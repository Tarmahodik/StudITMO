package itmo.course3.algorithms.lab1.Files;


import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class FileWorker {
    public static final int firstSymbol = 33;
    public static final int lastSymbol = 126;
    private String path;
    private int filesCount = 20;
    private int step = 1000;

    public FileWorker(String path) {
        this.path = path;
    }

    public ArrayList<ArrayList<String>> readFromFile() throws IOException {
        String line;
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>(filesCount);
        for (int i = 0; i < filesCount; i++) {
            try (
                    InputStream fis = new FileInputStream(path + Integer.toString(i));
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                    BufferedReader br = new BufferedReader(isr)
            ) {
                result.add(new ArrayList<>());
                while ((line = br.readLine()) != null) {
                    result.get(i).add(line);
                }
            }
        }
        return result;
    }

    public void generate() throws IOException {
        Random random = new Random();
        for (int i = 0; i < filesCount; i++) {
            try (
                    OutputStream ous = new FileOutputStream(path + Integer.toString(i));
                    OutputStreamWriter ouw = new OutputStreamWriter(ous, Charset.forName("UTF-8"));
                    BufferedWriter bw = new BufferedWriter(ouw)
            ) {
                for (int j = 0; j < step + i * step; j++) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int k = 0; k < 32; k++) {
                        stringBuffer.append((char) (random.nextInt(lastSymbol - firstSymbol) + firstSymbol));
                    }
                    bw.write(stringBuffer.toString());
                    bw.newLine();
                }
            }
        }
    }

    public int getFilesCount() {
        return filesCount;
    }

    public int getStep() {
        return step;
    }
}


