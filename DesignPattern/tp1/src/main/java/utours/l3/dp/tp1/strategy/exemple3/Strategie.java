package utours.l3.dp.tp1.strategy.exemple3;

/**
 * Variante « la stratégie tire les données » : le contexte se passe
 * lui-même, la stratégie va chercher ce dont elle a besoin.
 *
 * <p>Une seule signature, quelle que soit la quantité d'information
 * nécessaire ; chaque stratégie ne dépend que des accesseurs qu'elle
 * utilise. C'est cette variante que l'on retrouvera dans l'Observer.</p>
 */
public interface Strategie {
    void executer(Contexte contexte);
}
