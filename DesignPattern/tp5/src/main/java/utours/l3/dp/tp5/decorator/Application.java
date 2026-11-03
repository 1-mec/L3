package utours.l3.dp.tp5.decorator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;
import utours.l3.dp.tp5.sudoku.Grille9x9;

/**
 * État initial : l'application archive une grille, et l'on a commencé à
 * ajouter des services autour — journalisation, compression — en les écrivant
 * <em>dans</em> la méthode.
 *
 * <p>Trois défauts sautent aux yeux :</p>
 * <ul>
 *   <li>le service rendu (archiver) et les services annexes (journaliser,
 *       compresser) sont mélangés ;</li>
 *   <li>on ne peut pas choisir à l'exécution ce que l'on veut activer, sauf à
 *       multiplier les {@code if} ;</li>
 *   <li>la compression est écrite ici, mais la décompression n'existe nulle
 *       part : personne ne saura relire ce fichier.</li>
 * </ul>
 */
public final class Application {

    private Application() { }

    public static void archiver(Grille9x9 grille, Path fichier, boolean compresser)
            throws IOException {
        long debut = System.nanoTime();
        System.out.println("[log] écriture de " + fichier);

        byte[] contenu = FormatTexte.encoder(grille);
        if (compresser) {
            ByteArrayOutputStream tampon = new ByteArrayOutputStream();
            try (GZIPOutputStream gz = new GZIPOutputStream(tampon)) {
                gz.write(contenu);
            }
            contenu = tampon.toByteArray();
        }
        new ArchiveFichier().ecrire(contenu, fichier);

        System.out.println("[log] " + contenu.length + " octets en "
                + (System.nanoTime() - debut) / 1000 + " µs");
    }

    public static void main(String[] args) throws IOException {
        Grille9x9 grille = new Grille9x9();
        for (int c = 1; c <= 9; c++) {
            for (int l = 1; l <= 9; l++) {
                grille.set(c, l, (c + l) % 9 + 1);
            }
        }
        archiver(grille, Path.of("grille.dat"), true);
    }
}
