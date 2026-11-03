package utours.l3.dp.tp1.adapter.exemple1;

/**
 * Le service que l'on souhaite réutiliser. Il ferait très bien le travail,
 * mais il ne parle pas la même langue que le client : nom de méthode
 * différent, et il travaille en majuscules.
 *
 * <p>On ne modifie pas son code : c'est l'hypothèse de départ du pattern.</p>
 */
public class ServiceExistant {

    public String traiter(String texte) {
        return "[" + texte.toUpperCase() + "]";
    }
}
