package utours.l3.dp.tp1.strategy.exercice1;

/** Client de la liste : c'est lui qui déclenche l'affichage. */
public final class User {

    private User() { }

    public static void main(String[] args) {
        ListeLivres liste = new SearchEngine().getResult();
        liste.afficher();
    }
}
