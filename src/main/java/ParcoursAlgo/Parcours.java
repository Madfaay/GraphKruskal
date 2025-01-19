package ParcoursAlgo;

import Kruskal.Edge;

public interface Parcours {
    void parcourir(int sommet, boolean[] visites, Edge[] aretes, int[] index);


    Iterable<Edge> parcourirEtRetournerAretes(int ordre , int sommeDepart);
}
