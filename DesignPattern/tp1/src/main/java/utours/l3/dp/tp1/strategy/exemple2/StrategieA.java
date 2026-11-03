package utours.l3.dp.tp1.strategy.exemple2;

public class StrategieA implements Strategie {
    @Override
    public void executer(int valeur) {
        System.out.println("StrategieA : le double de " + valeur + " vaut " + (2 * valeur));
    }
}
