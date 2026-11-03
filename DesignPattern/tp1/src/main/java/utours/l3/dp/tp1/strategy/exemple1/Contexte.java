package utours.l3.dp.tp1.strategy.exemple1;

/**
 * Contexte : il rend un service, mais confie à une stratégie la partie
 * variable de ce service. Il ne connaît que l'interface.
 *
 * <p>Forme de base : aucune donnée n'est échangée entre le contexte et la
 * stratégie. Les exemples 2 et 3 traitent les deux sens de circulation.</p>
 */
public class Contexte {

    private Strategie strategie;

    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

    public void operation() {
        System.out.println("Contexte : début du travail");
        strategie.executer();
        System.out.println("Contexte : fin du travail");
    }
}
