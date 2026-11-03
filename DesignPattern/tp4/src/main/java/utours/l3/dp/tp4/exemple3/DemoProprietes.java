package utours.l3.dp.tp4.exemple3;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Le même pattern avec {@code java.beans.PropertyChangeSupport} : la forme
 * générique recommandée aujourd'hui quand on n'a pas de bibliothèque plus
 * spécifique sous la main.
 *
 * <p>Trois gains par rapport à la version dépréciée :</p>
 * <ul>
 *   <li>le mécanisme est <strong>détenu</strong>, pas hérité : l'héritage
 *       reste libre pour le domaine ;</li>
 *   <li>la notification transporte le <strong>nom de la propriété</strong>,
 *       l'ancienne et la nouvelle valeur : un observateur peut ne réagir qu'à
 *       ce qui le concerne ;</li>
 *   <li>rien n'est envoyé si la valeur n'a pas changé — comportement fourni
 *       par {@code firePropertyChange}, qu'il faudrait écrire à la main
 *       ailleurs.</li>
 * </ul>
 *
 * <p>Selon le contexte on préférera : les <em>listeners</em> typés de la
 * bibliothèque graphique employée, les propriétés observables de JavaFX, ou
 * {@code java.util.concurrent.Flow} lorsqu'il faut en plus gérer le débit.</p>
 */
public final class DemoProprietes {

    private DemoProprietes() { }

    public static class LivreObservable {

        private final PropertyChangeSupport support = new PropertyChangeSupport(this);
        private String titre;

        public LivreObservable(String titre) {
            this.titre = titre;
        }

        public void addPropertyChangeListener(PropertyChangeListener ecouteur) {
            support.addPropertyChangeListener(ecouteur);
        }

        public void removePropertyChangeListener(PropertyChangeListener ecouteur) {
            support.removePropertyChangeListener(ecouteur);
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String nouveau) {
            String ancien = this.titre;
            this.titre = nouveau;
            support.firePropertyChange("titre", ancien, nouveau);
        }
    }

    public static void main(String[] args) {
        LivreObservable livre = new LivreObservable("Sans titre");
        livre.addPropertyChangeListener(evenement ->
                System.out.printf("[%s] %s -> %s%n", evenement.getPropertyName(),
                        evenement.getOldValue(), evenement.getNewValue()));

        livre.setTitre("Design Patterns");
        livre.setTitre("Design Patterns");   // valeur inchangée : rien n'est notifié
    }
}
