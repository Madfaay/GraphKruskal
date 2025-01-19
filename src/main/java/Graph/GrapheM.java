package Graph;

import Kruskal.Edge;
import ParcoursAlgo.Dfs;

public class GrapheM extends Graph implements Dfs {

    int[][] adjacence;

    public GrapheM(String fileNameGraph) {
        super(fileNameGraph);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrice d'adjacence :\n");
        for (int i = 0; i < ordre; i++) {
            for (int j = 0; j < ordre; j++) {
                sb.append(adjacence[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    protected void initGraph() {
        adjacence = new int[ordre][ordre];
    }

    @Override
    public void ajoutAdjacence(int sommet, int voisin, int poids) {
        adjacence[sommet - 1][voisin - 1] = poids;
        adjacence[voisin - 1][sommet - 1] = poids;
    }

    @Override
    protected Edge[] aretesOrdonnesComposant(int sommetDebut) {
        Edge[] edges = new Edge[ordre * (ordre - 1) / 2]; // Taille maximale pour un graphe complet
        boolean[] visites = new boolean[ordre];
        int[] index = {0}; // Pour suivre l'indice courant dans le tableau

        dfs(sommetDebut, visites, edges, index);

        // Créer un tableau compact contenant seulement les arêtes collectées
        Edge[] resultat = new Edge[index[0]];
        System.arraycopy(edges, 0, resultat, 0, index[0]);
        return resultat;
    }

    @Override
    public void dfs(int sommet, boolean[] visites, Edge[] edges, int[] index) {
        visites[sommet - 1] = true;

        for (int voisin = sommet - 1; voisin < ordre; voisin++) { // Parcourir la diagonale supérieure
            if (adjacence[sommet - 1][voisin] > 0 && !visites[voisin]) {
                // Ajouter l'arête en maintenant le tableau trié
                Edge edge = new Edge(sommet, voisin + 1, adjacence[sommet - 1][voisin]);
                insererAvecTri(edges, index, edge);

                // Appel récursif pour explorer le voisin
                dfs(voisin + 1, visites, edges, index);
            }
        }
    }
}
