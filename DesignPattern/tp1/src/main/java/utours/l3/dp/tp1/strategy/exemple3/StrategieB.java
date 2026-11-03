package utours.l3.dp.tp1.strategy.exemple3;

public class StrategieB implements Strategie {
    @Override
    public void executer(Contexte contexte) {
        // Cette stratégie a besoin de DEUX informations : la signature n'a
        // pourtant pas changé.
        System.out.println("StrategieB : " + contexte.getLibelle() + " = "
                + (contexte.getValeur() * contexte.getValeur()));
    }
}
