package utours.l3.dp.tp5.composite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utours.l3.dp.tp5.outils.Refactoring;

@DisplayName("Partie B — arborescence de colis")
class ExpeditionTest {

    private static final String P = "utours.l3.dp.tp5.composite.";

    /** carton(200) { livre(450), pochette(20) { carte(5), câble(60) } } = 735 g */
    private static Colis arbreTemoin() {
        Colis carton = new Colis("carton", 200);
        carton.ajouter(new Article("livre", 450));
        Colis pochette = new Colis("pochette", 20);
        pochette.ajouter(new Article("carte mémoire", 5));
        pochette.ajouter(new Article("câble", 60));
        carton.ajouter(pochette);
        return carton;
    }

    @Nested
    @DisplayName("Invariants — doivent passer AVANT et APRÈS")
    class Invariants {

        @Test
        @DisplayName("le poids total d'un colis imbriqué vaut 735 g")
        void poidsTotal() {
            assertEquals(735L, Expedition.poidsTotal(arbreTemoin()));
        }

        @Test
        @DisplayName("un article seul pèse sa propre masse")
        void poidsArticle() {
            assertEquals(450L, Expedition.poidsTotal(new Article("livre", 450)));
        }

        @Test
        @DisplayName("un colis vide pèse sa tare")
        void poidsColisVide() {
            assertEquals(200L, Expedition.poidsTotal(new Colis("carton", 200)));
        }
    }

    @Nested
    @DisplayName("Objectifs — ignorés tant que le Composite n'est pas en place")
    class Objectifs {

        private Optional<Class<?>> element() {
            return Refactoring.classe(P + "Element");
        }

        @Test
        @DisplayName("Article et Colis partagent un type Element")
        void typeCommun() {
            Optional<Class<?>> e = element();
            assumeTrue(e.isPresent(), "Element n'existe pas encore : test ignoré");
            assertTrue(e.get().isInterface(), "Element doit être une interface");
            assertTrue(e.get().isAssignableFrom(Article.class), "Article doit être un Element");
            assertTrue(e.get().isAssignableFrom(Colis.class), "Colis doit être un Element");
        }

        @Test
        @DisplayName("chaque élément sait donner son poids, sans que le client ait à le demander")
        void poidsPolymorphe() throws Exception {
            Optional<Class<?>> e = element();
            assumeTrue(e.isPresent(), "Element n'existe pas encore : test ignoré");

            Method poids = e.get().getMethod("poids");
            assertEquals(735L, ((Number) poids.invoke(arbreTemoin())).longValue());
            assertEquals(450L, ((Number) poids.invoke(new Article("livre", 450))).longValue());
        }

        @Test
        @DisplayName("un troisième type d'élément s'intègre sans modifier le code existant")
        void ouvertALExtension() throws Exception {
            Optional<Class<?>> e = element();
            assumeTrue(e.isPresent(), "refactoring non commencé : test ignoré");
            Optional<Class<?>> palette = Refactoring.classe(P + "Palette");
            assumeTrue(palette.isPresent(),
                    "Palette n'existe pas encore : test ignoré (question « ouverture »)");

            assertTrue(e.get().isAssignableFrom(palette.get()),
                    "Palette doit être un Element");
            Object p = Refactoring.instancier(palette.get());
            Method poids = e.get().getMethod("poids");
            assertTrue(((Number) poids.invoke(p)).longValue() >= 0,
                    "une Palette doit savoir se peser, sans qu'Expedition ait changé");

            // Et elle se range dans un colis comme n'importe quel autre élément.
            Colis carton = new Colis("carton", 100);
            Method ajouter = Colis.class.getMethod("ajouter", e.get());
            ajouter.invoke(carton, p);
            assertTrue(((Number) poids.invoke(carton)).longValue() > 100L);
        }

        @Test
        @DisplayName("un colis est itérable, et le parcours descend dans l'arbre")
        void parcoursEnProfondeur() {
            assumeTrue(element().isPresent(), "refactoring non commencé : test ignoré");
            assumeTrue(Iterable.class.isAssignableFrom(Colis.class),
                    "Colis n'est pas encore Iterable : test ignoré");

            List<String> visites = new ArrayList<>();
            for (Object o : (Iterable<?>) arbreTemoin()) {
                visites.add(nomDe(o));
            }
            assertTrue(visites.contains("livre") && visites.contains("carte mémoire")
                            && visites.contains("câble"),
                    "le parcours doit atteindre les feuilles des sous-colis, "
                    + "et pas seulement les enfants directs. Visités : " + visites);
        }

        private String nomDe(Object o) {
            try {
                return String.valueOf(o.getClass().getMethod("getNom").invoke(o));
            } catch (ReflectiveOperationException ex) {
                return o.toString();
            }
        }
    }
}
