package utours.l3.dp.tp3.resolution;

import utours.l3.dp.tp3.sudoku.Grille9x9;
import utours.l3.dp.tp3.sudoku.Regles;

/**
 * Remplit les cases pour lesquelles une seule valeur est possible, et
 * recommence tant qu'il progresse.
 *
 * <p>Rapide, sans retour arrière, mais il échoue dès que la grille exige une
 * hypothèse. C'est un solveur honnête : il renvoie {@code false} plutôt que
 * de rendre une grille fausse.</p>
 */
public class SolveurGlouton implements Solveur {

    @Override
    public String nom() {
        return "glouton";
    }

    @Override
    public boolean resoudre(Grille9x9 grille) {
        boolean progresse = true;
        while (progresse) {
            progresse = false;
            for (int c = 1; c <= 9; c++) {
                for (int l = 1; l <= 9; l++) {
                    if (grille.get(c, l) != Grille9x9.VIDE) {
                        continue;
                    }
                    int unique = Grille9x9.VIDE;
                    int candidats = 0;
                    for (int v = 1; v <= 9; v++) {
                        if (Regles.placementLegal(grille, c, l, v)) {
                            candidats++;
                            unique = v;
                        }
                    }
                    if (candidats == 1) {
                        grille.set(c, l, unique);
                        progresse = true;
                    }
                }
            }
        }
        return Regles.grilleResolue(grille);
    }
}
