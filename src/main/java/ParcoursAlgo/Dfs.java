package ParcoursAlgo;

import Kruskal.Edge;

public interface Dfs {

    default void insererAvecTri(Edge[] aretes, int[] index, Edge nouvelleArete) {
        int i = index[0];

        // Trouver la position correcte pour insérer l'arête
        while (i > 0 && aretes[i - 1].compareTo(nouvelleArete) > 0) {
            aretes[i] = aretes[i - 1]; // Décaler les éléments vers la droite
            i--;
        }

        // Insérer l'arête à la bonne position
        aretes[i] = nouvelleArete;
        index[0]++; // Incrémenter l'indice
    }

     void dfs(int sommet, boolean[] visites, Edge[] aretes, int[] index) ;

}
