package utours.l3.dp.tp3.singleton;

import utours.l3.dp.tp3.acquisition.SourceDeGrille;
import utours.l3.dp.tp3.resolution.Solveur;

/** Fabrique abstraite issue du TP 2 : point de départ de cette partie. */
public interface FabriqueSudoku {

    String profil();

    SourceDeGrille creerSource();

    Solveur creerSolveur();
}
