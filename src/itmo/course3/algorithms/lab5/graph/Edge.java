package itmo.course3.algorithms.lab5.graph;


public class Edge {
    private Vertex srcVertex;
    private Vertex destVertex;
    private boolean visited = false;

    public Edge(Vertex srcVertex, Vertex destVertex) {
        this.srcVertex = srcVertex;
        this.destVertex = destVertex;
    }

    public Vertex getSrcVertex() {
        return srcVertex;
    }

    public void setSrcVertex(Vertex srcVertex) {
        this.srcVertex = srcVertex;
    }

    public Vertex getDestVertex() {
        return destVertex;
    }

    public void setDestVertex(Vertex destVertex) {
        this.destVertex = destVertex;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
