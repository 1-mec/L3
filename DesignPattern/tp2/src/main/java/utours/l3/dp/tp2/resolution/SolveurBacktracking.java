package utours.l3.dp.tp2.resolution;

import utours.l3.dp.tp2.sudoku.Grille9x9;
import utours.l3.dp.tp2.sudoku.Regles;

/**
 * Exploration exhaustive avec retour arrière.
 *
 * <p>Plus lent que le glouton sur les grilles faciles, mais il résout tout ce
 * qui est résoluble. C'est le couple classique « rapide mais partiel » /
 * « complet mais coûteux » : deux algorithmes interchangeables, donc deux
 * stratégies.</p>
 */
public class SolveurBacktracking implements Solveur {

    @Override
    public String nom() {
        return "backtracking";
    }

    @Override
    public boolean resoudre(Grille9x9 grille) {
        return Regles.grilleCoherente(grille) && explorer(grille);
    }

    private boolean explorer(Grille9x9 grille) {
        for (int l = 1; l <= 9; l++) {
            for (int c = 1; c <= 9; c++) {
                if (grille.get(c, l) != Grille9x9.VIDE) {
                    continue;
                }
                for (int v = 1; v <= 9; v++) {
                    if (Regles.placementLegal(grille, c, l, v)) {
                        grille.set(c, l, v);
                        if (explorer(grille)) {
                            return true;
                        }
                        grille.set(c, l, Grille9x9.VIDE);   // retour arrière
                    }
                }
                return false;   // aucune valeur ne convient : impasse
            }
        }
        return true;    // plus de case vide : résolu
    }
}
