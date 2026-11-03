package utours.l3.dp.tp1.strategy.exemple3;

public final class Client {

    private Client() { }

    public static void main(String[] args) {
        Contexte contexte = new Contexte("carré de 7", 7);
        contexte.setStrategie(new StrategieA());
        contexte.operation();
        contexte.setStrategie(new StrategieB());
        contexte.operation();
    }
}
