package utours.l3.dp.tp3.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Le même parcours sur trois structures de données différentes.
 *
 * <p>Trois remarques de bonne pratique, à propos de ce que ce code ne fait
 * pas :</p>
 * <ul>
 *   <li>les collections sont <strong>génériques</strong> ({@code List<String>}
 *       et non {@code List} tout court) : le compilateur vérifie les types, et
 *       aucun transtypage n'est nécessaire à la lecture. Un type brut est une
 *       survivance d'avant Java 5 ;</li>
 *   <li>la variable est déclarée au type <strong>interface</strong>
 *       ({@code List}, {@code Set}) et non au type concret : changer
 *       d'implémentation ne coûte alors qu'une ligne ;</li>
 *   <li>on ne se sert pas de {@code Vector}, qui est synchronisé sans qu'on
 *       le demande et ne sert plus qu'à la compatibilité.</li>
 * </ul>
 */
public final class DemoCollections {

    private DemoCollections() { }

    public static void main(String[] args) {
        List<String> liste = new ArrayList<>(List.of("C", "A", "B", "E", "F", "D"));
        Set<String> ensemble = new TreeSet<>(liste);

        System.out.println("Itérateur explicite sur une List :");
        Iterator<String> it = liste.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // La boucle « for-each » construit et pilote l'itérateur pour vous.
        // C'est la forme à préférer : moins de code, et pas d'erreur possible
        // sur la condition d'arrêt.
        System.out.println("Itérateur implicite sur un TreeSet (donc trié) :");
        for (String valeur : ensemble) {
            System.out.print(valeur + " ");
        }
        System.out.println();
    }
}
