package utours.l3.dp.tp1.strategy.exercice2;

import utours.l3.dp.tp1.sudoku.Grille9x9;

/**
 * Application de résolution de sudoku, réduite ici à l'acquisition.
 *
 * <p>Noter le {@code switch} : c'est le client qui choisit la source, et il
 * doit connaître toutes les méthodes du chargeur pour cela.</p>
 */
public final class Application {

    private Application() { }

    public static void main(String[] args) {
        String source = args.length > 0 ? args[0] : "aleatoire";
        ChargeurDeGrilles chargeur = new ChargeurDeGrilles();

        Grille9x9 grille;
        switch (source) {
            case "aleatoire" -> grille = chargeur.chargerAleatoire();
            case "vide"      -> grille = chargeur.chargerVide();
            default -> throw new IllegalArgumentException("Source inconnue : " + source);
        }
        System.out.print(grille);
    }
}
