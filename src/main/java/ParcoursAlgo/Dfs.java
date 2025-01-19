package ParcoursAlgo;

import Kruskal.Edge;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

import java.util.Iterator;

public interface Dfs extends Parcours {

    void dfs(int sommet, boolean[] visites, Edge[] aretes, int[] index);

    @Override
    default void parcourir(int sommet, boolean[] visites, Edge[] aretes, int[] index) {
        dfs(sommet, visites, aretes, index);
    }

    default Iterable<Edge> parcourirEtRetournerAretes(int ordre , int sommeDepart) {
        return new Iterable<Edge>() {
            @Override
            public Iterator<Edge> iterator() {
                Edge[] edges = new Edge[ordre * (ordre - 1) / 2];
                boolean[] visites = new boolean[ordre];
                int[] index = {0};

                dfs(sommeDepart, visites, edges, index);

                Edge[] resultat = new Edge[index[0]];
                System.arraycopy(edges, 0, resultat, 0, index[0]);

                return new Iterator<Edge>() {
                    private int currentIndex = 0;

                    @Override
                    public boolean hasNext() {
                        return currentIndex < resultat.length;
                    }

                    @Override
                    public Edge next() {
                        return resultat[currentIndex++];
                    }
                };
            }
        };
    }

    default void insererAvecTri(Edge[] aretes, int[] index, Edge nouvelleArete) {
        int i = index[0];
        while (i > 0 && aretes[i - 1].compareTo(nouvelleArete) > 0) {
            aretes[i] = aretes[i - 1]; // Décaler les éléments vers la droite
            i--;
        }
        aretes[i] = nouvelleArete; // Insérer à la bonne position
        index[0]++; // Incrémenter l'indice
    }

    default boolean isAreteDejaAjoutee(Edge[] aretes, int count, Edge nouvelleArete) {
        for (int i = 0; i < count; i++) {
            if (aretes[i].equals(nouvelleArete)) {
                return true;
            }
        }
        return false;
    }


}
