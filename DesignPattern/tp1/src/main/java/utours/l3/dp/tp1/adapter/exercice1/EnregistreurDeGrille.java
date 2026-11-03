package utours.l3.dp.tp1.adapter.exercice1;

import java.io.IOException;
import java.nio.file.Path;
import utours.l3.dp.tp1.sudoku.Grille9x9;

/**
 * Interface d'enregistrement attendue par l'application.
 *
 * <p>C'est la « cible » du pattern Adapter : tout ce que l'application sait
 * appeler.</p>
 */
public interface EnregistreurDeGrille {

    /**
     * Enregistre une grille dans un fichier.
     *
     * @param grille  la grille à enregistrer
     * @param fichier le chemin du fichier à produire
     * @throws IOException si l'écriture échoue
     */
    void enregistrer(Grille9x9 grille, Path fichier) throws IOException;
}
