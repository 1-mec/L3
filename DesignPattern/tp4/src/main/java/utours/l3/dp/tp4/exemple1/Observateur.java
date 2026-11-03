package utours.l3.dp.tp4.exemple1;

/**
 * Ce que le sujet sait appeler.
 *
 * <p>Mode <em>pull</em> : le sujet ne transmet que lui-même, et l'observateur
 * va chercher ce qui l'intéresse. Une seule signature convient donc à tous les
 * sujets, et ajouter une donnée observable ne change pas cette interface.</p>
 */
@FunctionalInterface
public interface Observateur {
    void mettreAJour(Sujet sujet);
}
