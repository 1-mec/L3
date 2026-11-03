package utours.l3.dp.tp3.singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utours.l3.dp.tp3.outils.Refactoring;
import utours.l3.dp.tp3.sudoku.Regles;

@DisplayName("Partie B — la fabrique et le Singleton")
class FabriqueUniqueTest {

    private static final String P = "utours.l3.dp.tp3.singleton.";

    @Nested
    @DisplayName("Invariants")
    class Invariants {

        @Test
        @DisplayName("la fabrique produit une famille qui fonctionne")
        void familleFonctionnelle() {
            FabriqueSudoku fabrique = new FabriqueFiable();
            var grille = fabrique.creerSource().charger();
            assertTrue(fabrique.creerSolveur().resoudre(grille));
            assertTrue(Regles.grilleResolue(grille));
        }
    }

    @Nested
    @DisplayName("Objectif 1 — la fabrique EST un singleton (héritage)")
    class ParHeritage {

        @Test
        @DisplayName("le constructeur est privé et l'instance est unique")
        void instanceUnique() throws Exception {
            Optional<Class<?>> c = Refactoring.classe(P + "FabriqueUnique");
            assumeTrue(c.isPresent(), "FabriqueUnique absente : test ignoré");

            for (Constructor<?> ctor : c.get().getDeclaredConstructors()) {
                assertTrue(Modifier.isPrivate(ctor.getModifiers()),
                        "tous les constructeurs doivent être privés, "
                        + "sinon rien n'empêche d'obtenir une seconde instance");
            }
            Method acces = c.get().getMethod("getInstance");
            assertTrue(Modifier.isStatic(acces.getModifiers()));
            Object a = acces.invoke(null);
            Object b = acces.invoke(null);
            assertNotNull(a);
            assertSame(a, b, "getInstance() doit toujours rendre la même instance");
            assertTrue(FabriqueSudoku.class.isInstance(a),
                    "la fabrique unique reste une FabriqueSudoku : "
                    + "ses clients ne changent pas");
        }
    }

    @Nested
    @DisplayName("Objectif 2 — le singleton DÉLÈGUE à une fabrique (composition)")
    class ParDelegation {

        @Test
        @DisplayName("le singleton expose les mêmes services et les sous-traite")
        void delegation() throws Exception {
            Optional<Class<?>> c = Refactoring.classe(P + "AccesFabrique");
            assumeTrue(c.isPresent(), "AccesFabrique absente : test ignoré");

            Method acces = c.get().getMethod("getInstance");
            Object singleton = acces.invoke(null);
            assertSame(singleton, acces.invoke(null));
            assertTrue(FabriqueSudoku.class.isInstance(singleton),
                    "le singleton adapte la fabrique : il en expose l'interface");

            FabriqueSudoku f = (FabriqueSudoku) singleton;
            assertEquals("fiable", f.profil());
            assertNotNull(f.creerSolveur());

            // La délégation implique que la fabrique déléguée soit un OBJET
            // détenu, et non une super-classe.
            boolean contientUneFabrique = java.util.Arrays.stream(c.get().getDeclaredFields())
                    .anyMatch(champ -> FabriqueSudoku.class.isAssignableFrom(champ.getType()));
            assertTrue(contientUneFabrique,
                    "la variante par délégation détient une fabrique, elle n'en hérite pas");
        }

        @Test
        @DisplayName("les produits restent créés à chaque appel : un singleton n'est pas un cache")
        void produitsDistincts() throws Exception {
            Optional<Class<?>> c = Refactoring.classe(P + "AccesFabrique");
            assumeTrue(c.isPresent(), "AccesFabrique absente : test ignoré");
            FabriqueSudoku f = (FabriqueSudoku) c.get().getMethod("getInstance").invoke(null);
            assertTrue(f.creerSolveur() != f.creerSolveur(),
                    "l'unicité porte sur la FABRIQUE, pas sur ses produits : "
                    + "deux solveurs partagés auraient un état commun");
        }
    }
}
