package utours.l3.dp.tp4.meteo;

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
import utours.l3.dp.tp4.exemple1.Observateur;
import utours.l3.dp.tp4.exemple1.Sujet;
import utours.l3.dp.tp4.outils.Refactoring;

@DisplayName("Exercice 1 — la centrale météo")
class ObservationTest {

    private static final String P = "utours.l3.dp.tp4.meteo.";

    @Nested
    @DisplayName("Le mécanisme fourni en exemple")
    class Mecanisme {

        @Test
        @DisplayName("tous les observateurs abonnés sont prévenus")
        void notificationDiffusee() {
            var livre = new utours.l3.dp.tp4.exemple1.LivreObservable("a");
            List<String> vus = new ArrayList<>();
            livre.attacher(s -> vus.add("un:" + titre(s)));
            livre.attacher(s -> vus.add("deux:" + titre(s)));

            livre.setTitre("b");
            assertEquals(List.of("un:b", "deux:b"), vus);
        }

        @Test
        @DisplayName("un observateur détaché n'est plus prévenu")
        void detachement() {
            var livre = new utours.l3.dp.tp4.exemple1.LivreObservable("a");
            List<String> vus = new ArrayList<>();
            Observateur o = s -> vus.add(titre(s));
            livre.attacher(o);
            livre.setTitre("b");
            livre.detacher(o);
            livre.setTitre("c");
            assertEquals(List.of("b"), vus, "detacher() doit vraiment résilier l'abonnement");
        }

        @Test
        @DisplayName("un observateur peut se détacher pendant sa propre notification")
        void detachementPendantNotification() {
            var livre = new utours.l3.dp.tp4.exemple1.LivreObservable("a");
            List<String> vus = new ArrayList<>();
            Observateur[] boite = new Observateur[1];
            boite[0] = s -> {
                vus.add(titre(s));
                ((Sujet) s).detacher(boite[0]);
            };
            livre.attacher(boite[0]);
            livre.setTitre("b");
            livre.setTitre("c");
            assertEquals(List.of("b"), vus,
                    "la notification doit parcourir une copie de la liste d'observateurs");
        }

        private String titre(Sujet s) {
            return ((utours.l3.dp.tp4.exemple1.Livre) s).getTitre();
        }
    }

    @Nested
    @DisplayName("Invariants de la centrale")
    class Invariants {

        @Test
        @DisplayName("un relevé met les mesures à jour")
        void mesures() {
            StationMeteo station = new StationMeteo();
            new CapteurVent(station).releve(18.4, 32);
            assertEquals(18.4, station.getTemperature(), 1e-9);
            assertEquals(32.0, station.getVitesseVent(), 1e-9);
        }
    }

    @Nested
    @DisplayName("Objectifs — ignorés tant que la station n'est pas observable")
    class Objectifs {

        private Optional<Class<?>> observable() {
            return Refactoring.classe(P + "StationMeteoObservable");
        }

        @Test
        @DisplayName("la station observable greffe le mécanisme sans modifier le noyau")
        void greffe() {
            Optional<Class<?>> c = observable();
            assumeTrue(c.isPresent(), "StationMeteoObservable absente : test ignoré");
            assertTrue(StationMeteo.class.isAssignableFrom(c.get()),
                    "la station observable adapte la station : elle en dérive");
            assertTrue(Sujet.class.isAssignableFrom(c.get()),
                    "…et implémente le mécanisme d'observation");
        }

        @Test
        @DisplayName("un relevé prévient tous les afficheurs")
        void relevePrevient() throws Exception {
            Optional<Class<?>> c = observable();
            assumeTrue(c.isPresent(), "StationMeteoObservable absente : test ignoré");

            Object station = Refactoring.instancier(c.get());
            List<Double> vus = new ArrayList<>();
            Method attacher = Sujet.class.getMethod("attacher", Observateur.class);
            attacher.invoke(station,
                    (Observateur) s -> vus.add(((StationMeteo) s).getVitesseVent()));

            new CapteurVent((StationMeteo) station).releve(17.9, 112);
            assertEquals(List.of(112.0), vus,
                    "le setter de mesures doit déclencher la notification");
        }

        @Test
        @DisplayName("un observateur n'est pas forcément une vue : l'alerte vent")
        void alerteVent() throws Exception {
            Optional<Class<?>> c = observable();
            Optional<Class<?>> alerte = Refactoring.classe(P + "AlerteVent");
            assumeTrue(c.isPresent() && alerte.isPresent(),
                    "AlerteVent absente : test ignoré");

            Object station = Refactoring.instancier(c.get());
            Object observateur = Refactoring.instancier(alerte.get());
            Sujet.class.getMethod("attacher", Observateur.class)
                    .invoke(station, observateur);

            Method declenchees = alerte.get().getMethod("alertesDeclenchees");
            CapteurVent capteur = new CapteurVent((StationMeteo) station);

            capteur.releve(18.0, 80);
            assertEquals(0, ((Number) declenchees.invoke(observateur)).intValue(),
                    "sous le seuil, aucune alerte");
            capteur.releve(18.0, 112);
            assertEquals(1, ((Number) declenchees.invoke(observateur)).intValue(),
                    "au-dessus de 100 km/h, une alerte — et le noyau n'a pas changé");
        }
    }
}
