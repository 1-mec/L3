package utours.l3.dp.tp4.courses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utours.l3.dp.tp4.exemple1.Observateur;
import utours.l3.dp.tp4.exemple1.Sujet;
import utours.l3.dp.tp4.outils.Refactoring;

@DisplayName("Exercice 2 — la liste de courses et sa vue")
class CoursesTest {

    private static final String P = "utours.l3.dp.tp4.courses.";

    @Nested
    @DisplayName("Invariants")
    class Invariants {

        @Test
        @DisplayName("la liste retient ce qu'on y met")
        void contenu() {
            ListeCourses liste = new ListeCourses();
            liste.ajouter("café");
            liste.ajouter("pain");
            liste.retirer("café");
            assertEquals(List.of("pain"), liste.articles());
        }

        @Test
        @DisplayName("la vue affiche le contenu quand on la rafraîchit")
        void affichage() {
            ListeCourses liste = new ListeCourses();
            liste.ajouter("café");
            ByteArrayOutputStream tampon = new ByteArrayOutputStream();
            new VueTexte(liste, new PrintStream(tampon, true, StandardCharsets.UTF_8))
                    .rafraichir();
            assertTrue(tampon.toString(StandardCharsets.UTF_8).contains("café"));
        }
    }

    @Nested
    @DisplayName("Objectifs — ignorés tant que la liste n'est pas observable")
    class Objectifs {

        @Test
        @DisplayName("la vue se met à jour toute seule quand la liste change")
        void miseAJourAutomatique() throws Exception {
            Optional<Class<?>> c = Refactoring.classe(P + "ListeCoursesObservable");
            assumeTrue(c.isPresent(), "ListeCoursesObservable absente : test ignoré");

            Object liste = Refactoring.instancier(c.get());
            ByteArrayOutputStream tampon = new ByteArrayOutputStream();
            PrintStream sortie = new PrintStream(tampon, true, StandardCharsets.UTF_8);
            VueTexte vue = new VueTexte((ListeCourses) liste, sortie);

            // La vue s'abonne : plus personne n'a à penser à la rafraîchir.
            Sujet.class.getMethod("attacher", Observateur.class)
                    .invoke(liste, (Observateur) s -> vue.rafraichir());

            Method ajouter = ListeCourses.class.getMethod("ajouter", String.class);
            ajouter.invoke(liste, "café");
            ajouter.invoke(liste, "pain");

            String affiche = tampon.toString(StandardCharsets.UTF_8);
            assertTrue(affiche.contains("café") && affiche.contains("pain"),
                    "chaque ajout doit provoquer un rafraîchissement");
            assertTrue(affiche.contains("2 articles"),
                    "le dernier affichage doit refléter l'état courant du modèle");
        }
    }
}
