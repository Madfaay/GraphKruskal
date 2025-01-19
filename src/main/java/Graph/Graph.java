package Graph;

import Kruskal.Edge;
import ParcoursAlgo.Parcours;

import java.io.File;
import java.util.Scanner;

public abstract class Graph implements Parcours {
    protected final int ordre;
    public boolean connexe = true; //par default


    public int getOrdre()
    {
        return ordre;
    }
    public Graph(String fileNameGraph) {
        File file = new File(fileNameGraph);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (Exception e) {
            System.out.printf("Error new Graph: \"%s\" doesn't exist.\n", fileNameGraph);
            e.printStackTrace();
            System.exit(1);
        }

        // Lecture de l'ordre du graphe
        ordre = sc.nextInt();
        this.initGraph();

        // Lecture des aretes
        for (int i = 0; i < ordre; ++i) {
            int vertexNb = sc.nextInt();
            int neighbor = sc.nextInt();
            int weight = 0;
            while (neighbor != 0) {
                weight = sc.nextInt();
                this.ajoutAdjacence(vertexNb, neighbor, weight);
                neighbor = sc.nextInt();
            }
        }
        sc.close();
    }

    protected abstract void initGraph();

    protected abstract void ajoutAdjacence(int sommet, int voisin, int poids);


}
