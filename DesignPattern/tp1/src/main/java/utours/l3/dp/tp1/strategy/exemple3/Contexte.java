package utours.l3.dp.tp1.strategy.exemple3;

public class Contexte {

    private final String libelle;
    private final int valeur;
    private Strategie strategie;

    public Contexte(String libelle, int valeur) {
        this.libelle = libelle;
        this.valeur = valeur;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getValeur() {
        return valeur;
    }

    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

    public void operation() {
        // Le contexte ne transmet que lui-même : mode « pull ».
        strategie.executer(this);
    }
}
