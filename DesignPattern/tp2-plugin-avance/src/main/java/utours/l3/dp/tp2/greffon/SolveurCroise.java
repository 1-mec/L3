package utours.l3.dp.tp2.greffon;

import utours.l3.dp.tp2.resolution.Solveur;
import utours.l3.dp.tp2.sudoku.Grille9x9;
import utours.l3.dp.tp2.sudoku.Regles;

/**
 * Retour arrière guidé : on traite d'abord la case qui a le moins de valeurs
 * possibles (heuristique dite « MRV »).
 *
 * <p>Même résultat que le retour arrière naïf, beaucoup moins d'essais. C'est
 * exactement le genre d'amélioration qu'un greffon apporte : un algorithme de
 * plus, sans toucher à l'application.</p>
 */
public class SolveurCroise implements Solveur {

    @Override
    public String nom() {
        return "croisé";
    }

    @Override
    public boolean resoudre(Grille9x9 grille) {
        return Regles.grilleCoherente(grille) && explorer(grille);
    }

    private boolean explorer(Grille9x9 grille) {
        int meilleurC = 0;
        int meilleurL = 0;
        int meilleurNb = 10;
        for (int c = 1; c <= 9; c++) {
            for (int l = 1; l <= 9; l++) {
                if (grille.get(c, l) != Grille9x9.VIDE) {
                    continue;
                }
                int nb = 0;
                for (int v = 1; v <= 9; v++) {
                    if (Regles.placementLegal(grille, c, l, v)) {
                        nb++;
                    }
                }
                if (nb < meilleurNb) {
                    meilleurNb = nb;
                    meilleurC = c;
                    meilleurL = l;
                }
            }
        }
        if (meilleurNb == 10) {
            return true;        // plus aucune case vide
        }
        if (meilleurNb == 0) {
            return false;       // impasse détectée au plus tôt
        }
        for (int v = 1; v <= 9; v++) {
            if (Regles.placementLegal(grille, meilleurC, meilleurL, v)) {
                grille.set(meilleurC, meilleurL, v);
                if (explorer(grille)) {
                    return true;
                }
                grille.set(meilleurC, meilleurL, Grille9x9.VIDE);
            }
        }
        return false;
    }
}
