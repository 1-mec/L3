package utours.l3.dp.tp4.courses;

import java.util.ArrayList;
import java.util.List;

/**
 * Objet métier : la liste de courses (le « M » du MVC de L2).
 *
 * <p>État initial : elle ne prévient personne quand elle change. Toute vue qui
 * l'affiche doit donc être rafraîchie à la main par celui qui a provoqué la
 * modification — c'est-à-dire que chaque appelant doit connaître toutes les
 * vues.</p>
 */
public class ListeCourses {

    private final List<String> articles = new ArrayList<>();

    public void ajouter(String article) {
        articles.add(article);
    }

    public void retirer(String article) {
        articles.remove(article);
    }

    public List<String> articles() {
        return List.copyOf(articles);
    }

    public int taille() {
        return articles.size();
    }
}
