package utours.l3.dp.tp5.sudoku;

/**
 * Grille de sudoku 9x9.
 *
 * <p>Objet purement métier : il détient les valeurs et rien d'autre. Il ne
 * sait ni s'afficher, ni s'enregistrer, ni se résoudre — c'est ce qui le rend
 * réutilisable, et c'est ce que les exercices vont exploiter.</p>
 *
 * <p>Les coordonnées sont données de 1 à 9, comme sur une grille de papier ;
 * la conversion vers l'indexation à partir de 0 est un détail interne.</p>
 */
public class Grille9x9 {

    /** Valeur d'une case vide. */
    public static final int VIDE = 0;

    private static final int TAILLE = 9;

    private final int[][] valeurs = new int[TAILLE][TAILLE];

    /**
     * Place une valeur dans une case.
     *
     * @param colonne colonne, de 1 à 9
     * @param ligne   ligne, de 1 à 9
     * @param valeur  valeur, de 0 (case vide) à 9
     * @throws IllegalArgumentException si un paramètre sort de son domaine
     */
    public void set(int colonne, int ligne, int valeur) {
        verifierCoordonnees(colonne, ligne);
        if (valeur < 0 || valeur > 9) {
            throw new IllegalArgumentException("Valeur hors domaine : " + valeur);
        }
        valeurs[colonne - 1][ligne - 1] = valeur;
    }

    /**
     * Lit la valeur d'une case.
     *
     * @param colonne colonne, de 1 à 9
     * @param ligne   ligne, de 1 à 9
     * @return la valeur, ou {@link #VIDE} si la case est vide
     */
    public int get(int colonne, int ligne) {
        verifierCoordonnees(colonne, ligne);
        return valeurs[colonne - 1][ligne - 1];
    }

    /** @return la taille du côté de la grille */
    public int taille() {
        return TAILLE;
    }

    /** @return vrai si aucune case n'est vide */
    public boolean estComplete() {
        for (int c = 1; c <= TAILLE; c++) {
            for (int l = 1; l <= TAILLE; l++) {
                if (get(c, l) == VIDE) {
                    return false;
                }
            }
        }
        return true;
    }

    private void verifierCoordonnees(int colonne, int ligne) {
        if (colonne < 1 || colonne > TAILLE || ligne < 1 || ligne > TAILLE) {
            throw new IllegalArgumentException(
                    "Coordonnées hors grille : (" + colonne + ", " + ligne + ")");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int l = 1; l <= TAILLE; l++) {
            for (int c = 1; c <= TAILLE; c++) {
                sb.append(get(c, l));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
