package utours.l3.dp.tp2.acquisition;

import utours.l3.dp.tp2.sudoku.Grille9x9;

/** Stratégie d'acquisition d'une grille (résultat du TP 1). */
@FunctionalInterface
public interface SourceDeGrille {
    Grille9x9 charger();
}
