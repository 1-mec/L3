package utours.l3.dp.tp1.strategy.exercice1;

import java.util.ArrayList;
import java.util.List;

/**
 * Liste de livres produite par une recherche.
 *
 * <p>Cette classe est un simple relais : elle parcourt sa collection et
 * demande à chaque livre de s'afficher. Elle ne décide rien du rendu.</p>
 */
public class ListeLivres {

    private final List<Livre> livres = new ArrayList<>();

    public void addLivre(Livre livre) {
        livres.add(livre);
    }

    /** @return le nombre d'ouvrages de la liste */
    public int taille() {
        return livres.size();
    }

    /** @return l'ouvrage de rang donné, à partir de 0 */
    public Livre get(int rang) {
        return livres.get(rang);
    }

    /** Affiche toute la liste, en format résumé. */
    public void afficher() {
        for (Livre livre : livres) {
            livre.afficherSimple();
        }
    }
}
