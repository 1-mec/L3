package utours.l3.dp.tp4.meteo;

/** Simulation d'un capteur : il pousse des relevés dans la station. */
public class CapteurVent {

    private final StationMeteo station;

    public CapteurVent(StationMeteo station) {
        this.station = station;
    }

    public void releve(double temperature, double vitesseVent) {
        station.nouvellesMesures(temperature, vitesseVent);
    }

    public static void main(String[] args) {
        StationMeteo station = new StationMeteo();
        CapteurVent capteur = new CapteurVent(station);
        capteur.releve(18.4, 32);
        capteur.releve(17.9, 112);
    }
}
