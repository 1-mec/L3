package utours.l3.dp.tp5.decorator;

import java.nio.charset.StandardCharsets;
import utours.l3.dp.tp5.sudoku.Grille9x9;

/**
 * Conversion d'une grille en octets, et retour.
 *
 * <p>Ce n'est pas un pattern : c'est le format de sérialisation, dont on a
 * besoin pour avoir quelque chose à archiver.</p>
 */
public final class FormatTexte {

    private FormatTexte() { }

    public static byte[] encoder(Grille9x9 grille) {
        return grille.toString().getBytes(StandardCharsets.UTF_8);
    }

    public static Grille9x9 decoder(byte[] octets) {
        Grille9x9 grille = new Grille9x9();
        String[] lignes = new String(octets, StandardCharsets.UTF_8).strip().split("\\R");
        for (int l = 1; l <= 9; l++) {
            for (int c = 1; c <= 9; c++) {
                grille.set(c, l, Character.digit(lignes[l - 1].charAt(c - 1), 10));
            }
        }
        return grille;
    }
}
