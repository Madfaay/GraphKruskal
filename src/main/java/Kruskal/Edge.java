package Kruskal;

public class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int poids;

    public Edge(int source, int destination, int poids) {
        this.source = source;
        this.destination = destination;
        this.poids = poids;
    }

    @Override
    public int compareTo(Edge autre) {
        return Integer.compare(this.poids, autre.poids); // Tri par poids croissant
    }

    @Override
    public String toString() {
        return "(" + source + " -> " + destination + ", poids: " + poids + ")";
    }
}
