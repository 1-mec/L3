package utours.l3.dp.tp1.strategy.exemple1;

/** Le client choisit la stratégie, puis demande le service. */
public final class Client {

    private Client() { }

    public static void main(String[] args) {
        Contexte contexte = new Contexte();

        contexte.setStrategie(new StrategieA());
        contexte.operation();

        // Le remplacement se fait à l'exécution, sans reconstruire le contexte.
        contexte.setStrategie(new StrategieB());
        contexte.operation();
    }
}
