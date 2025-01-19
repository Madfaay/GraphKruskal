package Kruskal;

import java.util.*;
import Graph.Graph;

public class Kruskal {

    // Union-Find (Disjoint Set)
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Compression de chemin
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;  // Les deux sommets sont déjà dans le même ensemble
            }

            // Union par rang
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    // Méthode pour exécuter l'algorithme de Kruskal
    public static List<Edge> kruskal(Graph graphe, int ordre, int sommeDepart) {
        // Union-Find pour gérer les ensembles disjoints
        UnionFind uf = new UnionFind(ordre);

        // Liste pour stocker les arêtes de l'arbre couvrant minimal
        List<Edge> mst = new ArrayList<>();

        // Récupérer les arêtes triées depuis le graphe
        Iterable<Edge> edges = graphe.parcourirEtRetournerAretes(ordre, sommeDepart);

        // Parcourir les arêtes triées
        for (Edge edge : edges) {
            // Si les deux sommets ne sont pas dans le même ensemble, les unir et ajouter l'arête
            if (uf.union(edge.getSrc(), edge.getDest())) {
                mst.add(edge);
            }
        }

        return mst;
    }


}
