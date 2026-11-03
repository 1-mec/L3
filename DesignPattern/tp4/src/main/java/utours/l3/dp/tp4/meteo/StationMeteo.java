package utours.l3.dp.tp4.meteo;

/**
 * Noyau logiciel de la centrale : il détient les mesures.
 *
 * <p>État initial : il affiche lui-même. Le noyau est donc solidaire d'un
 * affichage console, alors que le produit existe en version analogique,
 * numérique, ou les deux — et qu'on voudrait un seul noyau pour les trois.</p>
 */
public class StationMeteo {

    private double temperature;
    private double vitesseVent;

    public double getTemperature() {
        return temperature;
    }

    public double getVitesseVent() {
        return vitesseVent;
    }

    /** Appelée par les capteurs à chaque relevé. */
    public void nouvellesMesures(double temperature, double vitesseVent) {
        this.temperature = temperature;
        this.vitesseVent = vitesseVent;
        afficher();
    }

    /** Le défaut : le noyau connaît la présentation. */
    private void afficher() {
        System.out.printf("Température %.1f °C, vent %.0f km/h%n", temperature, vitesseVent);
    }
}
