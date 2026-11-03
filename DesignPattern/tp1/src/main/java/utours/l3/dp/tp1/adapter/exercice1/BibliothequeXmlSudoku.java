package utours.l3.dp.tp1.adapter.exercice1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Bibliothèque tierce trouvée sur étagère : elle sait produire du XML, mais
 * elle ne parle pas la langue de l'application.
 *
 * <p>Trois incompatibilités, et ce sont elles qui font l'exercice :</p>
 * <ul>
 *   <li>elle ne connaît pas {@code Grille9x9} : on la remplit case par case ;</li>
 *   <li>sa méthode s'appelle {@code sauver} et prend un {@code String} ;</li>
 *   <li>elle a un état interne, donc elle n'est pas réutilisable telle quelle
 *       pour deux grilles successives sans précaution.</li>
 * </ul>
 *
 * <p><strong>On ne modifie pas cette classe.</strong> C'est l'hypothèse de
 * travail du pattern Adapter : le code réutilisé n'est pas le nôtre.</p>
 */
public class BibliothequeXmlSudoku {

    private final int[][] cellules = new int[9][9];

    /** Renseigne une cellule. Coordonnées de 1 à 9. */
    public void def(int colonne, int ligne, int valeur) {
        cellules[colonne - 1][ligne - 1] = valeur;
    }

    /** Écrit le contenu courant au format XML. */
    public void sauver(String nomFichier) throws IOException {
        try (BufferedWriter out = Files.newBufferedWriter(Path.of(nomFichier))) {
            out.write("<grid width=\"9\" height=\"9\">\n");
            out.write("  <lines>\n");
            for (int l = 0; l < 9; l++) {
                out.write("    <line index=\"" + (l + 1) + "\">\n");
                for (int c = 0; c < 9; c++) {
                    out.write("      <column index=\"" + (c + 1) + "\">"
                            + cellules[c][l] + "</column>\n");
                }
                out.write("    </line>\n");
            }
            out.write("  </lines>\n");
            out.write("</grid>\n");
        }
    }
}
