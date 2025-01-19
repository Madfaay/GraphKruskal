package Graph;

import Kruskal.Edge;
import ParcoursAlgo.Dfs;

public class GrapheM extends Graph implements Dfs {
    private int[][] adjacence;

    public GrapheM(String fileNameGraph) {
        super(fileNameGraph);
    }

    @Override
    protected void initGraph() {
        adjacence = new int[ordre][ordre];
    }

    @Override
    protected void ajoutAdjacence(int sommet, int voisin, int poids) {
        adjacence[sommet - 1][voisin - 1] = poids;
        adjacence[voisin - 1][sommet - 1] = poids;
    }

    @Override
    public void dfs(int sommet, boolean[] visites, Edge[] aretes, int[] index) {
        visites[sommet - 1] = true;

        for (int voisin = 0; voisin < ordre; voisin++) {
            if (adjacence[sommet - 1][voisin] > 0) { // Si une arête existe
                Edge nouvelleArete = new Edge(sommet, voisin + 1, adjacence[sommet - 1][voisin]);

                // Ajouter l'arête si elle n'est pas déjà ajoutée
                if (!isAreteDejaAjoutee(aretes, index[0], nouvelleArete)) {
                    insererAvecTri(aretes, index, nouvelleArete);
                }

                // Continuer le DFS pour explorer les voisins
                if (!visites[voisin]) {
                    dfs(voisin + 1, visites, aretes, index);
                }
            }
        }

        // Vérification des sommets après avoir parcouru tous les voisins
        connexe = true;
        for (boolean visite : visites) {
            if (!visite) {
                connexe = false;
                break;
            }
        }
    }






    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrice d'adjacence:\n");
        for (int i = 0; i < ordre; i++) {
            for (int j = 0; j < ordre; j++) {
                sb.append(adjacence[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
