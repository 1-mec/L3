package utours.l3.dp.tp4.exemple1;

import java.util.ArrayList;
import java.util.List;

/**
 * Le mécanisme d'observation, isolé du domaine.
 *
 * <p>C'est une <strong>interface à méthodes par défaut</strong>, et non une
 * classe : en Java on n'hérite que d'une fois. Une classe métier qui dérive
 * déjà d'autre chose peut ainsi devenir observable. C'est précisément le
 * défaut de {@code java.util.Observable}, qui est une classe — et l'une des
 * raisons de sa dépréciation.</p>
 *
 * <p>La liste d'observateurs ne peut pas être un champ d'interface : elle est
 * fournie par la classe qui implémente, à travers {@link #observateurs()}.</p>
 */
public interface Sujet {

    /** @return la liste modifiable des observateurs, détenue par le sujet */
    List<Observateur> observateurs();

    default void attacher(Observateur observateur) {
        observateurs().add(observateur);
    }

    /**
     * Résilie un abonnement.
     *
     * <p>Méthode facile à oublier, et son absence est une cause classique de
     * fuite mémoire : un observateur jamais détaché maintient une référence
     * vers le sujet, qui ne peut plus être libéré.</p>
     */
    default void detacher(Observateur observateur) {
        observateurs().remove(observateur);
    }

    /**
     * Prévient tous les observateurs.
     *
     * <p>La copie de la liste n'est pas un détail : sans elle, un observateur
     * qui se détache pendant sa propre notification provoquerait une
     * {@code ConcurrentModificationException}.</p>
     */
    default void notifier() {
        for (Observateur observateur : new ArrayList<>(observateurs())) {
            observateur.mettreAJour(this);
        }
    }
}
