package utours.l3.dp.tp1.strategy.exemple2;

/**
 * Variante « le contexte pousse les données » : la donnée nécessaire au
 * travail est passée en paramètre.
 *
 * <p>Simple, mais la signature dépend de ce que le contexte a à donner :
 * si le besoin change, l'interface change, donc toutes les stratégies.</p>
 */
public interface Strategie {
    void executer(int valeur);
}
