package utours.l3.dp.tp1.strategy.exemple2;

public class Contexte {

    private final int valeur;
    private Strategie strategie;

    public Contexte(int valeur) {
        this.valeur = valeur;
    }

    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

    public void operation() {
        // La donnée voyage du contexte vers la stratégie : mode « push ».
        strategie.executer(valeur);
    }
}
