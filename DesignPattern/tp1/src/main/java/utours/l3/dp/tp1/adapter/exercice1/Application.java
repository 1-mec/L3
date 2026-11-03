package utours.l3.dp.tp1.adapter.exercice1;

import java.io.IOException;
import java.nio.file.Path;
import utours.l3.dp.tp1.sudoku.Grille9x9;

/**
 * État initial : l'application utilise proprement l'interface pour le format
 * texte, mais doit descendre dans les détails de la bibliothèque XML pour
 * l'autre format. Les deux enregistrements ne se ressemblent pas, alors
 * qu'ils rendent le même service.
 */
public final class Application {

    private Application() { }

    public static void main(String[] args) throws IOException {
        Grille9x9 grille = new Grille9x9();
        for (int c = 1; c <= 9; c++) {
            for (int l = 1; l <= 9; l++) {
                grille.set(c, l, (c + l) % 9 + 1);
            }
        }

        // Cas propre : on passe par l'interface.
        EnregistreurDeGrille enregistreur = new EnregistreurTexte();
        enregistreur.enregistrer(grille, Path.of("grille.txt"));

        // Cas sale : l'application connaît la bibliothèque, sa boucle de
        // remplissage et sa convention de nommage. C'est ce qu'il faut faire
        // disparaître.
        BibliothequeXmlSudoku xml = new BibliothequeXmlSudoku();
        for (int c = 1; c <= 9; c++) {
            for (int l = 1; l <= 9; l++) {
                xml.def(c, l, grille.get(c, l));
            }
        }
        xml.sauver("grille.xml");
    }
}
