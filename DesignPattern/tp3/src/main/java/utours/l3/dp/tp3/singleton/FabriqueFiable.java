package utours.l3.dp.tp3.singleton;

import utours.l3.dp.tp3.acquisition.SourceDeGrille;
import utours.l3.dp.tp3.acquisition.SourceExemple;
import utours.l3.dp.tp3.resolution.Solveur;
import utours.l3.dp.tp3.resolution.SolveurBacktracking;

/**
 * Fabrique concrète. Rien n'empêche aujourd'hui d'en créer autant qu'on veut :
 * {@code new FabriqueFiable()} est appelable de partout.
 */
public class FabriqueFiable implements FabriqueSudoku {

    /** Compteur d'instances : il rend visible ce que l'exercice veut corriger. */
    private static int instancesCreees;

    public FabriqueFiable() {
        instancesCreees++;
    }

    public static int instancesCreees() {
        return instancesCreees;
    }

    static void reinitialiserCompteur() {
        instancesCreees = 0;
    }

    @Override
    public String profil() {
        return "fiable";
    }

    @Override
    public SourceDeGrille creerSource() {
        return new SourceExemple();
    }

    @Override
    public Solveur creerSolveur() {
        return new SolveurBacktracking();
    }
}
