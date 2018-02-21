package itmo.course3.algorithms.lab3;

public class Edge implements Comparable {

    public int from;
    public int to;
    public int weight;
    public boolean used;

    public Edge(int f, int t, int w) {
        this.from = f;
        this.to = t;
        this.weight = w;
    }


    public int compareTo(Object obj) {
        Edge tmp = (Edge) obj;
        if (this.weight < tmp.weight) {
            return -1;
        } else if (this.weight > tmp.weight) {
            return 1;
        }
        return 0;
    }
}
