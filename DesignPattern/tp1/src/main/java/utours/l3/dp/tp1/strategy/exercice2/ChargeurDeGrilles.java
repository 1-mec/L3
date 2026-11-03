package utours.l3.dp.tp1.strategy.exercice2;

import java.util.Random;
import utours.l3.dp.tp1.sudoku.Grille9x9;

/**
 * État initial de l'exercice : une seule classe sait produire des grilles,
 * par autant de méthodes publiques qu'il y a de sources.
 *
 * <p>On imagine sans peine les sources à venir : une image et sa
 * reconnaissance de caractères, un service web, une base de données. Chacune
 * ajoutera une méthode ici. C'est le symptôme.</p>
 */
public class ChargeurDeGrilles {

    private final Random alea = new Random(42);   // graine fixe : résultats reproductibles

    /** Produit une grille en tirant les valeurs au hasard. */
    public Grille9x9 chargerAleatoire() {
        Grille9x9 grille = new Grille9x9();
        for (int c = 1; c <= 9; c++) {
            for (int l = 1; l <= 9; l++) {
                grille.set(c, l, alea.nextInt(9) + 1);
            }
        }
        return grille;
    }

    /** Produit une grille à partir de 9 lignes de 9 chiffres. */
    public Grille9x9 chargerDepuisTexte(String texte) {
        Grille9x9 grille = new Grille9x9();
        String[] lignes = texte.strip().split("\\R");
        for (int l = 1; l <= 9; l++) {
            String ligne = lignes[l - 1].strip();
            for (int c = 1; c <= 9; c++) {
                grille.set(c, l, Character.digit(ligne.charAt(c - 1), 10));
            }
        }
        return grille;
    }

    /** Produit une grille vide, utile pour les tests. */
    public Grille9x9 chargerVide() {
        return new Grille9x9();
    }
}
