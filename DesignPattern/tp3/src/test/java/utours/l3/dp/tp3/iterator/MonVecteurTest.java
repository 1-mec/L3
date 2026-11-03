package utours.l3.dp.tp3.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Partie A — écrire son propre itérateur")
class MonVecteurTest {

    private static MonVecteur vecteurTemoin() {
        MonVecteur v = new MonVecteur(6);
        int[] valeurs = {1, 4, 5, 9, 14, 23};
        for (int i = 0; i < valeurs.length; i++) {
            v.set(i, valeurs[i]);
        }
        return v;
    }

    @Nested
    @DisplayName("Invariants")
    class Invariants {

        @Test
        @DisplayName("le vecteur retient ce qu'on y place")
        void contenu() {
            assertEquals("[1, 4, 5, 9, 14, 23]", vecteurTemoin().toString());
            assertEquals(6, vecteurTemoin().taille());
        }
    }

    @Nested
    @DisplayName("Objectifs — ignorés tant que l'itérateur n'existe pas")
    class Objectifs {

        @Test
        @DisplayName("MonVecteur s'intègre au framework : il est Iterable")
        void estIterable() {
            assumeTrue(Iterable.class.isAssignableFrom(MonVecteur.class),
                    "MonVecteur n'est pas encore Iterable : test ignoré");
            List<Integer> vus = new ArrayList<>();
            for (Object o : (Iterable<?>) vecteurTemoin()) {
                vus.add(((Number) o).intValue());
            }
            assertEquals(List.of(1, 4, 5, 9, 14, 23), vus,
                    "la boucle for-each doit parcourir le vecteur dans l'ordre");
        }

        @Test
        @DisplayName("chaque appel à iterator() rend un itérateur neuf")
        void iterateursIndependants() {
            assumeTrue(Iterable.class.isAssignableFrom(MonVecteur.class),
                    "MonVecteur n'est pas encore Iterable : test ignoré");
            Iterable<?> v = (Iterable<?>) vecteurTemoin();

            Iterator<?> premier = v.iterator();
            premier.next();
            Iterator<?> second = v.iterator();

            assertEquals(1, ((Number) second.next()).intValue(),
                    "un itérateur ne se recycle pas : on en redemande un. "
                    + "Les deux doivent être indépendants.");
        }

        @Test
        @DisplayName("deux itérateurs décalés donnent les différences terme à terme")
        void differencesTermeATerme() {
            assumeTrue(Iterable.class.isAssignableFrom(MonVecteur.class),
                    "MonVecteur n'est pas encore Iterable : test ignoré");
            Iterable<?> v = (Iterable<?>) vecteurTemoin();

            Iterator<?> courant = v.iterator();
            Iterator<?> suivant = v.iterator();
            suivant.next();                 // décalage d'un rang

            List<Integer> ecarts = new ArrayList<>();
            while (suivant.hasNext()) {
                ecarts.add(((Number) suivant.next()).intValue()
                         - ((Number) courant.next()).intValue());
            }
            assertEquals(List.of(3, 1, 4, 5, 9), ecarts);
        }

        @Test
        @DisplayName("l'itérateur ne rend pas la représentation interne accessible")
        void encapsulationPreservee() {
            assumeTrue(Iterable.class.isAssignableFrom(MonVecteur.class),
                    "MonVecteur n'est pas encore Iterable : test ignoré");
            boolean fuite = java.util.Arrays.stream(MonVecteur.class.getMethods())
                    .anyMatch(m -> m.getReturnType() == int[].class);
            assertTrue(!fuite,
                    "aucune méthode publique ne doit rendre le tableau interne : "
                    + "l'itérateur existe précisément pour éviter cela");
        }
    }
}
