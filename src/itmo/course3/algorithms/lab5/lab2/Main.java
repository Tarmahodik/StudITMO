package itmo.course3.algorithms.lab5.lab2;

import itmo.course3.algorithms.lab5.graph.Edge;
import itmo.course3.algorithms.lab5.graph.Graph;
import itmo.course3.algorithms.lab5.graph.Vertex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph = generateGraph();
        graph.dfs(graph.a);
        graph.cities.add(graph.b);
        writeResToFile(graph);
    }

    public static Graph generateGraph() {
        Scanner sc;
        try {
            sc = new Scanner(new File("PATH.IN"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            return null;
        }

        int n = sc.nextInt(); //считали n
        int m = sc.nextInt(); //считали m

        //создаем лист пустых вершин с номерами
        List<Vertex> vertexes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertexes.add(new Vertex(i));
        }
        //считываем из файла ребра и создаем их
        for (int i = 0; i < m; i++) {
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            vertexes.get(c1).outgoingEdges.add(new Edge(vertexes.get(c1), vertexes.get(c2)));
        }
        //считываем из файла начальный, конечный, промежуточные пункты
        Vertex a = vertexes.get(sc.nextInt());
        Vertex b = vertexes.get(sc.nextInt());
        Vertex c = vertexes.get(sc.nextInt());

        //создаем из этих данных граф
        return new Graph(vertexes, a, b, c);
    }

    public static void writeResToFile(Graph graph) {
        try (
                FileWriter fileWriter = new FileWriter("PATH.OUT");
                BufferedWriter writer = new BufferedWriter(fileWriter);
        ) {
            if (graph.cities.size() == 2) {
                writer.write(Integer.toString(-1));
            } else {
                writer.write(graph.cities.size());
                for (int i = 0; i < graph.cities.size(); i++) {
                    writer.write(Integer.toString(graph.cities.get(i).index));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
        }
    }
}