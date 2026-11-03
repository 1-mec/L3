package utours.l3.dp.tp2.sudoku;

/**
 * Les règles du sudoku, isolées du reste.
 *
 * <p>Ni la grille ni les solveurs n'ont à les redéfinir : elles sont écrites
 * une fois, et tout le monde s'y réfère. C'est la forme la plus économique de
 * réutilisation — antérieure à tout pattern.</p>
 */
public final class Regles {

    private Regles() { }

    /**
     * @return vrai si l'on peut écrire {@code valeur} dans la case indiquée
     *         sans violer les règles (ligne, colonne, région 3x3)
     */
    public static boolean placementLegal(Grille9x9 grille, int colonne, int ligne, int valeur) {
        for (int i = 1; i <= 9; i++) {
            if (i != colonne && grille.get(i, ligne) == valeur) {
                return false;
            }
            if (i != ligne && grille.get(colonne, i) == valeur) {
                return false;
            }
        }
        int cDebut = ((colonne - 1) / 3) * 3 + 1;
        int lDebut = ((ligne - 1) / 3) * 3 + 1;
        for (int c = cDebut; c < cDebut + 3; c++) {
            for (int l = lDebut; l < lDebut + 3; l++) {
                if ((c != colonne || l != ligne) && grille.get(c, l) == valeur) {
                    return false;
                }
            }
        }
        return true;
    }

    /** @return vrai si aucune case renseignée ne viole les règles */
    public static boolean grilleCoherente(Grille9x9 grille) {
        for (int c = 1; c <= 9; c++) {
            for (int l = 1; l <= 9; l++) {
                int v = grille.get(c, l);
                if (v != Grille9x9.VIDE && !placementLegal(grille, c, l, v)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** @return vrai si la grille est complète ET cohérente */
    public static boolean grilleResolue(Grille9x9 grille) {
        return grille.estComplete() && grilleCoherente(grille);
    }
}
