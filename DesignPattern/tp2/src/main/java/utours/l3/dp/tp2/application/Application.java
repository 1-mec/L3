
package utours.l3.dp.tp2.application;

import utours.l3.dp.tp2.acquisition.SourceDeGrille;
import utours.l3.dp.tp2.acquisition.SourceExemple;
import utours.l3.dp.tp2.acquisition.SourceTexte;
import utours.l3.dp.tp2.resolution.Solveur;
import utours.l3.dp.tp2.resolution.SolveurBacktracking;
import utours.l3.dp.tp2.resolution.SolveurGlouton;
import utours.l3.dp.tp2.sudoku.Grille9x9;

/**
 * État initial : l'application choisit elle-même ses partenaires.
 *
 * <p>Le TP 1 a bien fait son travail — l'acquisition et la résolution sont
 * derrière des interfaces, et les algorithmes sont interchangeables. Il reste
 * pourtant une dépendance concrète, et c'est la dernière qui résiste :
 * <strong>les {@code new}</strong>.</p>
 *
 * <p>Conséquences : ajouter une source ou un solveur oblige à rouvrir cette
 * classe ; on ne peut pas la tester avec des partenaires factices ; et elle ne
 * compile que si toutes les implémentations sont présentes, ce qui interdit de
 * livrer un solveur sous forme de greffon.</p>
 */
public class Application {

    private final SourceDeGrille source;
    private final Solveur solveur;

    /**
     * @param profil « rapide » ou « fiable »
     */
    public Application(String profil, String texteGrille) {
        // Deux switch, et rien ne garantit qu'on pensera à compléter les deux.
        switch (profil) {
            case "rapide" -> this.solveur = new SolveurGlouton();
            case "fiable" -> this.solveur = new SolveurBacktracking();
            default -> throw new IllegalArgumentException("Profil inconnu : " + profil);
        }
        this.source = (texteGrille == null) ? new SourceExemple() : new SourceTexte(texteGrille);
    }

    /** @return la grille résolue, ou la grille partiellement remplie */
    public Grille9x9 executer() {
        Grille9x9 grille = source.charger();
        boolean resolue = solveur.resoudre(grille);
        System.out.println("Solveur « " + solveur.nom() + " » : "
                + (resolue ? "résolue" : "échec"));
        return grille;
    }

    public static void main(String[] args) {
        System.out.print(new Application("fiable", null).executer());
    }
}