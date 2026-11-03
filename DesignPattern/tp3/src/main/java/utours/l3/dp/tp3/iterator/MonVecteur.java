package utours.l3.dp.tp3.iterator;

/**
 * Un vecteur d'entiers de taille fixe : la structure de données que vous allez
 * doter d'un itérateur.
 *
 * <p>Elle est volontairement pauvre : on donne sa taille à la construction, on
 * place des valeurs, et on peut l'afficher. <strong>Il n'y a pas de getter par
 * rang</strong>, et c'est délibéré : sans itérateur, un client ne peut donc pas
 * parcourir le contenu. C'est ce manque que l'exercice comble — et il illustre
 * l'intention du pattern : <em>accéder aux éléments sans exposer la
 * représentation interne</em>.</p>
 */
public class MonVecteur {

    /** Représentation interne. Elle doit rester invisible du client. */
    private final int[] donnees;

    public MonVecteur(int taille) {
        this.donnees = new int[taille];
    }

    /** Place une valeur au rang indiqué, à partir de 0. */
    public void set(int rang, int valeur) {
        donnees[rang] = valeur;
    }

    /** @return le nombre d'éléments */
    public int taille() {
        return donnees.length;
    }

    /**
     * Copie défensive de la représentation interne.
     *
     * <p>Réservée à l'itérateur : renvoyer le tableau lui-même laisserait
     * n'importe qui modifier le vecteur par la bande.</p>
     */
    int[] donneesInternes() {
        return donnees;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < donnees.length; i++) {
            sb.append(i == 0 ? "" : ", ").append(donnees[i]);
        }
        return sb.append(']').toString();
    }
}
