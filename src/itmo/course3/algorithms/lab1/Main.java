package itmo.course3.algorithms.lab1;


import itmo.course3.algorithms.lab1.Files.FileWorker;
import itmo.course3.algorithms.lab1.Hash.HashTable;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker("/Users/Georgy/Desktop/Egor_Algo_Lab1/Files");
//
//        //Генерим файлики
//        try
//        {
//            fileWorker.generate();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }


        ArrayList<ArrayList<String>> lines = null;
        ArrayList<HashTable> tables = new ArrayList<>(fileWorker.getFilesCount());


        //Читам страшный лист листов строк
        try {
            lines = fileWorker.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Создаем таблицы для каждого файла и заполняем, заодно считаем коллизии и выводим
        for (int i = 0; i < fileWorker.getFilesCount(); i++) {
            HashTable hashTable = new HashTable();
            ArrayList<String> currLines = lines.get(i);
            for (String line : currLines) {
                hashTable.add(line);
            }
            System.out.println("Файл номер: " + i);
            System.out.println("Количество элементов: " + (i * fileWorker.getStep() + fileWorker.getStep()));
            System.out.println("Коллизий: " + hashTable.getCollisions());
            System.out.println();
            tables.add(hashTable);
        }


        System.out.println("=============================================================");


        //Проводим поиска каждого элемента таблицы в каждой таблице, считаем количество сравнений и выводим
        for (int i = 0; i < fileWorker.getFilesCount(); i++) {
            HashTable hashTable = tables.get(i);
            ArrayList<String> currLines = lines.get(i);
            hashTable.comp = 0;
            hashTable.setSearchCounts(0);
            for (String line : currLines) {
                hashTable.search(line);
            }
            System.out.println("Файл номер: " + i);
            System.out.println("Количество элементов: " + (i * fileWorker.getStep() + fileWorker.getStep()));
            System.out.println("Сравнений: " + hashTable.comp);
            System.out.println("Запросов на поиск: " + hashTable.searchCounts);
            System.out.println("Среднее количество сравнений на запрос: " + hashTable.getMean());
            System.out.println();
        }


        String path = "/Users/Georgy/Desktop/Egor_Algo_Lab1/Files";
        char separator = ';';
        try (
                OutputStream ous = new FileOutputStream(path);
                OutputStreamWriter ouw = new OutputStreamWriter(ous, Charset.forName("UTF-8"));
                BufferedWriter bw = new BufferedWriter(ouw)
        ) {
            int i = 0;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Номер файла" + separator + "Количество элементов" + separator + "Количество коллизий" + separator + "Среднее количество сравнений");
            bw.write(stringBuffer.toString());
            bw.newLine();
            for (HashTable table : tables) {
                stringBuffer = new StringBuffer();
                stringBuffer.append("" + i + separator + (i * fileWorker.getStep() + fileWorker.getStep()) + separator + table.getCollisions() + separator + table.getMean());
                bw.write(stringBuffer.toString());
                bw.newLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
