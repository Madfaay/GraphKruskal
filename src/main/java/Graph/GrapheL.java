package Graph;

import DataStructure.ListeChainee;
import Kruskal.Edge;
import ParcoursAlgo.Dfs;

public class GrapheL extends Graph implements Dfs {
    private ListeChainee<Arete>[] adjacence;

    public GrapheL(String fileNameGraph) {
        super(fileNameGraph);
    }

    @Override
    protected void initGraph() {
        adjacence = new ListeChainee[ordre];
        for (int i = 0; i < ordre; i++) {
            adjacence[i] = new ListeChainee<>();
        }
    }

    @Override
    protected void ajoutAdjacence(int sommet, int voisin, int poids) {
        adjacence[sommet - 1].ajouter(new Arete(voisin, poids));
        adjacence[voisin - 1].ajouter(new Arete(sommet, poids));
    }

    @Override
    public void dfs(int sommet, boolean[] visites, Edge[] aretes, int[] index) {
        visites[sommet - 1] = true;

        // Parcourir toutes les arêtes du sommet courant
        for (Arete arete : adjacence[sommet - 1]) {
            int voisin = arete.getVoisin();
            Edge nouvelleArete = new Edge(sommet, voisin, arete.getPoids());

            // Ajouter l'arête si elle n'a pas encore été ajoutée
            if (!isAreteDejaAjoutee(aretes, index[0], nouvelleArete)) {
                insererAvecTri(aretes, index, nouvelleArete);
            }

            // Continuer le DFS si le voisin n'a pas encore été visité
            if (!visites[voisin - 1]) {
                dfs(voisin, visites, aretes, index);
            }
        }

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
        sb.append("Liste d'adjacence:\n");
        for (int i = 0; i < ordre; i++) {
            sb.append("Sommet ").append(i + 1).append(" : ").append(adjacence[i]).append("\n");
        }
        return sb.toString();
    }

    private static class Arete {
        private final int voisin;
        private final int poids;

        public Arete(int voisin, int poids) {
            this.voisin = voisin;
            this.poids = poids;
        }

        public int getVoisin() {
            return voisin;
        }

        public int getPoids() {
            return poids;
        }

        @Override
        public String toString() {
            return "(Voisin: " + voisin + ", Poids: " + poids + ")";
        }
    }
}
