package utours.l3.dp.tp1.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import utours.l3.dp.tp1.adapter.exercice1.BibliothequeXmlSudoku;
import utours.l3.dp.tp1.adapter.exercice1.EnregistreurDeGrille;
import utours.l3.dp.tp1.adapter.exercice1.EnregistreurTexte;
import utours.l3.dp.tp1.outils.Refactoring;
import utours.l3.dp.tp1.sudoku.Grille9x9;

@DisplayName("Exercice 2 — enregistrement d'une grille")
class EnregistrementDeGrilleTest {

    private static final String PAQUETAGE = "utours.l3.dp.tp1.adapter.exercice1.";

    /** Une grille reconnaissable : la valeur d'une case dépend de ses coordonnées. */
    private static Grille9x9 grilleTemoin() {
        Grille9x9 grille = new Grille9x9();
        for (int c = 1; c <= 9; c++) {
            for (int l = 1; l <= 9; l++) {
                grille.set(c, l, (c + l) % 9 + 1);
            }
        }
        return grille;
    }

    @Nested
    @DisplayName("Invariants — doivent passer AVANT et APRÈS le refactoring")
    class Invariants {

        @Test
        @DisplayName("l'enregistreur texte produit 9 lignes de 9 chiffres, au chemin demandé")
        void formatTexte(@TempDir Path dossier) throws IOException {
            Path fichier = dossier.resolve("grille.txt");
            new EnregistreurTexte().enregistrer(grilleTemoin(), fichier);

            List<String> lignes = Files.readAllLines(fichier);
            assertEquals(9, lignes.size());
            for (String ligne : lignes) {
                assertEquals(9, ligne.length());
                assertTrue(ligne.chars().allMatch(Character::isDigit));
            }
        }

        @Test
        @DisplayName("la bibliothèque XML produit 81 cellules")
        void formatXml(@TempDir Path dossier) throws IOException {
            Path fichier = dossier.resolve("grille.xml");
            BibliothequeXmlSudoku xml = new BibliothequeXmlSudoku();
            Grille9x9 grille = grilleTemoin();
            for (int c = 1; c <= 9; c++) {
                for (int l = 1; l <= 9; l++) {
                    xml.def(c, l, grille.get(c, l));
                }
            }
            xml.sauver(fichier.toString());

            String contenu = Files.readString(fichier);
            assertEquals(81, contenu.split("<column ", -1).length - 1);
            assertTrue(contenu.startsWith("<grid"));
        }
    }

    @Nested
    @DisplayName("Objectifs — ignorés tant que l'adaptateur n'est pas écrit")
    class Objectifs {

        private Optional<Class<?>> adaptateur() {
            return Refactoring.classe(PAQUETAGE + "AdaptateurXml");
        }

        @Test
        @DisplayName("l'adaptateur se conforme à l'interface attendue par l'application")
        void conformite() {
            Optional<Class<?>> classe = adaptateur();
            assumeTrue(classe.isPresent(), "AdaptateurXml n'existe pas encore : test ignoré");
            assertTrue(EnregistreurDeGrille.class.isAssignableFrom(classe.get()),
                    "AdaptateurXml doit implémenter EnregistreurDeGrille");
        }

        @Test
        @DisplayName("l'adaptateur écrit du XML À L'EMPLACEMENT DEMANDÉ")
        void cheminRespecte(@TempDir Path dossier) throws Exception {
            Optional<Class<?>> classe = adaptateur();
            assumeTrue(classe.isPresent(), "AdaptateurXml n'existe pas encore : test ignoré");

            Path attendu = dossier.resolve("ma-grille.xml");
            EnregistreurDeGrille sujet =
                    (EnregistreurDeGrille) Refactoring.instancier(classe.get());
            sujet.enregistrer(grilleTemoin(), attendu);

            assertTrue(Files.exists(attendu),
                    "le fichier doit être créé là où l'appelant l'a demandé, "
                    + "et non à un emplacement codé en dur dans l'adaptateur");
            String contenu = Files.readString(attendu);
            assertEquals(81, contenu.split("<column ", -1).length - 1);
        }

        @Test
        @DisplayName("l'adaptateur transmet fidèlement les valeurs de la grille")
        void valeursTransmises(@TempDir Path dossier) throws Exception {
            Optional<Class<?>> classe = adaptateur();
            assumeTrue(classe.isPresent(), "AdaptateurXml n'existe pas encore : test ignoré");

            Grille9x9 grille = new Grille9x9();
            grille.set(1, 1, 7);
            grille.set(9, 9, 3);

            Path fichier = dossier.resolve("valeurs.xml");
            ((EnregistreurDeGrille) Refactoring.instancier(classe.get()))
                    .enregistrer(grille, fichier);

            String contenu = Files.readString(fichier);
            assertTrue(contenu.contains("<column index=\"1\">7</column>"),
                    "la case (1,1) doit valoir 7 dans le XML produit");
            assertTrue(contenu.contains("<column index=\"9\">3</column>"),
                    "la case (9,9) doit valoir 3 dans le XML produit");
        }

        @Test
        @DisplayName("l'adaptateur est réutilisable pour deux grilles successives")
        void reutilisable(@TempDir Path dossier) throws Exception {
            Optional<Class<?>> classe = adaptateur();
            assumeTrue(classe.isPresent(), "AdaptateurXml n'existe pas encore : test ignoré");

            EnregistreurDeGrille sujet =
                    (EnregistreurDeGrille) Refactoring.instancier(classe.get());

            Grille9x9 premiere = new Grille9x9();
            premiere.set(1, 1, 4);
            Path f1 = dossier.resolve("a.xml");
            sujet.enregistrer(premiere, f1);

            Grille9x9 seconde = new Grille9x9();   // toutes cases vides
            Path f2 = dossier.resolve("b.xml");
            sujet.enregistrer(seconde, f2);

            assertTrue(Files.readString(f2).contains("<column index=\"1\">0</column>"),
                    "l'état de la bibliothèque adaptée ne doit pas fuir "
                    + "d'un enregistrement à l'autre");
        }
    }
}
