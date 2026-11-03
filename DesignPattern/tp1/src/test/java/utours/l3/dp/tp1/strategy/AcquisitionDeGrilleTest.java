package utours.l3.dp.tp1.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utours.l3.dp.tp1.outils.Refactoring;
import utours.l3.dp.tp1.strategy.exercice2.ChargeurDeGrilles;
import utours.l3.dp.tp1.sudoku.Grille9x9;

@DisplayName("Exercice 3 — acquisition de grilles de sudoku")
class AcquisitionDeGrilleTest {

    private static final String PAQUETAGE = "utours.l3.dp.tp1.strategy.exercice2.";

    private static final String NEUF_LIGNES = """
            530070000
            600195000
            098000060
            800060003
            400803001
            700020006
            060000280
            000419005
            000080079""";

    @Nested
    @DisplayName("Invariants — doivent passer AVANT et APRÈS le refactoring")
    class Invariants {

        @Test
        @DisplayName("le chargement depuis un texte respecte les valeurs")
        void depuisTexte() {
            Grille9x9 grille = new ChargeurDeGrilles().chargerDepuisTexte(NEUF_LIGNES);
            assertEquals(5, grille.get(1, 1));
            assertEquals(3, grille.get(2, 1));
            assertEquals(Grille9x9.VIDE, grille.get(3, 1));
            assertEquals(9, grille.get(9, 9));
        }

        @Test
        @DisplayName("le chargement aléatoire remplit toute la grille")
        void aleatoire() {
            assertTrue(new ChargeurDeGrilles().chargerAleatoire().estComplete());
        }

        @Test
        @DisplayName("deux tirages successifs diffèrent")
        void tiragesDifferents() {
            ChargeurDeGrilles chargeur = new ChargeurDeGrilles();
            assertNotEquals(chargeur.chargerAleatoire().toString(),
                            chargeur.chargerAleatoire().toString());
        }
    }

    @Nested
    @DisplayName("Objectifs — ignorés tant que le refactoring n'est pas fait")
    class Objectifs {

        @Test
        @DisplayName("il existe une interface SourceDeGrille")
        void interfaceSource() {
            Optional<Class<?>> source = Refactoring.classe(PAQUETAGE + "SourceDeGrille");
            assumeTrue(source.isPresent(), "SourceDeGrille n'existe pas encore : test ignoré");
            assertTrue(source.get().isInterface());
            assertEquals(1, source.get().getMethods().length,
                    "une stratégie d'acquisition n'a qu'une opération : produire une grille");
        }

        @Test
        @DisplayName("au moins deux sources concrètes existent, et sont interchangeables")
        void sourcesConcretes() {
            Optional<Class<?>> source = Refactoring.classe(PAQUETAGE + "SourceDeGrille");
            Optional<Class<?>> alea = Refactoring.classe(PAQUETAGE + "SourceAleatoire");
            Optional<Class<?>> texte = Refactoring.classe(PAQUETAGE + "SourceTexte");
            assumeTrue(source.isPresent() && alea.isPresent() && texte.isPresent(),
                    "SourceAleatoire et SourceTexte n'existent pas encore : test ignoré");

            assertTrue(source.get().isAssignableFrom(alea.get()));
            assertTrue(source.get().isAssignableFrom(texte.get()));
        }
    }
}
