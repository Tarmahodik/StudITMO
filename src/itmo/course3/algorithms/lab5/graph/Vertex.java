package itmo.course3.algorithms.lab5.graph;

import java.util.ArrayList;
import java.util.List;


public class Vertex {

    public List<Edge> outgoingEdges; //ребра из точки
    public int index; //номер вершины

    public Vertex(int index) {
        this.outgoingEdges = new ArrayList<Edge>();
        this.index = index;
    }
}
