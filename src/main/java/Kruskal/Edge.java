package Kruskal;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge autre = (Edge) obj;

        return (source == autre.source && destination == autre.destination) ||
                (source == autre.destination && destination == autre.source);
    }


}
