package utours.l3.dp.tp3.acquisition;

import utours.l3.dp.tp3.sudoku.Grille9x9;

/** Lit une grille dans neuf lignes de neuf chiffres, 0 pour une case vide. */
public class SourceTexte implements SourceDeGrille {

    private final String texte;

    public SourceTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public Grille9x9 charger() {
        Grille9x9 grille = new Grille9x9();
        String[] lignes = texte.strip().split("\\R");
        for (int l = 1; l <= 9; l++) {
            for (int c = 1; c <= 9; c++) {
                grille.set(c, l, Character.digit(lignes[l - 1].strip().charAt(c - 1), 10));
            }
        }
        return grille;
    }
}
