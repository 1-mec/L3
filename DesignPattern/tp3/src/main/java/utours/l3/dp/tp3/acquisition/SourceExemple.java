package utours.l3.dp.tp3.acquisition;

import utours.l3.dp.tp3.sudoku.Grille9x9;

/** Une grille d'exemple codée en dur : pratique pour démarrer et pour tester. */
public class SourceExemple implements SourceDeGrille {

    /** Grille classique, de difficulté moyenne, à solution unique. */
    public static final String GRILLE = """
            530070000
            600195000
            098000060
            800060003
            400803001
            700020006
            060000280
            000419005
            000080079""";

    @Override
    public Grille9x9 charger() {
        return new SourceTexte(GRILLE).charger();
    }
}
