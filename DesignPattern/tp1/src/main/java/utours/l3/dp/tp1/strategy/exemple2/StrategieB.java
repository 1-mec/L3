package utours.l3.dp.tp1.strategy.exemple2;

public class StrategieB implements Strategie {
    @Override
    public void executer(int valeur) {
        System.out.println("StrategieB : le carré de " + valeur + " vaut " + (valeur * valeur));
    }
}
