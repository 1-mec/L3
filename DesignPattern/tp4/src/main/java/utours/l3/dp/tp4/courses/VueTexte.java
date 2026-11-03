package utours.l3.dp.tp4.courses;

import java.io.PrintStream;

/**
 * Vue console de la liste.
 *
 * <p>Le flux de sortie est injecté plutôt que codé en {@code System.out} :
 * la vue devient testable sans détourner la sortie standard. C'est une
 * habitude qui coûte un paramètre et fait gagner beaucoup.</p>
 *
 * <p>État initial : c'est l'appelant qui doit penser à appeler
 * {@link #rafraichir()} après chaque modification. S'il oublie, la vue ment.</p>
 */
public class VueTexte {

    private final ListeCourses modele;
    private final PrintStream sortie;

    public VueTexte(ListeCourses modele, PrintStream sortie) {
        this.modele = modele;
        this.sortie = sortie;
    }

    public void rafraichir() {
        sortie.println("--- liste de courses (" + modele.taille() + " articles) ---");
        for (String article : modele.articles()) {
            sortie.println("  - " + article);
        }
    }

    public static void main(String[] args) {
        ListeCourses liste = new ListeCourses();
        VueTexte vue = new VueTexte(liste, System.out);

        liste.ajouter("café");
        vue.rafraichir();          // à ne pas oublier…
        liste.ajouter("pain");
        // … et ici on a oublié : la vue affichée n'est plus à jour.
    }
}
