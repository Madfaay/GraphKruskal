package DataStructure;

import java.util.Iterator;

public class ListeChainee<T> implements Iterable<T> {

    // Classe interne pour représenter un nœud de la liste
    private class Noeud {
        T valeur;
        Noeud suivant;

        Noeud(T valeur) {
            this.valeur = valeur;
            this.suivant = null;
        }
    }

    private Noeud tete;
    private Noeud dernier; // Pointeur vers le dernier nœud pour optimisation des ajouts

    // Constructeur
    public ListeChainee() {
        this.tete = null;
        this.dernier = null;
    }

    // Ajouter un élément à la fin
    public void ajouter(T valeur) {
        Noeud nouveauNoeud = new Noeud(valeur);
        if (tete == null) {
            tete = nouveauNoeud;
            dernier = nouveauNoeud;
        } else {
            dernier.suivant = nouveauNoeud;
            dernier = nouveauNoeud;
        }
    }

    // Ajouter un élément au début
    public void ajouterAuDebut(T valeur) {
        Noeud nouveauNoeud = new Noeud(valeur);
        nouveauNoeud.suivant = tete;
        tete = nouveauNoeud;
        if (dernier == null) {
            dernier = tete; // Si la liste était vide
        }
    }

    // Supprimer un élément par sa valeur
    public boolean supprimer(T valeur) {
        if (tete == null) {
            return false; // La liste est vide
        }

        if (tete.valeur.equals(valeur)) {
            tete = tete.suivant;
            if (tete == null) {
                dernier = null; // Mise à jour si la liste devient vide
            }
            return true;
        }

        Noeud actuel = tete;
        while (actuel.suivant != null && !actuel.suivant.valeur.equals(valeur)) {
            actuel = actuel.suivant;
        }

        if (actuel.suivant != null) {
            actuel.suivant = actuel.suivant.suivant;
            if (actuel.suivant == null) {
                dernier = actuel; // Mise à jour du dernier nœud si nécessaire
            }
            return true;
        }

        return false; // Valeur non trouvée
    }

    // Vérifier si la liste contient une valeur
    public boolean contient(T valeur) {
        Noeud actuel = tete;
        while (actuel != null) {
            if (actuel.valeur.equals(valeur)) {
                return true;
            }
            actuel = actuel.suivant;
        }
        return false;
    }

    // Obtenir la taille de la liste
    public int taille() {
        int count = 0;
        Noeud actuel = tete;
        while (actuel != null) {
            count++;
            actuel = actuel.suivant;
        }
        return count;
    }

    // Supprimer tous les éléments de la liste
    public void vider() {
        tete = null;
        dernier = null;
    }

    // Représentation en chaîne de caractères
    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder();
        Noeud actuel = tete;
        while (actuel != null) {
            resultat.append(actuel.valeur).append(" -> ");
            actuel = actuel.suivant;
        }
        resultat.append("null");
        return resultat.toString();
    }

    // Classe interne pour l'itérateur
    private class ListeChaineeIterator implements Iterator<T> {
        private Noeud courant;

        public ListeChaineeIterator() {
            courant = tete;
        }

        @Override
        public boolean hasNext() {
            return courant != null;
        }

        @Override
        public T next() {
            T valeur = courant.valeur;
            courant = courant.suivant;
            return valeur;
        }
    }

    // Implémentation de l'interface Iterable
    @Override
    public Iterator<T> iterator() {
        return new ListeChaineeIterator();
    }

}
