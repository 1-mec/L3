package utours.l3.dp.tp4.exemple2;

import java.util.Observable;
import java.util.Observer;

/**
 * Le pattern tel que le JDK le proposait jusqu'à Java 8 — et pourquoi on ne
 * s'en sert plus.
 *
 * <p>{@code java.util.Observable} et {@code java.util.Observer} sont
 * <strong>dépréciés depuis Java 9</strong>. Ce code est conservé parce qu'on
 * en rencontre encore, et parce que ses trois défauts sont instructifs :</p>
 * <ol>
 *   <li>{@code Observable} est une <strong>classe</strong> : elle consomme
 *       l'unique héritage disponible. Un livre qui dérive déjà d'autre chose
 *       ne peut pas être observable ;</li>
 *   <li>la notification n'est <strong>pas typée</strong> :
 *       {@code update(Observable, Object)} impose un transtypage, sans aucune
 *       garantie du compilateur ;</li>
 *   <li>{@code setChanged()} est un état interne qu'il faut penser à
 *       positionner : l'oublier fait <strong>silencieusement</strong>
 *       disparaître les notifications. C'est le piège classique.</li>
 * </ol>
 */
@SuppressWarnings("deprecation")
public final class DemoObservableHistorique {

    private DemoObservableHistorique() { }

    /** Ici le livre EST un Observable : il ne peut plus rien être d'autre. */
    public static class LivreHistorique extends Observable {

        private String titre;

        public LivreHistorique(String titre) {
            this.titre = titre;
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
            setChanged();          // à ne surtout pas oublier
            notifyObservers();
        }
    }

    public static void main(String[] args) {
        LivreHistorique livre = new LivreHistorique("Sans titre");
        Observer trace = (o, argument) ->
                System.out.println("[trace] " + ((LivreHistorique) o).getTitre());
        livre.addObserver(trace);
        livre.setTitre("Design Patterns");
    }
}
