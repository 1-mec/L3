package utours.l3.dp.tp2.greffon;

import utours.l3.dp.tp2.resolution.Solveur;
import utours.l3.dp.tp2.sudoku.Grille9x9;
import utours.l3.dp.tp2.sudoku.Regles;

/**
 * Solveur apporté par un greffon : « singleton caché ».
 *
 * <p>Pour chaque région, ligne et colonne, si une valeur n'a plus qu'une seule
 * case possible, on la place. Plus puissant qu'un simple comptage de
 * candidats, et toujours sans retour arrière.</p>
 */
public class SolveurComptage implements Solveur {

    @Override
    public String nom() {
        return "comptage";
    }

    @Override
    public boolean resoudre(Grille9x9 grille) {
        boolean progresse = true;
        while (progresse) {
            progresse = false;
            for (int valeur = 1; valeur <= 9; valeur++) {
                progresse |= placerUniqueParLigne(grille, valeur);
                progresse |= placerUniqueParColonne(grille, valeur);
            }
        }
        return Regles.grilleResolue(grille);
    }

    private boolean placerUniqueParLigne(Grille9x9 grille, int valeur) {
        boolean pose = false;
        for (int l = 1; l <= 9; l++) {
            int candidats = 0;
            int colonne = 0;
            for (int c = 1; c <= 9; c++) {
                if (grille.get(c, l) == Grille9x9.VIDE
                        && Regles.placementLegal(grille, c, l, valeur)) {
                    candidats++;
                    colonne = c;
                }
            }
            if (candidats == 1) {
                grille.set(colonne, l, valeur);
                pose = true;
            }
        }
        return pose;
    }

    private boolean placerUniqueParColonne(Grille9x9 grille, int valeur) {
        boolean pose = false;
        for (int c = 1; c <= 9; c++) {
            int candidats = 0;
            int ligne = 0;
            for (int l = 1; l <= 9; l++) {
                if (grille.get(c, l) == Grille9x9.VIDE
                        && Regles.placementLegal(grille, c, l, valeur)) {
                    candidats++;
                    ligne = l;
                }
            }
            if (candidats == 1) {
                grille.set(c, ligne, valeur);
                pose = true;
            }
        }
        return pose;
    }
}
