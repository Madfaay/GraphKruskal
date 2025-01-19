package Graph;

import Kruskal.Edge;

import java.io.File;
import java.util.Scanner;

public  abstract class  Graph {
    protected  final int  ordre   ;
    public boolean connexe ;

    public Graph(String fileNameGraph)
    {
        File file = new File(fileNameGraph);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        }
        catch(Exception e) {
            System.out.printf("Error new Graph : \"%s\" doesn\'t exist.\n", fileNameGraph);
            e.getStackTrace();
            System.exit(1);
        }
        // If we can open the file.
        ordre = sc.nextInt();

        this.initGraph();

        for(int i = 0; i < ordre; ++i){
            int vertexNb = sc.nextInt();
            int neighbor = sc.nextInt();
            int weight = 0;
            while(neighbor != 0){
                weight = sc.nextInt();
                this.ajoutAdjacence(vertexNb, neighbor, weight);
                neighbor = sc.nextInt();
            }
        }
        sc.close();
    }

    protected abstract  void initGraph();
    protected abstract void ajoutAdjacence(int sommet , int voisin , int poids);
    protected abstract Edge[] aretesOrdonnesComposant(int sommetDebut);








}
