package Graph;

import DataStructure.ListeChainee;
import Kruskal.Edge;
import ParcoursAlgo.Dfs;

public class GrapheL extends Graph implements Dfs {
    ListeChainee<Arete>[] adjacence; // Tableau de listes chaînées d'arêtes

    public GrapheL(String fileNameGraph) {
        super(fileNameGraph);
    }

    @Override
    protected void initGraph() {
        adjacence = new ListeChainee[ordre]; // Initialisation du tableau
        for (int i = 0; i < ordre; i++)
            adjacence[i] = new ListeChainee<Arete>();
    }

    @Override
    protected void ajoutAdjacence(int sommet, int voisin, int poids) {
        adjacence[sommet - 1].ajouter(new Arete(voisin, poids));
        adjacence[voisin - 1].ajouter(new Arete(sommet, poids));
    }

    @Override
    protected Edge[] aretesOrdonnesComposant(int sommetDebut) {
        Edge[] edges = new Edge[ordre * ordre]; // Taille maximale possible
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

        for (Arete arete : adjacence[sommet - 1]) {
            if (!visites[arete.getVoisin() - 1]) {
                // Ajouter l'arête en maintenant le tableau trié
                Edge edge = new Edge(sommet, arete.getVoisin(), arete.getPoids());
                insererAvecTri(edges, index, edge);
                dfs(arete.getVoisin(), visites, edges, index);
            }
        }
    }

    // Classe interne pour représenter une arête dans la liste d'adjacence
    private static class Arete {
        int voisin;
        int poids;

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

    // Getter pour les arêtes
    public ListeChainee<Arete>[] getAretes() {
        return adjacence;
    }

    // Méthode pour ajouter une arête
    public void ajouteAdjacence(int sommet, int voisin, int distance) {
        if (sommet < 1 || sommet > ordre || voisin < 1 || voisin > ordre) {
            throw new IllegalArgumentException("Sommet ou voisin invalide");
        }
        adjacence[sommet - 1].ajouter(new Arete(voisin, distance));
        adjacence[voisin - 1].ajouter(new Arete(sommet, distance));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graphe (ordre: ").append(ordre).append("):\n");

        for (int i = 0; i < ordre; i++) {
            sb.append("Sommet ").append(i + 1).append(" : ");
            ListeChainee<Arete> listeAretes = adjacence[i];
            if (listeAretes.taille() == 0) {
                sb.append("aucune arête");
            } else {
                sb.append(listeAretes);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
