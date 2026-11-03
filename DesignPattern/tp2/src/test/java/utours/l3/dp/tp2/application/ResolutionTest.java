package utours.l3.dp.tp2.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.reflect.Method;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utours.l3.dp.tp2.acquisition.SourceDeGrille;
import utours.l3.dp.tp2.acquisition.SourceExemple;
import utours.l3.dp.tp2.outils.Refactoring;
import utours.l3.dp.tp2.resolution.Solveur;
import utours.l3.dp.tp2.resolution.SolveurBacktracking;
import utours.l3.dp.tp2.resolution.SolveurGlouton;
import utours.l3.dp.tp2.sudoku.Grille9x9;
import utours.l3.dp.tp2.sudoku.Regles;

@DisplayName("TP2 — acquisition, résolution, fabriques")
class ResolutionTest {

    private static final String P = "utours.l3.dp.tp2.application.";

    @Nested
    @DisplayName("Invariants — doivent passer AVANT et APRÈS le refactoring")
    class Invariants {

        @Test
        @DisplayName("la grille d'exemple est cohérente et incomplète")
        void grilleExemple() {
            Grille9x9 g = new SourceExemple().charger();
            assertTrue(Regles.grilleCoherente(g));
            assertFalse(g.estComplete());
            assertEquals(5, g.get(1, 1));
        }

        @Test
        @DisplayName("le solveur par retour arrière résout la grille d'exemple")
        void backtrackingResout() {
            Grille9x9 g = new SourceExemple().charger();
            assertTrue(new SolveurBacktracking().resoudre(g));
            assertTrue(Regles.grilleResolue(g));
        }

        @Test
        @DisplayName("le solveur glouton respecte son contrat : il ne ment pas")
        void gloutonHonnete() {
            Grille9x9 g = new SourceExemple().charger();
            boolean annonce = new SolveurGlouton().resoudre(g);
            assertEquals(annonce, Regles.grilleResolue(g),
                    "un solveur doit renvoyer vrai si et seulement si la grille est résolue");
        }

        @Test
        @DisplayName("un solveur refuse une grille incohérente")
        void grilleIncoherente() {
            Grille9x9 g = new SourceExemple().charger();
            g.set(3, 1, 5);     // deux 5 sur la même ligne
            assertFalse(Regles.grilleCoherente(g));
            assertFalse(new SolveurBacktracking().resoudre(g));
        }

        @Test
        @DisplayName("l'application résout la grille d'exemple avec le profil fiable")
        void applicationFiable() {
            assertTrue(Regles.grilleResolue(new Application("fiable", null).executer()));
        }
    }

    @Nested
    @DisplayName("Objectif 1 — Factory Method")
    class FabriqueMethode {

        @Test
        @DisplayName("le créateur est abstrait et laisse la création à ses sous-classes")
        void createurAbstrait() throws Exception {
            Optional<Class<?>> createur = Refactoring.classe(P + "ApplicationSudoku");
            assumeTrue(createur.isPresent(), "ApplicationSudoku absente : test ignoré");

            Class<?> c = createur.get();
            assertTrue(java.lang.reflect.Modifier.isAbstract(c.getModifiers()),
                    "le créateur doit être abstrait : c'est la sous-classe qui décide du produit");
            Method fabrique = c.getDeclaredMethod("creerSolveur");
            assertTrue(java.lang.reflect.Modifier.isAbstract(fabrique.getModifiers()),
                    "creerSolveur() doit être abstraite");
            assertTrue(Solveur.class.isAssignableFrom(fabrique.getReturnType()),
                    "la méthode de fabrique renvoie le type ABSTRAIT du produit");
        }

        @Test
        @DisplayName("deux sous-classes produisent deux solveurs différents")
        void deuxCreateurs() throws Exception {
            Optional<Class<?>> rapide = Refactoring.classe(P + "ApplicationRapide");
            Optional<Class<?>> fiable = Refactoring.classe(P + "ApplicationFiable");
            assumeTrue(rapide.isPresent() && fiable.isPresent(),
                    "ApplicationRapide / ApplicationFiable absentes : test ignoré");

            // creerSolveur() est protected : elle appartient au mecanisme
            // d'extension, pas a l'interface publique. Le test passe donc par
            // getDeclaredMethod.
            assertEquals("glouton", nomDuSolveur(rapide.get()));
            assertEquals("backtracking", nomDuSolveur(fiable.get()));
        }

        private String nomDuSolveur(Class<?> createur) throws Exception {
            Method creer = createur.getDeclaredMethod("creerSolveur");
            creer.setAccessible(true);
            return ((Solveur) creer.invoke(Refactoring.instancier(createur))).nom();
        }
    }

    @Nested
    @DisplayName("Objectif 2 — Abstract Factory")
    class FabriqueAbstraite {

        @Test
        @DisplayName("la fabrique produit une famille cohérente : source ET solveur")
        void familleComplete() throws Exception {
            Optional<Class<?>> fabrique = Refactoring.classe(P + "FabriqueSudoku");
            assumeTrue(fabrique.isPresent(), "FabriqueSudoku absente : test ignoré");

            Class<?> f = fabrique.get();
            assertTrue(f.isInterface(),
                    "l'Abstract Factory se remplace par composition : une interface, "
                    + "pas une hiérarchie de créateurs");
            assertTrue(SourceDeGrille.class.isAssignableFrom(f.getMethod("creerSource").getReturnType()));
            assertTrue(Solveur.class.isAssignableFrom(f.getMethod("creerSolveur").getReturnType()));
        }

        @Test
        @DisplayName("on change toute la famille en changeant d'objet fabrique")
        void familleInterchangeable() throws Exception {
            Optional<Class<?>> fabrique = Refactoring.classe(P + "FabriqueSudoku");
            Optional<Class<?>> rapide = Refactoring.classe(P + "FabriqueRapide");
            Optional<Class<?>> fiable = Refactoring.classe(P + "FabriqueFiable");
            assumeTrue(fabrique.isPresent() && rapide.isPresent() && fiable.isPresent(),
                    "fabriques concrètes absentes : test ignoré");

            Method creerSolveur = fabrique.get().getMethod("creerSolveur");
            Method creerSource = fabrique.get().getMethod("creerSource");

            Object fr = Refactoring.instancier(rapide.get());
            Object ff = Refactoring.instancier(fiable.get());

            assertEquals("glouton", ((Solveur) creerSolveur.invoke(fr)).nom());
            assertEquals("backtracking", ((Solveur) creerSolveur.invoke(ff)).nom());
            assertTrue(Regles.grilleCoherente(((SourceDeGrille) creerSource.invoke(fr)).charger()));
        }
    }
}
