package utours.l3.dp.tp1.strategy.exemple3;

public class StrategieA implements Strategie {
    @Override
    public void executer(Contexte contexte) {
        System.out.println("StrategieA : " + contexte.getValeur() + " -> "
                + (2 * contexte.getValeur()));
    }
}
