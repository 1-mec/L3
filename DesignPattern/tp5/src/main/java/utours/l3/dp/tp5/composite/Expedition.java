package utours.l3.dp.tp5.composite;

/**
 * État initial : c'est le CLIENT qui connaît la structure de l'arbre et qui
 * sait la parcourir.
 *
 * <p>Conséquences, toutes désagréables :</p>
 * <ul>
 *   <li>chaque nouveau traitement — le poids, mais aussi le prix, le volume,
 *       l'inventaire, l'affichage — réécrit la même récursion et la même
 *       cascade de {@code instanceof} ;</li>
 *   <li>ajouter une troisième sorte de contenu (une palette, un contenant
 *       consigné) oblige à retrouver et compléter <em>tous</em> ces
 *       {@code instanceof}, sans que le compilateur ne signale les oublis ;</li>
 *   <li>le {@code else} final ne peut rien faire de sensé : il lève une
 *       exception à l'exécution pour un problème qui aurait dû être détecté à
 *       la compilation.</li>
 * </ul>
 */
public final class Expedition {

    private Expedition() { }

    /** Calcule le poids total d'un contenu, quel qu'il soit. */
    public static long poidsTotal(Object element) {
        if (element instanceof Article article) {
            return article.getMasseEnGrammes();
        } else if (element instanceof Colis colis) {
            long total = colis.getTareEnGrammes();
            for (Object enfant : colis.getContenu()) {
                total += poidsTotal(enfant);
            }
            return total;
        } else {
            throw new IllegalArgumentException(
                    "Contenu inconnu : " + element.getClass());
        }
    }

    public static void main(String[] args) {
        Colis carton = new Colis("carton", 200);
        carton.ajouter(new Article("livre", 450));
        Colis pochette = new Colis("pochette", 20);
        pochette.ajouter(new Article("carte mémoire", 5));
        pochette.ajouter(new Article("câble", 60));
        carton.ajouter(pochette);

        System.out.println("Poids total : " + poidsTotal(carton) + " g");
    }
}
