package utours.l3.dp.tp1.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.reflect.Method;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utours.l3.dp.tp1.outils.Refactoring;
import utours.l3.dp.tp1.outils.SortieConsole;
import utours.l3.dp.tp1.strategy.exercice1.ListeLivres;
import utours.l3.dp.tp1.strategy.exercice1.Livre;
import utours.l3.dp.tp1.strategy.exercice1.SearchEngine;

@DisplayName("Exercice 1 — affichage d'une liste de livres")
class AffichageDesLivresTest {

    private static final String PAQUETAGE = "utours.l3.dp.tp1.strategy.exercice1.";

    @Nested
    @DisplayName("Invariants — doivent passer AVANT et APRÈS le refactoring")
    class Invariants {

        @Test
        @DisplayName("la recherche produit quatre ouvrages")
        void quatreOuvrages() {
            assertEquals(4, new SearchEngine().getResult().taille());
        }

        @Test
        @DisplayName("afficher() écrit une ligne par ouvrage, avec son titre")
        void affichageResume() {
            ListeLivres liste = new SearchEngine().getResult();
            String sortie = SortieConsole.capturer(liste::afficher);

            for (int rang = 0; rang < liste.taille(); rang++) {
                Livre livre = liste.get(rang);
                assertTrue(sortie.contains(livre.getTitre()),
                        "le titre « " + livre.getTitre() + " » doit apparaître");
            }
            assertEquals(liste.taille(), compter(sortie, "----------------------------------------"),
                    "un séparateur par ouvrage");
        }

        @Test
        @DisplayName("l'affichage résumé ne contient pas le résumé de l'ouvrage")
        void resumeNonAffiche() {
            ListeLivres liste = new SearchEngine().getResult();
            String sortie = SortieConsole.capturer(liste::afficher);
            assertTrue(!sortie.contains(liste.get(0).getResume()),
                    "le format résumé doit rester résumé");
        }
    }

    @Nested
    @DisplayName("Objectifs — ignorés tant que le refactoring n'est pas fait")
    class Objectifs {

        @Test
        @DisplayName("il existe une interface AfficheurLivre")
        void interfaceAfficheur() {
            Optional<Class<?>> afficheur = Refactoring.classe(PAQUETAGE + "AfficheurLivre");
            assumeTrue(afficheur.isPresent(),
                    "AfficheurLivre n'existe pas encore : test ignoré");
            assertTrue(afficheur.get().isInterface(),
                    "AfficheurLivre doit être une interface : le couplage doit être abstrait");
        }

        @Test
        @DisplayName("le Livre n'expose plus de méthode d'affichage")
        void livreSansAffichage() {
            assumeTrue(Refactoring.classe(PAQUETAGE + "AfficheurLivre").isPresent(),
                    "refactoring non commencé : test ignoré");
            for (Method m : Livre.class.getDeclaredMethods()) {
                assertTrue(!m.getName().startsWith("afficher"),
                        "Livre ne doit plus exposer " + m.getName()
                        + " : la présentation appartient à l'afficheur");
            }
        }

        @Test
        @DisplayName("la liste sait afficher avec l'afficheur qu'on lui donne")
        void afficheurFourniParLAppelant() throws Exception {
            Optional<Class<?>> interfaceAfficheur = Refactoring.classe(PAQUETAGE + "AfficheurLivre");
            Optional<Class<?>> detaille = Refactoring.classe(PAQUETAGE + "AfficheurDetaille");
            assumeTrue(interfaceAfficheur.isPresent() && detaille.isPresent(),
                    "AfficheurDetaille n'existe pas encore : test ignoré");

            Method afficher = ListeLivres.class.getMethod("afficher", interfaceAfficheur.get());
            ListeLivres liste = new SearchEngine().getResult();
            Object afficheurDetaille = Refactoring.instancier(detaille.get());

            String sortie = SortieConsole.capturer(() -> {
                try {
                    afficher.invoke(liste, afficheurDetaille);
                } catch (ReflectiveOperationException e) {
                    throw new AssertionError(e);
                }
            });
            assertTrue(sortie.contains(liste.get(0).getResume()),
                    "l'afficheur détaillé doit montrer le résumé de l'ouvrage");
        }

        @Test
        @DisplayName("afficher() sans argument existe toujours : l'API publique est préservée")
        void compatibiliteAscendante() throws Exception {
            assumeTrue(Refactoring.classe(PAQUETAGE + "AfficheurLivre").isPresent(),
                    "refactoring non commencé : test ignoré");
            ListeLivres.class.getMethod("afficher");   // lève NoSuchMethodException si supprimée
        }
    }

    private static int compter(String texte, String motif) {
        int total = 0;
        int i = texte.indexOf(motif);
        while (i >= 0) {
            total++;
            i = texte.indexOf(motif, i + motif.length());
        }
        return total;
    }
}
