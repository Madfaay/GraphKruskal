package Kruskal;

import java.util.*;
import Graph.Graph;
import Graph.GrapheL;
import Graph.GrapheM;

import DataStructure.ListeChainee;

public class Kruskal {

    // Union-Find (Disjoint Set)
    static class UnionFind {
        private final int[] parent;
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
    public static ListeChainee<Edge> kruskal(Graph graphe, int ordre, int sommeDepart) {
        // Union-Find pour gérer les ensembles disjoints
        UnionFind uf = new UnionFind(ordre);

        // Liste pour stocker les arêtes de l'arbre couvrant minimal
        ListeChainee<Edge> mst = new ListeChainee<>();

        // Récupérer les arêtes triées depuis le graphe
        Iterable<Edge> edges = graphe.parcourirEtRetournerAretes(ordre, sommeDepart);

        int cout = 0;
        // Parcourir les arêtes triées
        for (Edge edge : edges) {
            // Si les deux sommets ne sont pas dans le même ensemble, les unir et ajouter l'arête
            if (uf.union(edge.getSrc()-1, edge.getDest()-1)) {
                mst.ajouter(edge);
                cout = cout + edge.getWeight();
            }
        }

        System.out.println("Le cout de l'arbre est "+cout);
        return mst;
    }

    public static void main(String[] args) {

            String emplacementActuel = System.getProperty("user.dir");


            GrapheL grapheL = new GrapheL("fichier.txt");


            Iterable<Edge> edgesl = grapheL.parcourirEtRetournerAretes(grapheL.getOrdre() , 1);

            ListeChainee<Edge> mst = kruskal(grapheL, grapheL.getOrdre(), 1);
        System.out.println("Arêtes de l'arbre couvrant minimal :");
        for (Edge edge : mst) {
            System.out.println("De " + edge.getSrc() + " à " + edge.getDest() + " avec un poids de " + edge.getWeight());
        }
        }
}
