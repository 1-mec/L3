package utours.l3.dp.tp3.resolution;

import utours.l3.dp.tp3.sudoku.Grille9x9;

/**
 * Stratégie de résolution.
 *
 * <p>Le contrat est volontairement modeste : le solveur <em>tente</em> de
 * résoudre, et dit s'il y est parvenu. Un contrat qui promettrait la
 * résolution serait faux pour le solveur glouton, et le principe de
 * substitution serait violé.</p>
 */
public interface Solveur {

    /** @return le nom affichable du solveur */
    String nom();

    /**
     * Tente de compléter la grille sur place.
     *
     * @return vrai si la grille est résolue à la sortie
     */
    boolean resoudre(Grille9x9 grille);
}
