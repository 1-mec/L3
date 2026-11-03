package utours.l3.dp.tp1.adapter.exercice1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import utours.l3.dp.tp1.sudoku.Grille9x9;

/**
 * Implémentation maison : neuf lignes de neuf chiffres.
 */
public class EnregistreurTexte implements EnregistreurDeGrille {

    @Override
    public void enregistrer(Grille9x9 grille, Path fichier) throws IOException {
        // try-with-resources : le flux est fermé quoi qu'il arrive.
        try (BufferedWriter sortie = Files.newBufferedWriter(fichier)) {
            for (int ligne = 1; ligne <= grille.taille(); ligne++) {
                for (int colonne = 1; colonne <= grille.taille(); colonne++) {
                    sortie.write(Character.forDigit(grille.get(colonne, ligne), 10));
                }
                sortie.newLine();
            }
        }
    }
}
