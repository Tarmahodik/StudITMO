package itmo.course3.algorithms.lab5.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    public boolean wasInB = false; //были ли мы в городе B
    public boolean wasInC = false; //были ли мы в городе C

    public List<Vertex> vertexes; //вершины графа
    public List<Vertex> cities = new ArrayList<>(); //города, по которым прошлись в результате

    public Vertex a; //начальный пункт
    public Vertex b; //конечный
    public Vertex c; //через что надо пройти

    public Graph(List<Vertex> vertexes, Vertex a, Vertex b, Vertex c) {
        this.vertexes = vertexes;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void dfs(Vertex pos) { //поиск в глубину
        cities.add(pos); //выставляем в результат начальную позицию
        for (Edge edge : pos.outgoingEdges) { //проходим по всем ребрам из текущей точки
            if (!edge.isVisited()) {    //если по ребру не ходили
                if (edge.getDestVertex() == b && wasInC) { //проверяем ведет ли ребро в конечный пункт и были ли мы в C
                    wasInB = true; //если да - помечаем, что мы попали в B и выходим из функции
                    return;
                }
                if (edge.getDestVertex() == c) { //если же ребро ведет в C
                    wasInC = true; // помечаем
                }
                edge.setVisited(true); //помечаем ребро, как пройденное
                dfs(edge.getDestVertex()); //ищем в глубину из точки, в которую ведет текущее ребро.
                if (wasInB && wasInC) { //если были в C и попали в B выходим из функции
                    return;
                }
                //код ниже выполняется, если мы дошли до самого низа графа, но нужные условия не соблюлись
                //т.е. подчищаем наши метки посещений, чтобы искать заново по другому маршруту
                edge.setVisited(false); //не посещали ребро
                cities.remove(cities.get(cities.size() - 1)); //не были больше в этом городе
                if (edge.getDestVertex() == c) {
                    wasInC = false; //не были больше в C
                }
            }

        }
    }
}
